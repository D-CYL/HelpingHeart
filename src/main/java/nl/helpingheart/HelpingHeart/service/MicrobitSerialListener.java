package nl.helpingheart.HelpingHeart.service;

import com.fazecast.jSerialComm.SerialPort;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import nl.helpingheart.HelpingHeart.model.Gebruiker;
import nl.helpingheart.HelpingHeart.repository.GebruikerRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Component
public class MicrobitSerialListener {

    private final GebruikerRepository repository;
    private final ProcessingNotifier notifier;

    public MicrobitSerialListener(GebruikerRepository repository, ProcessingNotifier notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    @PostConstruct
    public void startListening() {
        SerialPort[] ports = SerialPort.getCommPorts();
        System.out.println("🔍 Available serial ports: " + ports.length);

        SerialPort microbitPort = null;

        // Try to auto-detect micro:bit
        for (SerialPort p : ports) {
            String desc = p.getDescriptivePortName().toLowerCase();
            System.out.println("➡️ " + p.getSystemPortName() + " | " + p.getDescriptivePortName());
            if (desc.contains("microbit") || desc.contains("usb")) {
                microbitPort = p;
                break;
            }
        }

        if (microbitPort == null) {
            System.out.println("❌ Could not detect micro:bit automatically. Using first port as fallback.");
            if (ports.length > 0) microbitPort = ports[0];
            else {
                System.out.println("❌ No serial ports found");
                return;
            }
        }

        microbitPort.setBaudRate(115200);

        microbitPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);


        if (!microbitPort.openPort()) {
            System.out.println("❌ Failed to open serial port: " + microbitPort.getSystemPortName());
            return;
        }

        System.out.println("✅ Listening on serial port: " + microbitPort.getSystemPortName());
        SerialPort selectedPort = microbitPort; // effectively final

        // Thread to continuously read serial input
        new Thread(() -> {
            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(selectedPort.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
//                    System.out.println("📥 Received: " + line);

                    // ✅ Only accept valid input
                    if (!line.equals("A") && !line.equals("B")) {
                        System.out.println("⚠️ Ignored invalid input: " + line);
                        continue;
                    }

                    System.out.println("📥 Received: " + line);
                    // Save to database
                    Gebruiker gebruiker = new Gebruiker();
                    gebruiker.setKeuze(line);                 // "A" or "B"
                    gebruiker.setDatum(LocalDateTime.now());  // DATETIME

                    repository.save(gebruiker);
                    System.out.println("💾 Saved to database");

                    // 🔔 Notify Processing
                    notifier.sendEvent(line);

                    // ✅ Optional debounce
                    Thread.sleep(50);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

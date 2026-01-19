package nl.helpingheart.HelpingHeart.service;

import com.fazecast.jSerialComm.SerialPort;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import nl.helpingheart.HelpingHeart.model.Button_event;
import nl.helpingheart.HelpingHeart.repository.Button_eventRepository;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Component
public class MicrobitSerialListener {

    private final Button_eventRepository repository;

    public MicrobitSerialListener(Button_eventRepository repository) {
        this.repository = repository;
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

        if (!microbitPort.openPort()) {
            System.out.println("❌ Failed to open serial port: " + microbitPort.getSystemPortName());
            return;
        }

        System.out.println("✅ Listening on serial port: " + microbitPort.getSystemPortName());
        SerialPort selectedPort = microbitPort;  // final for lambda
        // Thread to continuously read serial input
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(selectedPort.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    System.out.println("📥 Received: " + line);

                    // Save to database
                    Button_event event = new Button_event();
                    event.setButton(line);
                    event.setEventTime(LocalDateTime.now());
                    repository.save(event);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
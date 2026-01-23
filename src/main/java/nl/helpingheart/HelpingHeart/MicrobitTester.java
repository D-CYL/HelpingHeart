package nl.helpingheart.HelpingHeart;

import com.fazecast.jSerialComm.SerialPort;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MicrobitTester {
    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPort("COM5");
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0); // wait indefinitely

        if (comPort.openPort()) {
            System.out.println("✅ Listening on port: " + comPort.getSystemPortName());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(comPort.getInputStream()))) {
                String line;
                while (true) {
                    line = reader.readLine();
                    if (line != null) {
                        System.out.println("📥 Received: " + line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                comPort.closePort();
                System.out.println("Port closed");
            }
        } else {
            System.out.println("❌ Could not open port");
        }
    }
}

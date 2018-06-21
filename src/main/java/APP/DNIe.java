package APP;

import EventHandler.CardReaderHandler;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import utils.Coding;

public class DNIe {

    public boolean clearCertificates() {
        boolean rtn = false;
        try {
            Process p = Runtime.getRuntime().exec("certutil -user -delstore my 1.3.6.1.4.1.311.20.2.2");
            p.waitFor();
            rtn = true;
        } catch (IOException | InterruptedException e2) {
            rtn = false;
        }

        return rtn;
    }

    public X509Certificate readCertificate() {
        System.out.println("CP A/1");
        X509Certificate resp = null;
        KeyStore ks;
        try {
            System.out.println("CP A/2");
            boolean errorReading = true, driverError = false;
            int iterations = 0,
                    maxIterations = 300;
            while (errorReading) {
                System.out.println("CP A/2_L");
                if (iterations > maxIterations) {
                    driverError = true;
                    break;
                }
                System.out.println("CP A/3");
                ks = KeyStore.getInstance("Windows-MY");
                ks.load(null, null);
                System.out.println("CP A/34");
                Enumeration en = ks.aliases();
                System.out.println("CP A/35");
                if (en.hasMoreElements()) {
                    String aliasKey = (String) en.nextElement();
                    System.out.println("CP A/37");
                    resp = (X509Certificate) ks.getCertificate(aliasKey);
                    errorReading = false;
                } else {
                    System.out.println("CP A/5_S");
                    Thread.sleep(100);
                }
                System.out.println("CP A/36");
            }
            if (driverError) {
                System.out.println("CP A/EE");
                CardReaderHandler.getInstance().cardReaderError("CARD_READER_DRIVER_NOT_FOUND");
            }
        } catch (Exception e) {
            System.out.println("CP A/E2");
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }

        return resp;
    }
}

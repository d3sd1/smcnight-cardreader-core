package APP;

import EventHandler.CardReaderHandler;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import utils.Coding;

public class DNIe
{

    public boolean clearCertificates()
    {
        boolean rtn = false;
        try
        {
            Process p = Runtime.getRuntime().exec("certutil -user -delstore my 1.3.6.1.4.1.311.20.2.2");
            p.waitFor();
            rtn = true;
        }
        catch (IOException | InterruptedException e2)
        {
            rtn = false;
        }

        return rtn;
    }

    public X509Certificate readCertificate()
    {
        X509Certificate resp = null;
        if (this.clearCertificates())
        {
            KeyStore ks;
            try
            {
                boolean errorReading = true, driverError = false;
                int iterations = 0,
                        maxIterations = 300;
                while (errorReading)
                {
                    if (iterations > maxIterations)
                    {
                        driverError = true;
                        break;
                    }
                    ks = KeyStore.getInstance("Windows-MY");
                    ks.load(null, null);
                    Enumeration en = ks.aliases();
                    if (en.hasMoreElements())
                    {
                        String aliasKey = (String) en.nextElement();
                        resp = (X509Certificate) ks.getCertificate(aliasKey);
                        errorReading = false;
                    }
                    else
                    {
                        Thread.sleep(100);
                    }
                }
                if (driverError)
                {
                    CardReaderHandler.getInstance().cardReaderError("CARD_READER_DRIVER_NOT_FOUND");
                }
            }
            catch (Exception e)
            {
                CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
            }
        }
        else
        {
            CardReaderHandler.getInstance().cardReaderError("CARD_READER_CRASH");
        }

        return resp;
    }
}

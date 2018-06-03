package utils;

import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class Coding {
    public static String base64Encode(String str)
    {
        
        String encoded = Base64.getEncoder().encodeToString((str == null ? "":str).getBytes(StandardCharsets.UTF_8));

        return encoded;
    }
    public static String base64Encode(byte[] str)
    {
        return base64Decode(new String(str, StandardCharsets.UTF_8));
    }
    public static String base64Decode(String str)
    {
        byte [] decoded = Base64.getDecoder().decode((str == null ? "":str)); 
        return new String(decoded, StandardCharsets.UTF_8);
    }
    public static String encodeCertificate(X509Certificate cert)
    {
        String pemCert;
        try
        {
            pemCert = "-----BEGIN CERTIFICATE-----\n"
                    + Base64.getEncoder().encodeToString(cert.getEncoded()) + "\n"
                    + "-----END CERTIFICATE-----";
        }
        catch (CertificateEncodingException e)
        {
            pemCert = "";
        }
        return base64Encode(pemCert);
    }
}

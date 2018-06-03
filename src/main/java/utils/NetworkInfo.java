package utils;

import EventHandler.CardReaderHandler;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import scm.Constants;

public class NetworkInfo
{

    public void setMACAddress()
    {
        try
        {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac2 = network.getHardwareAddress();
            if(null == mac2)
            {
                mac2 = "".getBytes();
                CardReaderHandler.getInstance().cardReaderError("PC_INET_CRASH");
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac2.length; i++)
            {
                sb.append(String.format("%02X%s", mac2[i], (i < mac2.length - 1) ? "-" : ""));
            }
            Constants.NETWORK_MAC = sb.toString();

        }
        catch (SocketException | UnknownHostException e)
        {
            Constants.NETWORK_MAC = "";
        }
    }
    public String getMACAddress()
    {
        return Constants.NETWORK_MAC;
    }

    public void setIPAddress()
    {
        try
        {
            InetAddress ip = InetAddress.getLocalHost();
            Constants.NETWORK_IP = ip.getHostAddress();
        }
        catch (UnknownHostException e)
        {
            Constants.NETWORK_IP = "";
        }
    }
    public String getIPAddress()
    {
        return Constants.NETWORK_IP;
    }
}

package scm;

import APP.Call;
import EventHandler.CardReaderHandler;
import utils.AutoUpdater;
import utils.NetworkInfo;
import utils.OSInfo;
import utils.Service;

public class App
{

    private static App instance = null;

    /* Prevenir inicialización directa sin singleton */
    private App()
    {
    }

    public static App getInstance()
    {
        if (instance == null)
        {
            instance = new App();
        }
        return instance;
    }

    public void initialize()
    {
        try
        {
            System.setProperty("java.net.preferIPv6Addresses","true");
            System.setProperty("java.net.preferIPv4Stack","false");
            compatibleOS();
            startService();
            startNetwork();
            startApi();
            updateApp();
        }
        catch (Exception e)
        {
            CardReaderHandler.getInstance().cardReaderError("INIT_ERROR");
            e.printStackTrace();
        }
    }

    public void startApi()
    {
        Call.initInstance();
    }

    public void updateApp()
    {
        /* Update if actual version is lower than last */
        AutoUpdater update = new AutoUpdater("CLIENTS_CARD_READER");
        update.proccess();
    }

    public void compatibleOS()
    {
        /* Check OS Compatibility */
        OSInfo os = new OSInfo();
        if (!os.checkCompatibility())
        {
            CardReaderHandler.getInstance().cardReaderError("INCOMPATIBLE_OS");
        }
    }

    public void startService()
    {
        /* Start service */
        Service service = new Service("CLIENTS_CARD_READER");
        if (!service.init())
        {
            CardReaderHandler.getInstance().cardReaderError("ALREADY_RUNNING");
        }
    }

    public void startNetwork()
    {
        NetworkInfo network = new NetworkInfo();
        network.setMACAddress();
        network.setIPAddress();
    }
}

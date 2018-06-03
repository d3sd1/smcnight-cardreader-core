package utils;

import APP.Api;
import model.Version;
import scm.Constants;

public class AutoUpdater
{

    private int resultCode = 1;
    private final String SOFTWARE_TYPE;
    private final String LAST_VERSION;

    public AutoUpdater(String softwareType)
    {
        this.SOFTWARE_TYPE = softwareType;
        Api api = new Api();
        Version version = api.version();
        LAST_VERSION = version.getVersion();
        
    }

    
    public int proccess()
    {
        /*
        * CODES GUIDE:
        * 1. Success
        * 2. Update network error
        * 3. Update permissions error
         
        File updateFolder = new File("update");
        removeUpdateFolder(updateFolder);
        if (this.checkUpdate())
        {
            updateFolder.mkdir();
            Gui.manager.setUpdatingStatus();
            downloadLastVersion();
            executeLastVersion();
            /* falta reemplazar el jar actual por el descargado en /updates/lastest.exe, y correr ese.
            y que coja la ultima version del api rest
            //removeUpdateFolder(new File("update"));
        }

        return resultCode;*/
        return 1;
    }

    /*private void removeUpdateFolder(File folder)
    {
        File[] files = folder.listFiles();
        if (files != null)
        { //some JVMs return null for empty dirs
            for (File f : files)
            {
                if (f.isDirectory())
                {
                    removeUpdateFolder(f);
                }
                else
                {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    private boolean downloadLastVersion()
    {
        boolean success = false;
        try
        {
            URL website = new URL(Constants.apiUrl + "download");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("update/lastest.exe");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            success = true;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            success = false;
        }
        return success;
    }

    private void executeLastVersion()
    {
        String[] run =
        {
            "update/lastest.exe"
        };

        try
        {
            Runtime.getRuntime().exec(run);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        System.exit(0);

    }*/

    private boolean checkUpdate()
    {
        return !Constants.APP_VERSION.equals(LAST_VERSION);
    }

    public String getLastVersion()
    {
        return this.LAST_VERSION;
    }
}

package utils;

import java.io.IOException;
import java.net.ServerSocket;
import scm.Constants;

public class Service
{
    private ServerSocket serv;
    private String SOFTWARE_TYPE;
    public Service(String softwareType)
    {
        this.SOFTWARE_TYPE = softwareType;
    }
    public boolean init()
    {
        boolean startSuccess = false;
        try
        {
            serv = new ServerSocket(Constants.SERVICE_PORT);
            startSuccess = true;
            this.exitOnClose();
        }
        catch (IOException x)
        {
            startSuccess = false;
        }
        return startSuccess;
    }

    private void exitOnClose()
    {
        
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    serv.close();
                }
                catch (IOException ex)
                {
                    
                }
            }
        });
    }
}

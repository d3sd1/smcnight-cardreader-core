package utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class AppWorker
{

    public void reload()
    {
        try
        {
            final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            final File currentJar = new File(scm.Constants.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            if (!currentJar.getName().endsWith(".jar"))
            {
                return;
            }

            final ArrayList<String> command = new ArrayList<>();
            command.add(javaBin);
            command.add("-jar");
            command.add(currentJar.getPath());

            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
            System.exit(0);
        }
        catch (IOException | URISyntaxException e)
        {
            
        }
    }

    public void reloadAfter(int seconds)
    {
        new java.util.Timer().schedule(new java.util.TimerTask()
        {
            @Override
            public void run()
            {
                AppWorker app = new AppWorker();
                app.reload();
            }
        }, seconds*1000);
    }
    public void closeAfter(int seconds)
    {
        new java.util.Timer().schedule(new java.util.TimerTask()
        {
            @Override
            public void run()
            {
                AppWorker app = new AppWorker();
                app.close();
            }
        }, seconds*1000);
    }
    public void close()
    {
        System.exit(0);
    }
}

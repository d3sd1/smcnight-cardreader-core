package utils;

import java.util.logging.Logger;

public final class OSInfo
{

    private final String OS = System.getProperty("os.name").toLowerCase();

    public boolean checkCompatibility()
    {
        boolean supportedOS = false;
        if (isWindows())
        {
            supportedOS = true;
        }
        return supportedOS;
    }

    public boolean isWindows()
    {
        return (this.OS.contains("win"));
    }

    public boolean isMac()
    {
        return (OS.contains("mac"));
    }

    public boolean isUnix()
    {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }

    public boolean isSolaris()
    {
        return (OS.contains("sunos"));
    }
    private static final Logger LOG = Logger.getLogger(OSInfo.class.getName());

}

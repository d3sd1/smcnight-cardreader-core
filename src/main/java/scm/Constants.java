package scm;


public class Constants {

    public static final int SERVICE_PORT = 33923;
    public static String SCM_WEB = null;
    public static String SCM_API = null;
    public static String NETWORK_MAC;
    public static String NETWORK_IP;
    public static String APP_VERSION = "0.4.0";
    public static boolean IS_VIP_READER = false;

    public static void setEnv(String[] args) {
        boolean isDev = false;
        for (String arg : args) {
            if (arg.equals("--env=dev")) {
                isDev = true;
            }
        }
        if (isDev) {
            /* Dev variables */
            Constants.SCM_WEB = "https://web.discoexample.dev.scmnight.com/";
            Constants.SCM_API = "https://api.discoexample.dev.scmnight.com/app/";
            System.out.println("YOU'RE RUNNING IN DEV ENVIRONMENT. USING GLOBAL DEV SERVER.");
        } else {
            /* Prod variables */
            Constants.SCM_WEB = "https://192.168.1.2/";
            Constants.SCM_API = "https://192.168.1.2/app/";
        }
    }
}

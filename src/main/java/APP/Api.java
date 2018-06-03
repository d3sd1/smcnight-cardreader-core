package APP;

import com.google.gson.Gson;
import java.util.HashMap;
import model.ClientEntrance;
import model.UserEntrance;
import model.Version;

public class Api {
    public Version version()
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("version"), Version.class);
    }
    public ClientEntrance clientEntrance(String encodedCert, boolean forceAccess, boolean vip)
    {
        Gson gson = new Gson();
        HashMap<String, Object> params = new HashMap();
        params.put("cert", encodedCert);
        params.put("forceAccess", forceAccess);
        params.put("vip", vip);
        return gson.fromJson(Call.getInstance().put("client/entrance",params), ClientEntrance.class);
    }
    public UserEntrance userEntrance(String encodedCert)
    {
        Gson gson = new Gson();
        HashMap<String, Object> params = new HashMap();
        params.put("cert", encodedCert);
        return gson.fromJson(Call.getInstance().put("user/entrance",params), UserEntrance.class);
    }
}

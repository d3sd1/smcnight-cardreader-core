package APP;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import model.*;

public class Api {
    public Version version()
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("version"), Version.class);
    }
    public ClientEntrance clientEntrance(String encodedCert, int rate, boolean forceAccess, boolean vip)
    {
        Gson gson = new Gson();
        HashMap<String, Object> params = new HashMap();
        params.put("cert", encodedCert);
        params.put("forceAccess", forceAccess);
        params.put("vip", vip);
        params.put("rate", rate);
        return gson.fromJson(Call.getInstance().put("client/entrance",params), ClientEntrance.class);
    }
    public UserEntrance userEntrance(String encodedCert)
    {
        Gson gson = new Gson();
        HashMap<String, Object> params = new HashMap();
        params.put("cert", encodedCert);
        return gson.fromJson(Call.getInstance().put("user/entrance",params), UserEntrance.class);
    }
    public UserEntrance userEntranceByDni(String dni)
    {
        Gson gson = new Gson();
        HashMap<String, Object> params = new HashMap();
        return gson.fromJson(Call.getInstance().put("user/entrance/" + dni,params), UserEntrance.class);
    }
    public List<ClientEntrancePricing> clientEntrancePricing(String dni)
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("client/pricing/" + dni), new TypeToken<List<ClientEntrancePricing>>(){}.getType());
    }
    public List<ClientBan> clientEntranceBans(String dni)
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("client/bans/" + dni), new TypeToken<List<ClientBan>>(){}.getType());
    }
    public Client clientEntranceData(String encodedCert)
    {
        Gson gson = new Gson();
        HashMap<String, Object> params = new HashMap();
        params.put("cert", encodedCert);
        return gson.fromJson(Call.getInstance().post("client/info",params), Client.class);
    }
    public EntranceType clientEntranceType(String dni)
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("client/entrance/type/" + dni), EntranceType.class);
    }
}

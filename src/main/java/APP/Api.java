package APP;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.*;

public class Api {
    public Version version()
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("version"), Version.class);
    }
    public ClientEntrance clientEntrance(ClientEntrance clientEntrance)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
/*

        HashMap<String, Object> params = new HashMap();
        params.put("vip", clientEntrance.isVip());
        params.put("forceaccess", clientEntrance.isforceaccess());
 */
        return gson.fromJson(Call.getInstance().put("client/entrance",gson.toJson(clientEntrance)), ClientEntrance.class);
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
    public Client clientDataById(String dni)
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("client/info/" + dni), Client.class);
    }
    public List<Gender> genders()
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("client/genders"), new TypeToken<List<Gender>>(){}.getType());
    }
    public List<Nationality> nationalities()
    {
        Gson gson = new Gson();
        return gson.fromJson(Call.getInstance().get("client/nationalities"), new TypeToken<List<Nationality>>(){}.getType());
    }
}

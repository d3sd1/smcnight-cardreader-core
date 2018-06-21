package APP;

import EventHandler.CardReaderHandler;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import scm.Constants;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import utils.NetworkInfo;

public class Call
{

    private static Call instance = null;
    private String token;
    private boolean API_ALLOWED = true;

    /* Prevenir inicialización directa sin singleton */
    private Call()
    {
    }

    public static Call initInstance()
    {
        if (instance == null)
        {
            System.setProperty("java.net.preferIPv6Addresses","true");
            instance = new Call();
            instance.requestApiKey();
        }
        return instance;
    }

    public static Call getInstance()
    {
        return instance;
    }

    private String requestApiKey()
    {
        String json = null;
        try
        {
            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory((HttpRequest request) ->
            {
                request.setParser(new JsonObjectParser(JSON_FACTORY));
            });
            GenericUrl url = new GenericUrl(Constants.apiUrl + "mac-token");
            System.out.println("CALLED URL: " + url);
            HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
            HttpHeaders headers = new HttpHeaders();
            NetworkInfo ni = new NetworkInfo();
            headers.set("mac", ni.getMACAddress());
            requestGoogle.setHeaders(headers);
            HttpResponse response = requestGoogle.execute();
            json = IOUtils.toString(response.getContent(), StandardCharsets.UTF_8);
            Gson gson = new Gson();
            Map<String, String> myMap = gson.fromJson(json, new TypeToken<Map<String, Object>>()
            {
            }.getType());
            this.token = myMap.get("value");
        }
        catch (HttpResponseException ex)
        {
            ex.printStackTrace();
            API_ALLOWED = false;
            switch (ex.getStatusCode())
            {
                case 403:
                    CardReaderHandler.getInstance().cardReaderError("PC_API_NOT_ADDED");
                    break;
                case 406:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
                    break;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            API_ALLOWED = false;
            CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
        }
        return json;
    }

    public String get(String urlstr)
    {

        String json = null;
        try
        {
            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory((HttpRequest request) ->
            {
                request.setParser(new JsonObjectParser(JSON_FACTORY));
            });

            GenericUrl url = new GenericUrl(Constants.apiUrl + urlstr);

            System.out.println("CALLED URL: " + url);
            HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("Bearer " + token);
            requestGoogle.setHeaders(headers);
            HttpResponse response = requestGoogle.execute();

            json = IOUtils.toString(response.getContent(), StandardCharsets.UTF_8);
        }
        catch (HttpResponseException ex)
        {
            ex.printStackTrace();
            switch (ex.getStatusCode())
            {
                case 400:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
                    break;
                case 401:
                    if(this.API_ALLOWED)
                    {
                        requestApiKey();
                    }
                    get(urlstr);
                    break;
                default:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
        }
        return json;
    }

    public String post(String urlstr, HashMap<String, Object> parameters)
    {

        String json = null;
        try
        {
            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory((HttpRequest request) ->
            {
                request.setParser(new JsonObjectParser(JSON_FACTORY));
            });

            GenericUrl url = new GenericUrl(Constants.apiUrl + urlstr);
            System.out.println("CALLED URL: " + url);
            HttpContent content = null;
            if (null != parameters)
            {
                content = new JsonHttpContent(JSON_FACTORY, parameters);
            }
            System.out.println(((JsonHttpContent) content).getData());
            HttpRequest requestGoogle = requestFactory.buildPostRequest(url, content);
            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("Bearer " + token);
            requestGoogle.setHeaders(headers);
            HttpResponse response = requestGoogle.execute();

            json = IOUtils.toString(response.getContent(), StandardCharsets.UTF_8);
            System.out.println("RESP: " + json);
        }
        catch (HttpResponseException ex)
        {
            ex.printStackTrace();
            switch (ex.getStatusCode())
            {
                case 400:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
                    break;
                case 401:
                    if(this.API_ALLOWED)
                    {
                        requestApiKey();
                    }
                    post(urlstr, parameters);
                    break;
                default:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
        }
        return json;
    }

    public String put(String urlstr, HashMap<String, Object> parameters)
    {

        String json = null;
        try
        {
            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory((HttpRequest request) ->
            {
                request.setParser(new JsonObjectParser(JSON_FACTORY));
            });

            GenericUrl url = new GenericUrl(Constants.apiUrl + urlstr);
            System.out.println("CALLED URL: " + url);
            HttpContent content = null;
            if (null != parameters)
            {
                content = new JsonHttpContent(JSON_FACTORY, parameters);
            }
            System.out.println(((JsonHttpContent) content).getData());
            HttpRequest requestGoogle = requestFactory.buildPutRequest(url, content);
            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("Bearer " + token);
            requestGoogle.setHeaders(headers);
            HttpResponse response = requestGoogle.execute();

            json = IOUtils.toString(response.getContent(), StandardCharsets.UTF_8);
        }
        catch (HttpResponseException ex)
        {
            ex.printStackTrace();
            switch (ex.getStatusCode())
            {
                case 400:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
                    break;
                case 401:
                    if(this.API_ALLOWED)
                    {
                        requestApiKey();
                    }
                    put(urlstr, parameters);
                    break;
                default:
                    if (ex.getStatusCode() < 409)
                    {
                        CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
                    }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
        }
        return json;
    }

    public String delete(String urlstr)
    {

        String json = null;
        try
        {
            HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
            JsonFactory JSON_FACTORY = new JacksonFactory();
            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory((HttpRequest request) ->
            {
                request.setParser(new JsonObjectParser(JSON_FACTORY));
            });

            GenericUrl url = new GenericUrl(Constants.apiUrl + urlstr);
            System.out.println("CALLED URL: " + url);
            HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setAuthorization("Bearer " + token);
            requestGoogle.setHeaders(headers);
            HttpResponse response = requestGoogle.execute();

            json = IOUtils.toString(response.getContent(), StandardCharsets.UTF_8);
        }
        catch (HttpResponseException ex)
        {
            ex.printStackTrace();
            switch (ex.getStatusCode())
            {
                case 400:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
                    break;
                case 401:
                    if(this.API_ALLOWED)
                    {
                        requestApiKey();
                    }
                    delete(urlstr);
                    break;
                default:
                    CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            CardReaderHandler.getInstance().cardReaderError("API_ACCESS_ERROR");
        }
        return json;
    }
}

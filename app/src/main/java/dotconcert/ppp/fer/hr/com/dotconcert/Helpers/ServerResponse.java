package dotconcert.ppp.fer.hr.com.dotconcert.Helpers;

/**
 * Created by Cveki on 9.11.2014..
 */

public class ServerResponse {
    public Boolean Successful;
    public String Message;

    public ServerResponse(Boolean successful, String message){
        Successful = successful;
        Message = message;
    }
}

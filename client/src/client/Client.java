/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import encryption.*;
import java.security.PublicKey;

/**
 *
 * @author kamilersz
 */
public class Client {
    private static Socket s;
    public static void sendPacket(JSONObject json) throws IOException {
 
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                s.getOutputStream()));
        out.write(json.toString());
        out.newLine();
        out.write("###");
        out.newLine();
        out.flush();
        System.out.println("sent");       
    }
    
    public static JSONObject recvPacket() throws IOException, JSONException {
        
        String inputLine = null;
        String result = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        System.out.println("open input");
        while ((inputLine = in.readLine()) != null) {
            System.out.println("inputLine " + inputLine);
            if (inputLine.contains("###")) {
                break;
            }
            result = result.concat(inputLine);

        }
        
        return new JSONObject(result);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, JSONException {

        s = new Socket("127.0.0.1", 1234);
        s.setSoTimeout(5000);

        JSONObject json = new JSONObject();
        json.put("method", "getpublickey");

        sendPacket(json);
        System.out.println("sent");
        
        json = recvPacket();
        System.out.println(json.toString(4));
        PublicKey key = (PublicKey)ObjectString.SToO((String)json.get("publickey"));
        System.out.println(key);
        
        json = new JSONObject();
        String username = "kamilersz";
        String password = "test";
        json.put("method","login");
        json.put("username",RSA.encrypt(username, key));
        json.put("password",RSA.encrypt(password, key));
        sendPacket(json);
        
        json = recvPacket();
        
        System.out.println(json.toString(4));

        System.out.println("closing socket");
        s.close();

    }
}

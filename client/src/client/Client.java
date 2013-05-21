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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamilersz
 */
public class Client {

    public static Socket s;

    public static void sendPacket(JSONObject json) throws IOException {

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                s.getOutputStream()));
        out.write(json.toString());
        out.newLine();
        out.write("###");
        out.newLine();
        out.flush();
        lastSendTime = System.currentTimeMillis();
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

        if ("".compareTo(result) == 0) {
            return new JSONObject();
        } else {
            return new JSONObject(result);
        }
    }

    public static JSONObject reqGetPublicKey() throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("method", "getpublickey");

        if (!isConnected()) {
            reconnect();
        }
        sendPacket(json);
        System.out.println("sent");

        json = recvPacket();
        return json;
    }

    public static JSONObject reqLogin(String username, String password) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        json.put("method", "login");
        json.put("username", RSA.encrypt(username, key));
        json.put("password", RSA.encrypt(password, key));
        if (!isConnected()) {
            reconnect();
        }
        sendPacket(json);

        json = recvPacket();
        return json;
    }

    public static JSONObject reqGetTask(String user_id) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        long timestamp = System.currentTimeMillis();
        String token = MD5.hash(user_id + String.valueOf(timestamp));

        json.put("method", "gettask");
        json.put("user_id", RSA.encrypt(user_id, key));
        json.put("timestamp", timestamp);
        json.put("token", token);
        if (!isConnected()) {
            reconnect();
        }
        sendPacket(json);

        json = recvPacket();
        return json;
    }

    public static JSONObject reqSetTask(String user_id, String task_id, int done) throws JSONException, IOException {
        JSONObject json = new JSONObject();
        long timestamp = System.currentTimeMillis();
        String token = MD5.hash(user_id + String.valueOf(timestamp));

        json.put("method", "settask");
        json.put("user_id", RSA.encrypt(user_id, key));
        json.put("timestamp", timestamp);
        json.put("task_id", task_id);
        json.put("token", token);
        json.put("done", done);
        if (!isConnected()) {
            reconnect();
        }
        sendPacket(json);

        json = recvPacket();
        return json;
    }

    public static void reconnect() throws IOException {
        System.out.println("Connecting...");
        s = new Socket("127.0.0.1", 1234);
        s.setSoTimeout(timeout);
        System.out.println("Connected!");

    }
    public static PublicKey key;
    private static int timeout = 5000;
    private static volatile long lastSendTime;

    public static boolean isConnected() throws IOException {
        if (s == null) {
            return false;
        } else if (s.isClosed()) {
            return false;
        } else {
            if (lastSendTime + timeout < System.currentTimeMillis()) {
                s.close();
                return false;
            } else {
                return true;
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = null;
        try {
            json = reqGetPublicKey();
            System.out.println(json.toString(4));

            key = (PublicKey) ObjectString.SToO((String) json.get("publickey"));
            System.out.println(key);

            json = reqLogin("admin", "admin");
            System.out.println(json.toString(4));

            String user_id = json.getString("user_id");
            json = reqGetTask(user_id);
            System.out.println(json.toString(4));

            json = reqSetTask(user_id, "63");
            System.out.println(json.toString(4));

            json = reqGetTask(user_id);
            System.out.println(json.toString(4));

            System.out.println("closing socket");
            s.close();
        } catch (IOException e) {
            System.out.println("Cannot connecto to servero");
        }
    }
}
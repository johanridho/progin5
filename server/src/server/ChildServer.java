/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import encryption.ObjectString;
import encryption.RSA;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kamilersz
 */
class ChildServer extends Thread {

    Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public void sendPacket(JSONObject json) throws IOException {

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                socket.getOutputStream()));
        out.write(json.toString());
        out.newLine();
        out.write("###");
        out.newLine();
        out.flush();
        System.out.println("sent");
    }

    public JSONObject recvPacket() throws IOException, JSONException {

        String inputLine = null;
        String result = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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

    public ChildServer(Socket socket) {
        this.socket = socket;
    }

    public JSONObject respPublicKey(JSONObject json) throws JSONException {
        json = new JSONObject();
        json.put("emergency", true);
        json.put("message", "hello");
        json.put("publickey", ObjectString.OToS(Server.publicKey));
        return json;
    }

    public JSONObject respLogin(JSONObject json) throws JSONException, IOException {
        String username = RSA.decrypt((String) json.get("username"), Server.privateKey);
        String password = RSA.decrypt((String) json.get("password"), Server.privateKey);
        json = new JSONObject(Server.http("GET","http://progin001.ap01.aws.af.cm/api.php/auth/" + username + "/"+password));
        return json;
    }

    @Override
    public void run() {

        System.out.println("open input");
        JSONObject json = null;
        while (!socket.isClosed()) {
            try {
                json = recvPacket();
            } catch (IOException | JSONException ex) {
                //Logger.getLogger(ChildServer.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    socket.close();
                } catch (IOException ex1) {
                    //Logger.getLogger(ChildServer.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            try {
                if ("getpublickey".compareToIgnoreCase((String) json.get("method")) == 0) {
                    json = respPublicKey(json);
                } else if ("login".compareToIgnoreCase((String) json.get("method")) == 0) {
                    json = respLogin(json);
                }
                sendPacket(json);
            } catch (IOException | JSONException ex) {
                //Logger.getLogger(ChildServer.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    socket.close();
                } catch (IOException ex1) {
                    //Logger.getLogger(ChildServer.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
}

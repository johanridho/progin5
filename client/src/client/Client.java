/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import sun.rmi.runtime.Log;

/**
 *
 * @author kamilersz
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, JSONException {

        Socket s = new Socket("127.0.0.1", 1234);
        s.setSoTimeout(5000);

        JSONObject json = new JSONObject();
        json.put("emergency", false);
        json.put("message", "hello");


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                s.getOutputStream()));
        out.write(json.toString());
        out.newLine();
        out.write("###");
        out.newLine();
        out.flush();
        System.out.println("sent");


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

        System.out.println("closing socket");
        s.close();

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author kamilersz
 */
public class Server {

    public static String http(String method, String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        conn.setRequestMethod(method);
        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }
        StringBuilder sb;
        try (BufferedReader rd = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()))) {
            sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
        }

        conn.disconnect();
        return sb.toString();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, JSONException {
        String hasil = http("GET","http://progin001.ap01.aws.af.cm/api.php/getTask/1");
        JSONArray j = new JSONArray(hasil);
        System.out.println(j.toString(4));
    }
}

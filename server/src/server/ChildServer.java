/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamilersz
 */
class ChildServer extends Thread {

    Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public ChildServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        String inputLine = null;
        String result = "";
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ChildServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("open input");
        try {
            while ((inputLine = in.readLine()) != null) {
                System.out.println("inputLine " + inputLine);
                if (inputLine.contains("###")) {
                    break;
                }
                result = result.concat(inputLine);

            }
        } catch (IOException ex) {
            Logger.getLogger(ChildServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out.write("hello");
            out.newLine();
            out.write("###");
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChildServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

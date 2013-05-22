/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import encryption.RSA;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
        if ("put".compareToIgnoreCase(method) == 0) {
            conn.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write("Hello world");
            out.close();
        }
        if (conn.getResponseCode() != 200) {
            System.out.println("exception");
            System.out.println(conn.getResponseMessage());
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
     *
     * public static void main(String[] args) throws IOException, JSONException
     * { String hasil = http("GET",
     * "http://progin001.ap01.aws.af.cm/api.php/getTask/1"); JSONArray j = new
     * JSONArray(hasil); System.out.println(j.toString(4)); }
     */
    final private static int port = 1234;
    public static PublicKey publicKey;
    public static PrivateKey privateKey;

    public static void main(String args[]) throws IOException, URISyntaxException {
        
//        Desktop.getDesktop().browse(new URI("http://www.google.com"));
        JFrame fr = new JFrame();
        fr.setSize(1200, 800);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pn = new JPanel(null);
        fr.setContentPane(pn);
        
        JButton btn2 = new JButton("Stop");
        btn2.setBounds(300, 30, 200, 30);
//        btn2.setVisible(false);
        pn.add(btn2);
        
        JButton btn = new JButton("Start");
        btn.setBounds(30, 30, 200, 30);
        pn.add(btn);
        final JFrame frCheckbox = fr;
        final JButton btn2checkbox = btn2;
        
        btn.addActionListener(new ActionListener() {
            
            
            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet.");                           
//                btn2checkbox.setVisible(true);
                System.out.println("starting server...");
                try {
            // Check if the pair of keys are present else generate those.
                    if (!RSA.areKeysPresent()) {
                        // Method generates a pair of keys using the RSA algorithm and stores it
                        // in their respective files
                        RSA.generateKey();
                    }


                    ObjectInputStream inputStream = null;

                    // Encrypt the string using the public key
                    inputStream = new ObjectInputStream(new FileInputStream(RSA.PUBLIC_KEY_FILE));
                    publicKey = (PublicKey) inputStream.readObject();

                    // Decrypt the cipher text using the private key.
                    inputStream = new ObjectInputStream(new FileInputStream(RSA.PRIVATE_KEY_FILE));
                    privateKey = (PrivateKey) inputStream.readObject();

                    ServerSocket sSocket = new ServerSocket(port);
                    
                    System.out.println("server started ...");
                    
                    while (true) {   // use infinte loop for accepting request        
                        Socket socket = sSocket.accept();
                        ChildServer cServer = new ChildServer(socket);
                        cServer.start();
                    }
                    
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(frCheckbox, "Cannot bind socket");
                }
               
            }
        });
        
        
        
        
        
        fr.setVisible(true);
    }//end main
}

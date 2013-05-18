/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import org.json.JSONException;

/**
 *
 * @author user
 */


public class GUI {

    	public static void main(String [] args)throws IOException, JSONException {
//		   JSONObject json = null;
//        try {
//            json = Client.reqGetPublicKey();
//            System.out.println(json.toString(4));
//
//            Client.key = (PublicKey) ObjectString.SToO((String) json.get("publickey"));
//            System.out.println(Client.key);
//
//            json = Client.reqLogin("admin", "admin");
//            System.out.println(json.toString(4));
//
//            String user_id = json.getString("user_id");
//            json = Client.reqGetTask(user_id);
//            System.out.println(json.toString(4));
//
//            json = Client.reqNegateTask(user_id, "63");
//            System.out.println(json.toString(4));
//
//            json = Client.reqGetTask(user_id);
//            System.out.println(json.toString(4));
//
//            System.out.println("closing socket");
//            Client.s.close();
//        } catch (IOException e) {
//            System.out.println("Cannot connecto to servero");
//        }            
    
//		Login login = new Login();
		Tugas tugas = new Tugas();
//                Client.reqGetTask(null)
        

        }//end main    

}//end class
    

        



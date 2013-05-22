package client;

import encryption.ObjectString;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends JFrame{
	
	private JButton btnLogin;
	private JTextField teksUsername;
	private JTextField teksPwd;
	
	
	public Login(){
		super("Login");
		setSize(800, 700);
//		pack();
//		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addLoginForm();
	}
        
        public String getUsername(){
            return teksUsername.getText();
        }
        
        public String getPwd(){
            return  teksPwd.getText();
        }
	
	public void addLoginForm(){		
		JPanel pUtama = new JPanel(null);		
		setContentPane(pUtama);
				
		JPanel p = new JPanel(new GridLayout(3,2));		
		p.setBounds(70, 70, 200, 100);
//		p.setBorder(BorderFactory.createLineBorder(Color.black));
		pUtama.add(p);
				
		JLabel Lusrn = new JLabel("Username");
				
		JLabel Lpwd = new JLabel("Password");		
		
		btnLogin = new JButton("Login");
//		btnLogin.setSize(50, 30);
//		btnLogin.setBounds(400, 50, 90, 30);
		btnLogin.addActionListener(new ActionListener() {
			
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                            try {                 
            //                    System.out.println(getUsername());
            //                    System.out.println(getPwd());

                                try {
                                    JSONObject json = Client.reqGetPublicKey();
                        //            System.out.println(json.toString(4));

                                    Client.key = (PublicKey) ObjectString.SToO((String) json.get("publickey"));
                        //            System.out.println(Client.key);

                        //            json = Client.reqLogin("admin", "admin");
                        //            System.out.println(json.toString(4));
                                    json = Client.reqLogin(getUsername(),getPwd());
                                    String user_id = json.getInt("user_id")+"";
                                    System.out.println("user id: "+user_id);
        //                            System.out.println("closing socket");
                                    Client.s.close();                                    
//                                    System.out.println("tes");
                                    
                                    Tugas tugas = new Tugas(getUsername(),getPwd());
                                    dispose();
                                    
                                } catch (IOException e) {
                                    System.out.println("Cannot connecto to servero");
                                    JOptionPane.showMessageDialog(new JFrame(), "login gagal");
                                }
        //                        System.out.println("connected to server....");
//                                    
                            } catch (JSONException ex) {
                                JOptionPane.showMessageDialog(new JFrame(), "login gagal");
        //                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                        
                        }//end act
                    });
		
		
		
		teksUsername = new JTextField();
//		teksUsername.setBounds(50, 50, 90, 30);
		
		teksPwd = new JTextField();
//		teksPwd.setBounds(500, 0, 90, 30);
		
		p.add(Lusrn);
		p.add(teksUsername);		
		p.add(Lpwd);
		p.add(teksPwd);
		p.add(btnLogin);

		setVisible(true);
	}
	
	
}

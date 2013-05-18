package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.json.JSONException;


public class Login extends JFrame{
	
	private JButton btnLogin;
	private JTextField teksUsername;
	private JTextField teksPwd;
	
	
	public Login(){
		super();
		setSize(800, 700);
//		pack();
//		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addLoginForm();
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
                    Tugas tugas = new Tugas();
                    dispose();
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
				
			}
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

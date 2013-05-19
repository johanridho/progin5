package client;

import encryption.ObjectString;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.PublicKey;
import org.json.JSONObject;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONException;


public class Tugas extends JFrame{

        private JSONArray arrayTask;
        private String[] tagsTgs;
        private String[] nameTgs;
        private int[] doneTgs;
        private int[] idTgs;
        private int[] katTgs;
        private int[] userTgs;            
        private String[] deadTgs;
    
	public Tugas() throws IOException, JSONException{
		super();
		setSize(1200, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setDefaultLookAndFeelDecorated(true);

                
                
/*-------------------------------------------------------------------------------------- */     

	   JSONObject json = null;
           JSONObject isi = null;
        try {
            json = Client.reqGetPublicKey();
//            System.out.println(json.toString(4));

            Client.key = (PublicKey) ObjectString.SToO((String) json.get("publickey"));
//            System.out.println(Client.key);

            json = Client.reqLogin("admin", "admin");
//            System.out.println(json.toString(4));

            String user_id = json.getString("user_id");
            json = Client.reqGetTask(user_id);
            isi = Client.reqGetTask(user_id);
//            System.out.println(json.toString(4));
            System.out.println("isigan---------------------------------------------------------------------------\n"
                                +isi.toString(4)+
                                "\nisigan2------------------------------------------------------------------------");         
            
            
            arrayTask = isi.getJSONArray("data");
            
            tagsTgs = new String[arrayTask.length()];
            nameTgs = new String[arrayTask.length()];
            doneTgs = new int[arrayTask.length()];
            idTgs = new int[arrayTask.length()];
            katTgs = new int[arrayTask.length()];
            userTgs = new int[arrayTask.length()];            
            deadTgs = new String[arrayTask.length()];
          
            for (int i=0; i<arrayTask.length();i++){
                JSONObject tgs = arrayTask.getJSONObject(i);
                nameTgs[i] = tgs.getString("name");
                tagsTgs[i] = tgs.getString("tags");
                doneTgs[i] = tgs.getInt("done");
                idTgs[i] = tgs.getInt("task_id");
                katTgs[i] = tgs.getInt("category_id");
                userTgs[i] = tgs.getInt("user_id");
                deadTgs[i] = tgs.getString("deadline");
                
//                System.out.println("----------------------------"+i+""+i+""+i);
//                System.out.println(deadTgs[i]);
//                System.out.println("----------------------------"+i+""+i+""+i);
            }//end for
           
            
            
//            json = Client.reqNegateTask(user_id, "63");
//            System.out.println(json.toString(4));

//            json = Client.reqGetTask(user_id);
//            System.out.println(json.toString(4));

            System.out.println("closing socket");
            Client.s.close();
        } catch (IOException e) {
            System.out.println("Cannot connecto to servero");
        }
		
/*-------------------------------------------------------------------------------------- */     
                
                
                //panel utama
		JPanel pUtama = new JPanel(null);
		setContentPane(pUtama);
                
                //panel penampung tugas2
                JPanel pUtama2 = new JPanel(new GridLayout(4,4));
                pUtama2.setBounds(20,50,1200,700);
                pUtama2.setBorder(BorderFactory.createLineBorder(Color.black));
                pUtama.add(pUtama2);
                 
                //button logout
		JButton logout = new JButton("Logout");
		logout.setBounds(10, 10, 100, 30);		
		pUtama.add(logout);
		logout.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Login login = new Login();
				dispose();
			}
		});
	
                //label daftar tugas
		JLabel daftarTugas = new JLabel("Daftar Tugas");
		daftarTugas.setBounds(350, 10, 100, 50);
		pUtama.add(daftarTugas);
		
                //loop bikin tugas
                for(int i=0;i<arrayTask.length();i++){
                
                    JPanel pTugas = new JPanel(new GridLayout(7,2));
    //		pTugas.setBounds(50,70,300,400);
                    pTugas.setBorder(BorderFactory.createLineBorder(Color.black));
                    pUtama2.add(pTugas);

                    JLabel judul = new JLabel("Judul :");
                    pTugas.add(judul);

                    JLabel judulTgs = new JLabel(nameTgs[i]);
                    pTugas.add(judulTgs);
                    
                    JLabel id = new JLabel("ID tugas :");
                    pTugas.add(id);
                    
                    JLabel LabelIdTgs = new JLabel(idTgs[i]+"");
                    pTugas.add(LabelIdTgs);
                    
                    JLabel userID = new JLabel("ID user :");
                    pTugas.add(userID);
                    
                    JLabel userIDTgs = new JLabel(userTgs[i]+"");
                    pTugas.add(userIDTgs);

                    JLabel kategori = new JLabel("Kategori :");
                    pTugas.add(kategori);		

                    JLabel kategoriTgs = new JLabel(""+katTgs[i]);
                    pTugas.add(kategoriTgs);

                    JLabel deadline = new JLabel("Deadline :");
                    pTugas.add(deadline);

                    JLabel deadlineTgs = new JLabel(deadTgs[i]);
                    pTugas.add(deadlineTgs);

                    JLabel tag = new JLabel("Tag :");
                    pTugas.add(tag);

                    JLabel tagTgs = new JLabel(tagsTgs[i]);
                    pTugas.add(tagTgs);

                    JLabel status = new JLabel("Status :");
                    pTugas.add(status);

                    JCheckBox statusTgs = new JCheckBox();
                    if(doneTgs[i]==1){
                        statusTgs.setSelected(true);
                    }else{
                        statusTgs.setSelected(false);
                    }
                    pTugas.add(statusTgs);		

    //		judulTgs.setBounds(200, arg1, arg2, arg3)
                
                }//end for

        
		setVisible(true);
		
	}//end ctor
	
	
	
	
	
}//end class

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Tugas extends JFrame{

	public Tugas(){
		super();
		setSize(800, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setDefaultLookAndFeelDecorated(true);
		
		JPanel pUtama = new JPanel(null);
		setContentPane(pUtama);
		
		JButton logout = new JButton("Logout");
		logout.setBounds(600, 600, 100, 30);		
		pUtama.add(logout);
		logout.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Login login = new Login();
				dispose();
			}
		});
		
		
		
		
		JLabel daftarTugas = new JLabel("Daftar Tugas");
		daftarTugas.setBounds(350, 10, 100, 50);
		pUtama.add(daftarTugas);
				
		JPanel pTugas = new JPanel(new GridLayout(5,2));
		pTugas.setBounds(50,70,300,400);
		pTugas.setBorder(BorderFactory.createLineBorder(Color.black));
		pUtama.add(pTugas);
		
		JLabel judul = new JLabel("Judul :");
		pTugas.add(judul);
		
		JLabel judulTgs = new JLabel("Tugas 1");
		pTugas.add(judulTgs);
				
		JLabel kategori = new JLabel("Kategori :");
		pTugas.add(kategori);		
		
		JLabel kategoriTgs = new JLabel("Progin");
		pTugas.add(kategoriTgs);
		
		JLabel deadline = new JLabel("Deadline :");
		pTugas.add(deadline);
		
		JLabel deadlineTgs = new JLabel("10-10-2013");
		pTugas.add(deadlineTgs);
		
		JLabel tag = new JLabel("Tag :");
		pTugas.add(tag);
		
		JLabel tagTgs = new JLabel("Terserah1, Terserah2");
		pTugas.add(tagTgs);
		
		JLabel status = new JLabel("Status :");
		pTugas.add(status);
		
		JCheckBox statusTgs = new JCheckBox();
		pTugas.add(statusTgs);		
		
//		judulTgs.setBounds(200, arg1, arg2, arg3)
		
		
		
		
		
		
		setVisible(true);
		
	}//end ctor
	
	
	
	
	
}//end class

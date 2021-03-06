package client;

import encryption.ObjectString;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONException;

public class Tugas extends JFrame {

    private long waktuSkrg;
    private long lastSync;
    private long lastSyncDB;
    private String user_id;
    private JSONArray arrayTask;
    private String[] tagsTgs;
    private String[] nameTgs;
    private int[] doneTgs;
    private int[] idTgs;
    private String[] katTgs;
    private String[] userTgs;
    private String[] deadTgs;
    private String[] attTgs;
    private String[] attTgs22;
    private String[] attTgs33;
    private String[] arrayAsignee;
    private JLabel[] judulTgs;
    private JLabel[] LabelIdTgs;
    private JLabel[] userIDTgs;
    private JLabel[] kategoriTgs;
    private JLabel[] deadlineTgs;
    private JLabel[] tagTgs;
    private JCheckBox[] statusTgs;

    public Tugas(String usergan, String pwdgan) throws IOException, JSONException {
        super("Daftar Tugas");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setDefaultLookAndFeelDecorated(true);            

//            System.out.println("asdasdasd");
//            System.out.println(getLastWaktu("1"));
//            System.out.println(getLastSyncFile("1"));
//            setLastSyncFile("1", "33");
//            System.out.println(getStatusFromFile("1", 18));

//            lastSyncDB = 2;




        /*
         * --------------------------------------------------------------------------------------
         */

        JSONObject json = null;

        try {
            json = Client.reqGetPublicKey();
//            System.out.println(json.toString(4));

            Client.key = (PublicKey) ObjectString.SToO((String) json.get("publickey"));
//            System.out.println(Client.key);

//            json = Client.reqLogin("admin", "admin");
//            System.out.println(json.toString(4));
            json = Client.reqLogin(usergan, pwdgan);

            user_id = json.getInt("user_id") + "";



            boolean isSukses = json.getBoolean("success");
//            System.out.println("----------------------------------------awwwwwwwwwwwww");
//            System.out.println(isSukses);
//            json = Client.reqGetTask(user_id);

            /*
             * -------------------------------bikin file
             * user-------------------------------
             */

            File fileUser = new File(user_id);
            if (fileUser.exists()) {
//                lastSync = getLastSyncFile(user_id);
                lastSync = System.currentTimeMillis();
                setLastSyncFile(user_id, lastSync + "");

//                sinkronkan(usergan, pwdgan);

                System.out.println("qqqqqqqqqqq");
//                System.out.println(lastSync);
//                System.out.println(lastSyncDB);
//                sinkronkan(user_id, pwdgan);

            } else {
                Writer writer = null;

                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(user_id), "utf-8"));
                    writer.write("0");
                } catch (IOException ex) {
                    // report
                } finally {
                    try {
                        writer.close();
                    } catch (Exception ex) {
                    }
                }
                lastSync = 0;
            }


            JSONObject isi = Client.reqGetTask(user_id);
//            System.out.println(json.toString(4));

            System.out.println("isigan---------------------------------------------------------------------------\n"
                    + isi.toString(4)
                    + "\nisigan2------------------------------------------------------------------------");
//            


            arrayTask = isi.getJSONArray("data");

            judulTgs = new JLabel[arrayTask.length()];
            LabelIdTgs = new JLabel[arrayTask.length()];
            userIDTgs = new JLabel[arrayTask.length()];
            kategoriTgs = new JLabel[arrayTask.length()];
            deadlineTgs = new JLabel[arrayTask.length()];
            tagTgs = new JLabel[arrayTask.length()];
            statusTgs = new JCheckBox[arrayTask.length()];


            tagsTgs = new String[arrayTask.length()];
            nameTgs = new String[arrayTask.length()];
            doneTgs = new int[arrayTask.length()];
            idTgs = new int[arrayTask.length()];
            katTgs = new String[arrayTask.length()];
            userTgs = new String[arrayTask.length()];
            deadTgs = new String[arrayTask.length()];
            attTgs = new String[arrayTask.length()];
            attTgs22 = new String[arrayTask.length()];
            attTgs33 = new String[arrayTask.length()];

            for (int i = 0; i < arrayTask.length(); i++) {
                JSONObject tgs = arrayTask.getJSONObject(i);
                JSONArray arraytemp = tgs.getJSONArray("attachment");
                String[] apake = new String[arraytemp.length()];
                for (int j = 0; j < arraytemp.length(); j++) {
                    JSONObject temp = arraytemp.getJSONObject(j);
                    apake[j] = temp.getString("filename");
                    if (attTgs[i] == null) {
                        attTgs[i] = apake[j];
                    } else if (attTgs22[i] == null) {
                        attTgs22[i] = apake[j];
                    } else if (attTgs33[i] == null) {
                        attTgs33[i] = apake[j];
                    }
                }


                JSONArray arraytemp2 = tgs.getJSONArray("tags");
                String[] apake2 = new String[arraytemp2.length()];
                tagsTgs[i] = "";
                for (int jj = 0; jj < arraytemp2.length(); jj++) {
                    JSONObject temp2 = arraytemp2.getJSONObject(jj);
                    apake2[jj] = temp2.getString("name");
                    tagsTgs[i] += apake2[jj] + ",";
                }

                JSONArray arraytemp3 = tgs.getJSONArray("assignee");
                String[] apake3 = new String[arraytemp3.length()];
                userTgs[i] = "";
                for (int jj = 0; jj < arraytemp3.length(); jj++) {
                    JSONObject temp3 = arraytemp3.getJSONObject(jj);
                    apake3[jj] = temp3.getString("name");
                    userTgs[i] += apake3[jj] + ",";
                }

                nameTgs[i] = tgs.getString("name");
//                tagsTgs[i] = tgs.get("tags")+"";
                doneTgs[i] = tgs.getInt("done");
                idTgs[i] = tgs.getInt("task_id");
                katTgs[i] = tgs.getString("category");
//                userTgs[i] = tgs.get("assignee")+"";
                deadTgs[i] = tgs.getString("deadline");
//                attTgs[i] = ;


//                System.out.println("----------------------------"+i+""+i+""+i);
//                System.out.println(deadTgs[i]);
//                System.out.println("----------------------------"+i+""+i+""+i);
            }//end for



//            isi = Client.reqNegateTask(user_id, "1"); //----------------------------------------------------------
//            System.out.println(json.toString(4));

//            json = Client.reqGetTask(user_id);
//            System.out.println(json.toString(4));

            System.out.println("closing socket");
            Client.s.close();
        } catch (IOException e) {
            System.out.println("Cannot connecto to servero");
        }



        /*
         * --------------------------------------------------------------------------------------
         */


        //panel utama
        JPanel pUtama = new JPanel(null);
        setContentPane(pUtama);

        //panel penampung tugas2
        JPanel pUtama2 = new JPanel(new GridLayout(4, 1));
        pUtama2.setBounds(20, 50, 1200, 700);
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

        JButton sync = new JButton("Synchronize");
        sync.setBounds(350, 10, 120, 30);
        pUtama.add(sync);
        final String pwdgan2 = pwdgan;
        sync.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    try {
                        // TODO Auto-generated method stub
//                        if(getLastSyncFile(user_id)>=getLastWaktu(user_id)){
//                            JOptionPane.showMessageDialog(new Frame(), "Semua tugas sudah sinkron");
//                        }else{
                        System.out.println("Syncronizing...");
                        sinkronkan(user_id, pwdgan2);
                        JOptionPane.showMessageDialog(new Frame(), "Sinkron berhasil");
//                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (JSONException ex) {
                    Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //label daftar tugas
//		JLabel daftarTugas = new JLabel("Daftar Tugas");
//		daftarTugas.setBounds(350, 10, 100, 50);
//		pUtama.add(daftarTugas);

        //loop bikin tugas
        for (int i = 0; i < arrayTask.length(); i++) {

            JPanel pTugas = new JPanel(new GridLayout(10, 2));
            //		pTugas.setBounds(50,70,300,400);
            pTugas.setBorder(BorderFactory.createLineBorder(Color.black));
            pUtama2.add(pTugas);

            JLabel judul = new JLabel("Judul :");
            pTugas.add(judul);

            judulTgs[i] = new JLabel(nameTgs[i]);
            pTugas.add(judulTgs[i]);

            JLabel id = new JLabel("Assignee :");
            pTugas.add(id);

            LabelIdTgs[i] = new JLabel(idTgs[i] + "");
            pTugas.add(LabelIdTgs[i]);

            JLabel userID = new JLabel("ID user :");
            pTugas.add(userID);

            userIDTgs[i] = new JLabel(userTgs[i] + "");
            pTugas.add(userIDTgs[i]);

            JLabel kategori = new JLabel("Kategori :");
            pTugas.add(kategori);

            kategoriTgs[i] = new JLabel("" + katTgs[i]);
            pTugas.add(kategoriTgs[i]);

            JLabel deadline = new JLabel("Deadline :");
            pTugas.add(deadline);

            deadlineTgs[i] = new JLabel(deadTgs[i]);
            pTugas.add(deadlineTgs[i]);

            JLabel tag = new JLabel("Tag :");
            pTugas.add(tag);

            tagTgs[i] = new JLabel(tagsTgs[i]);
            pTugas.add(tagTgs[i]);

            JLabel attach = new JLabel("Attachment :");
            pTugas.add(attach);

            JLabel attachTgs = new JLabel(attTgs[i]);
            attachTgs.setCursor(new Cursor(Cursor.HAND_CURSOR));
            final String sss = attTgs[i];
            attachTgs.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://localhost/IF3038-2013/src/attachment/" + sss));
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });


            pTugas.add(attachTgs);


            JLabel attachTgs22 = new JLabel(attTgs22[i]);
            attachTgs22.setCursor(new Cursor(Cursor.HAND_CURSOR));
            final String sss22 = attTgs22[i];
            attachTgs22.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://localhost/IF3038-2013/src/attachment/" + sss22));
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            pTugas.add(attachTgs22);

            JLabel attachTgs33 = new JLabel(attTgs33[i]);
            attachTgs33.setCursor(new Cursor(Cursor.HAND_CURSOR));
            final String sss33 = attTgs33[i];
            attachTgs33.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://localhost/IF3038-2013/src/attachment/" + sss33));
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            pTugas.add(attachTgs33);

            JLabel status = new JLabel("Status :");
            pTugas.add(status);

            statusTgs[i] = new JCheckBox();
            if (doneTgs[i] == 1) {
                statusTgs[i].setSelected(true);
            } else {
                statusTgs[i].setSelected(false);
            }

            final int iCheckbox = i;
            /*
             * ------------------------------------------checkbox ------------------------------------------
             */
            statusTgs[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (!Client.isConnected()) {
                            System.out.println("rekonek");
                            Client.reconnect();
                        }
                        System.out.println("konek sukses");
                        try {
//                                        JSONObject isi2 = Client.reqNegateTask(user_id, idTgs[iCheckbox]+"");
                            if (statusTgs[iCheckbox].equals("0")) {
                                JSONObject isi2 = Client.reqSetTask(user_id, idTgs[iCheckbox] + "", 1);
                            } else {
                                JSONObject isi2 = Client.reqSetTask(user_id, idTgs[iCheckbox] + "", 0);
                            }


                        } catch (JSONException ex) {
                            Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (doneTgs[iCheckbox] == 0) {
                            doneTgs[iCheckbox] = 1;
                        } else {
                            doneTgs[iCheckbox] = 0;
                        }
                        String ss = idTgs[iCheckbox] + "," + doneTgs[iCheckbox];
                        tambahIsiFile(user_id, ss);

                    } catch (IOException ex) {
                        System.out.println("konek gagal");
//                        Logger.getLogger(Tugas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            pTugas.add(statusTgs[i]);

            //		judulTgs.setBounds(200, arg1, arg2, arg3)

        }//end for


        setVisible(true);


    }//end ctor

    public void tambahIsiFile(String file, String stringTambah) {
        waktuSkrg = System.currentTimeMillis();

        int size = 0;

        try (BufferedReader br2 = new BufferedReader(new FileReader(file))) {
            while ((br2.readLine()) != null) {
                size++;
            }

        } catch (IOException e) {
//			e.printStackTrace();
        }

//                System.out.println(size);

        String[] arrayIsi = new String[size];
//                int iterasi = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int iterasi = 0; iterasi < size; iterasi++) {
                System.out.println(iterasi);
                arrayIsi[iterasi] = br.readLine();
                System.out.println("------");
                System.out.println(arrayIsi[iterasi]);
//                            iterasi++;
            }
        } catch (IOException e) {
//			e.printStackTrace();
        }

//               
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "utf-8"));
            writer.write("" + lastSync);
            writer.write("\n");
            for (int i = 1; i < arrayIsi.length; i++) {
                writer.write(arrayIsi[i]);
                writer.write("\n");
            }
            writer.write(stringTambah);
            writer.write("," + waktuSkrg);
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }

    }//end tambahisi

    public Long getLastWaktu(String file) {
        String lastLine = "";
        String temp;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((temp = br.readLine()) != null) {
                if (temp != null) {
                    lastLine = temp;
                } else {
                    lastLine = "0";
                }
            }

        } catch (IOException e) {
//			e.printStackTrace();
        }

        String[] arrayTemp = lastLine.split(",");

        return Long.parseLong(arrayTemp[2]);

    }//end getlast

    public Long getLastSyncFile(String file) {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                    if(br.readLine()==null){
//                        lastLine = "0";
//                    }else{
            lastLine = br.readLine();
//                    }

        } catch (IOException e) {
//			e.printStackTrace();
        }

        return Long.parseLong(lastLine);
    }

    public void setLastSyncFile(String file, String waktuSync) {
        int size = 0;

        try (BufferedReader br2 = new BufferedReader(new FileReader(file))) {
            while ((br2.readLine()) != null) {
                size++;
            }

        } catch (IOException e) {
//			e.printStackTrace();
        }

//                System.out.println(size);

        String[] arrayIsi = new String[size];
//                int iterasi = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (int iterasi = 0; iterasi < size; iterasi++) {
//                            System.out.println(iterasi);
                arrayIsi[iterasi] = br.readLine();
//                            System.out.println("------");
//                            System.out.println(arrayIsi[iterasi]);
//                            iterasi++;
            }
        } catch (IOException e) {
//			e.printStackTrace();
        }

//               
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "utf-8"));
            writer.write("" + waktuSync);
            writer.write("\n");
            for (int i = 1; i < arrayIsi.length; i++) {
                writer.write(arrayIsi[i]);
                writer.write("\n");
            }
        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    public int getStatusFromFile(String userIdFile, int idTgsFile) {
        String stat = "2";
        String temp;
        String[] arraystat;
        int a = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(userIdFile))) {
            while ((temp = br.readLine()) != null) {
//                        System.out.println(temp);
                if (temp != null && a >= 1) {
//                            System.out.println("tes");
                    arraystat = temp.split(",");
                    if (arraystat[0].equals(idTgsFile + "")) {
                        stat = arraystat[1];
                    }

                } else {
                    stat = "2";
                }
                a++;
            }

        } catch (IOException e) {
//			e.printStackTrace();
        }


        return Integer.parseInt(stat);
    }

    public void sinkronkan(String file, String pwdgan) throws JSONException, IOException {
        System.out.println("synccoy");

        if (!Client.isConnected()) {
//                System.out.println("ttttttttt");
            System.out.println("sync gagal, rekonek");
            Client.reconnect();

        }
//            else{
//            if(lastSyncDB < getLastSyncFile(user_id)){
        System.out.println("wwwwwwwwwwwww");
//                lastSyncDB = System.currentTimeMillis();
        lastSync = System.currentTimeMillis();
//                System.out.println(lastSyncDB);
//                lastSync=lastSyncDB;
//                System.out.println(lastSyncDB);
        System.out.println(lastSync);
        setLastSyncFile(file, lastSync + "");

        JSONObject json = null;

        try {
            json = Client.reqGetPublicKey();

            Client.key = (PublicKey) ObjectString.SToO((String) json.get("publickey"));

            json = Client.reqLogin(user_id, pwdgan);

            JSONObject isi = Client.reqGetTask(user_id);

//                    System.out.println("isigan---------------------------------------------------------------------------\n"
//                                        +isi.toString(4)+
//                                        "\nisigan2------------------------------------------------------------------------");         

            JSONArray arrayTask2 = isi.getJSONArray("data");

            //            JSONObject aisi = Client.reqNegateTask(user_id, "1"); //----------------------------------------------------------           
            String[] tagsTgs2 = new String[arrayTask2.length()];
            String[] nameTgs2 = new String[arrayTask2.length()];
            String[] katTgs2 = new String[arrayTask2.length()];
            String[] userTgs2 = new String[arrayTask2.length()];
            String[] deadTgs2 = new String[arrayTask2.length()];
            int[] doneTgs2 = new int[arrayTask2.length()];
            int[] idTgs2 = new int[arrayTask2.length()];

            for (int i = 0; i < arrayTask2.length(); i++) {
                JSONObject tgs = arrayTask2.getJSONObject(i);
                doneTgs2[i] = tgs.getInt("done");
                idTgs2[i] = tgs.getInt("task_id");
//                        System.out.println("bbbbbbbbbbbbbbbb");
//                        System.out.println(doneTgs2[i]);
                if (doneTgs2[i] == getStatusFromFile(user_id, idTgs2[i]) || getStatusFromFile(user_id, idTgs2[i]) == 2) {
                } else {
                    if (doneTgs2[i] == 0) {
                        JSONObject tempJson = Client.reqSetTask(user_id, idTgs2[i] + "", 1);
                    } else {
                        JSONObject tempJson = Client.reqSetTask(user_id, idTgs2[i] + "", 0);
                    }

                }
            }//end for     
//                        
            json = Client.reqGetPublicKey();

            Client.key = (PublicKey) ObjectString.SToO((String) json.get("publickey"));

            json = Client.reqLogin(user_id, pwdgan);

            isi = Client.reqGetTask(user_id);

            arrayTask2 = isi.getJSONArray("data");
//                    
            for (int i = 0; i < arrayTask2.length(); i++) {
                JSONObject tgs = arrayTask2.getJSONObject(i);
                doneTgs2[i] = tgs.getInt("done");
                if (doneTgs2[i] == 1) {
                    statusTgs[i].setSelected(true);
                } else {
                    statusTgs[i].setSelected(false);
                }
            }//end for

            Client.s.close();
        } catch (IOException e) {
            System.out.println("Cannot connecto to servero");
        }

//                judulTgs[0].setVisible(false);
//               judulTgs[0].setText("asd");        
//               judulTgs[0].setVisible(true);
//               setVisible(true);
//                 judulTgs[0].setText("asd");     
        System.out.println("sync sukses");
//            }else{ //lastSync >= getLastSyncFile(user_id)

//            }
//            }//end connect            
    }//end sinkronkan

    public void browseURL(String s) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(s));
    }
}//end class


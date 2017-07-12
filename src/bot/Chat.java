/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Oscar
 */
public class Chat extends javax.swing.JFrame {

    /**
     * Creates new form Cbhat
     */
    public Chat() {
        initComponents();
        this.setSize(660, 460);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        userMsj = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(241, 239, 239));
        jPanel4.setLayout(null);

        chatArea.setEditable(false);
        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(213, 30, 400, 320);

        userMsj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userMsjActionPerformed(evt);
            }
        });
        jPanel4.add(userMsj);
        userMsj.setBounds(210, 370, 360, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/send.gif"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel1);
        jLabel1.setBounds(580, 360, 40, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userMsjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userMsjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userMsjActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        chat = chatArea.getText();

        if (userMsj.getText().equals("")) {
            System.out.println("Empty message!");
        } else {
            chatArea.setText(chat.concat(userMsj.getText() + "\n"));
            String response = talkToMe(userMsj.getText());
            userMsj.setText("");
            chat = chatArea.getText();

            //puntos y respuesta
            hiloPuntos = new Hilo(chatArea, response);
            pts = new Thread(hiloPuntos);
            pts.start();

        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private String talkToMe(String user) {
        String response = "";
        user = user.toLowerCase();
        Random r = new Random();
        int num = r.nextInt(3) + 1;

        //Greetings
        if (user.equals("hi") || user.contains("hello") || user.contains("hola")) {
            response = "Hello";
        }

        //presentation
        if (user.contains("my name is") || user.contains("i am") || user.contains("i'm")) {
            if (user.contains("my name is")) {
                userName = user.substring(10);
            } else if (user.contains("i am")) {
                userName = user.substring(4);
            } else if (user.contains("i'm")) {
                userName = user.substring(3);
            }

            response = "Nice to meet you" + userName;
        }

        //my name is korra
        if (user.contains("who") && user.contains("you")) {
            response = "My name is Korra";
        }

        //calculate distance
        Chat http = new Chat();
        if (user.contains("distance") && user.contains("and") && user.contains("between")) {
            System.out.println("Testing 1 - Send Http GET request");
            String city1 = captureCity1(user);
            String city2 = captureCity2(user);
            System.out.println("city1: " + city1 + "    city2: " + city2);
            String km = "";
            try {
                km = http.calculateDistance(city1, city2);
            } catch (Exception ex) {
                Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
            }

            city1 = city1.substring(0, 1).toUpperCase() + city1.substring(1);
            city2 = city2.substring(0, 1).toUpperCase() + city2.substring(1);

            response = "The distance between " + city1 + " and " + city2 + " is " + km;
        }

        return response;
    }

    private String captureCity1(String user) {
        String city1 = "";
        int cityStartID = 0, cityFinishID = 0;
        System.out.println("String length: " + user.length());
        
        for (int i = 0; i < user.length() - 1; i++) {
            if (user.charAt(i) == 't' && user.charAt(i + 1) == 'w' && user.charAt(i + 2) == 'e' && user.charAt(i + 3) == 'e' && user.charAt(i + 4) == 'n') {
                cityStartID = i + 6;
            }
            else if (user.charAt(i) == 'a' && user.charAt(i + 1) == 'n' && user.charAt(i + 2) == 'd' && (i+2)< user.length()-2) {
                cityFinishID = i - 1;
            }
        }

        city1 = user.substring(cityStartID, cityFinishID);
        return city1;
    }

    private String captureCity2(String user) {
        String city2 = "";
        int cityStartID = 0, cityFinishID = user.length();
        for (int i = 0; i < user.length() - 1; i++) {
            if (user.charAt(i) == 'a' && user.charAt(i + 1) == 'n' && user.charAt(i + 2) == 'd' && user.charAt(i + 3) == ' ') {
                cityStartID = i + 4;
            }
        }
        city2 = user.substring(cityStartID, cityFinishID);
        return city2;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Chat().setVisible(true);
            }
        });

    }

    public String calculateDistance(String city1, String city2) throws Exception {

        String origin = city1.replace(' ', '+'), destination = city2.replace(' ', '+');
        String YOUR_API_KEY = "AIzaSyCm5bDkt7Ohgm4l5VhBstydD-fGSmcr6oo";
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin="
                + origin + "&destination=" + destination + "&key=" + YOUR_API_KEY;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        String routeDistance = "";
        try {
            String jsonStringDistance = response.toString();
            System.out.println(jsonStringDistance);
            JSONObject jsonObjDistance = new JSONObject(jsonStringDistance);
            routeDistance = jsonObjDistance.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").get("text").toString();
            System.out.println(routeDistance);
        } catch (Exception ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return routeDistance;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField userMsj;
    // End of variables declaration//GEN-END:variables
    String chat = "";
    String userName = "";
    public Hilo hiloPuntos;
    Thread pts;
    private final String USER_AGENT = "Mozilla/5.0";
    private String distance = "";

}

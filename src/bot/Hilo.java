/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class Hilo implements Runnable{
    
    private JTextArea a;
    private String response;
    
    public Hilo(JTextArea a, String response){
        this.a = a;
        this.response = response;
    }
    
    @Override
    public void run(){
        try {
            String s = a.getText();
            String newS;
            for(int i = 0; i < 3; i++){
                Thread.sleep(500);
                s = a.getText();
                a.setText(s + ". ");
            }
                            Thread.sleep(500);

            s = a.getText();
            newS = s.substring(0, s.length()-6);
            System.out.println(newS);
            
            s = newS;
            a.setText(s.concat(response + "\n"));

            
           
        } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            
            
        }
    }
}

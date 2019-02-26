/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class Hack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (int i = 30000; i < 32000; i++) {
                 Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome https://db.raidforums.com/z/down.php?id="+i});
                 Thread.sleep(10);
            }
           
        } catch (Exception ex) {
            Logger.getLogger(Hack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

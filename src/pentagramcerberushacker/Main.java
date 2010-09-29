/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pentagramcerberushacker;

import java.io.DataInputStream;
import java.io.IOException;
import pl.pkarpik.common.ParserStronyWWW;
import pl.pkarpik.pentagramcerberushacker.engine.ConnectionParametr;
import pl.pkarpik.pentagramcerberushacker.engine.MainStatus;
import pl.pkarpik.pentagramcerberushacker.engine.ByteTransfer;
import pl.pkarpik.pentagramcerberushacker.gui.NewJFrame;

/**
 *
 * @author Pio
 */
public class Main {


    //public static final String ADRES_STRONY="http://192.168.1.100"; 
    public static final String ADRES_STRONY = "http://192.168.1.1/userRpm/StatusRpm.htm";

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        final ConnectionParametr p = new ConnectionParametr();
        p.setHttpAddres(ADRES_STRONY);
        p.setUser("admin");
        p.setPassword("admin");
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewJFrame(p).setVisible(true);
            }
        });

        MainStatus ms = new MainStatus(p);

        //while (true) 
        {
            ByteTransfer stats = ms.getByteTransferPerSecond(1);
            Double recive = stats.getReciveBytes().doubleValue();
            Double send = stats.getSendBytes().doubleValue();
            recive /= 1024;
            send /= 1024;
//                                recive/=1024;
//                                send/=1024;

            System.out.println("Recive (KB):" + recive + " Send(KB):" + send);
        }

    }
}

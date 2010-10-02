/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pentagramcerberushacker;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static Logger logger = Logger.getLogger(Main.class.getName());
    private static final String ADRES_STRONY_PROP = "ADRES_STRONY";
    private static final String LOGIN_PROP = "LOGIN";
    private static final String PASSWORD_PROP = "PASSWORD";

    public void init() {
//        try {
//            InputStream in = getClass().getResourceAsStream("application.properties");
//            System.out.println("in = " + in);
//            Properties prop = new Properties();
//            prop.load(in);
//            System.out.println("prop.getProperty = " + prop.getProperty("LOGIN"));
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }


        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("application.properties");

            properties.load(fileInputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }


        logger.info("adres: " + properties.getProperty(ADRES_STRONY_PROP));
        logger.info("haslo: " + properties.getProperty(PASSWORD_PROP));
        logger.info("login: " + properties.getProperty(LOGIN_PROP));



        final ConnectionParametr p = new ConnectionParametr();
        p.setHttpAddres(properties.getProperty(ADRES_STRONY_PROP));
        p.setUser(properties.getProperty(LOGIN_PROP));
        p.setPassword(properties.getProperty(PASSWORD_PROP));

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NewJFrame(p).setVisible(true);
            }
        });

        MainStatus ms = new MainStatus(p);

        int i = 5;
        //while (i-->0)
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

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        new Main().init();

    }
}

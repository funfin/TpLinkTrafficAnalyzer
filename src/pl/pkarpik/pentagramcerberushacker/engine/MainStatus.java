/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pkarpik.pentagramcerberushacker.engine;

import java.io.BufferedReader;
import java.io.IOException;
import pl.pkarpik.common.ParserStronyWWW;

/**
 *
 * @author Pio
 */
public class MainStatus {

    private ParserStronyWWW parser;

    public MainStatus(ConnectionParametr p) {
        parser = new ParserStronyWWW(p.getHttpAddres(), p.getUser(), p.getPassword());
    }

    public ByteTransfer getByteTransfer() {
        ByteTransfer stats = null;
        String line = null;
        BufferedReader in = new BufferedReader(parser.getPage());
        try {
            while ((line = in.readLine()) != null) {
                if (line.contains("var statistList = new Array(")) {
                    line = in.readLine();

                    String reciveStr = "";
                    String sendStr = "";
                    reciveStr = line.substring(0, line.indexOf(','));

                    //System.out.println(reciveStr);
                    line = line.substring(line.indexOf(',') + 2, line.length());

                    sendStr = line.substring(0, line.indexOf(','));

                    //System.out.println(sendStr);
                    //System.out.println(line.indexOf(','));
                    stats = new ByteTransfer(Long.parseLong(reciveStr), Long.parseLong(sendStr));
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return stats;
    }

    public ByteTransfer getByteTransferPerSecond() {
        return getByteTransferPerSecond(1);
    }

    public ByteTransfer getByteTransferPerSecond(int deltaTime) {
        ByteTransfer start = getByteTransfer();
        try {
            Thread.sleep(1000 * deltaTime);
        } catch (InterruptedException e) {
            System.out.println("Couldn't fall asleep " + e);
        }
        ByteTransfer end = getByteTransfer();
        ByteTransfer statsPerSec = new ByteTransfer((end.getReciveBytes() - start.getReciveBytes()) / deltaTime,
                (end.getSendBytes() - start.getSendBytes()) / deltaTime);
        return statsPerSec;
    }
    
}

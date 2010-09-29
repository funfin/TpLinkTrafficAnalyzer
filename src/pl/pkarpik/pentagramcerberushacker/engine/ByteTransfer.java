/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pkarpik.pentagramcerberushacker.engine;

/**
 *
 * @author Pio
 */
public class ByteTransfer {

    private Long reciveBytes;
    private Long sendBytes;

    
    public ByteTransfer(Long reciveBytes, Long sendBytes){
        this.reciveBytes=reciveBytes;
        this.sendBytes=sendBytes;
    }
    
    public Long getReciveBytes() {
        return reciveBytes;
    }

    public void setReciveBytes(Long reciveBytes) {
        this.reciveBytes = reciveBytes;
    }

    public Long getSendBytes() {
        return sendBytes;
    }

    public void setSendBytes(Long sendBytes) {
        this.sendBytes = sendBytes;
    }
    
    public Long getBytesTransfered(){
        return getReciveBytes()+getSendBytes();
    }

}

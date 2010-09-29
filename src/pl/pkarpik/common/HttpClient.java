package pl.pkarpik.common;

/**
 * @author pkarpik
 *
 */



import java.net.*;
import java.io.*;

/**
 * 
 * @author pkarpik
 *
 */
public abstract class HttpClient 
{
	private static String adres=null;
		
	
	/**
	 * Metoda zwraca DataInputStream pliku dostepnego pod okreslonym adresem http   
	 * @return DataInputStream plik
	 */
	public static InputStreamReader getFile(String adres)
	{
		HttpClient.adres=adres;
		return getFile();
	}
	/**
	 * Metoda zwraca DataInputStream pliku  
	 * @return DataInputStream plik
	 */
    private static InputStreamReader getFile()
    {
    	 checkUsage(adres);
    	 try 
    	 {

      //  Authenticator.setDefault (new MyAuthenticator("admin","admin2"));  //set Authenticator for popup login
    		 URL url=new URL(adres);
                 InputStreamReader in =new InputStreamReader((InputStream)url.getContent());
    		 //in.close();
    		 return in;
         } 
    	 catch(MalformedURLException mue)
         { 	
    		 System.out.println(adres+"is an invalid URL:"+mue);
    		 return null;
         } 
    	 catch(IOException ioe)
    	 {
    		 System.out.println("IOException: "+ioe);
    		 return null;
         }
    	 
    }

    private static void checkUsage(String adres)
    {
        if (adres==null) 
           	System.out.println("podaj adres internetowy do pliku");
    }
    
   
}

 class MyAuthenticator extends Authenticator {  
        String uname;
        String pass;
        
        public MyAuthenticator(String uname, String pass) {
            super();
            this.uname = uname;
            this.pass = pass;
        }
        
        public String getUname() {
            return uname;
        }
        
        public void setUname(String uname) {
            this.uname = uname;
        }
             
        public String getPass() {
            return pass;
        }
        
        public void setPass(String pass) {
            this.pass = pass;
        }
        
       protected PasswordAuthentication getPasswordAuthentication() {
          String user = getUname();
          String pw = getPass();
          return (new PasswordAuthentication (user, pw.toCharArray()));
       }
    }  // end of MyAuthenticator class
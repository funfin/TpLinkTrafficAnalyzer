package pl.pkarpik.common;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ParserStronyWWW {
	private final String ADRES_STRONY; 
	private String plik="tmp.txt";
        
        private int currentLine=0;
        
        MyAuthenticator myAuth;
	
	public ParserStronyWWW(String adresStrony)
	{
		this.ADRES_STRONY=adresStrony;
		
	}
        
    public ParserStronyWWW(String adresStrony, String user, String pass)
	{
		this.ADRES_STRONY=adresStrony;
                this.myAuth = new MyAuthenticator(user,pass);
                Authenticator.setDefault (myAuth);  //set Authenticator for popup login
		
	}
	
	public InputStreamReader getPage()
	{
		 return HttpClient.getFile(ADRES_STRONY);
	}
	
         public String getLine(int lineNumber)
	{
		String line=null;
		BufferedReader in=new BufferedReader(getPage());
                        
		int i=0;
		try {
			while ((line = in.readLine()) != null) 
				if(lineNumber==i++){
					return line;
                                }
                        return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
         
         public String getNextLine(){
             currentLine=currentLine+1;
             return getLine(currentLine-1);
         }
        
	public String getLineWithText(String text)
	{
		String line=null;
		BufferedReader in=new BufferedReader(getPage());
		
		try {
			while ((line = in.readLine()) != null){ 
                                currentLine=currentLine+1;
                        	if (line.contains(text))
					return line;
                                currentLine=currentLine+1;
                        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public String getLineWithText(String text, long ktoreWystapienie)
	{
		String line=null;
		BufferedReader in=new BufferedReader(getPage());
		try{
			for(long i=0;i<ktoreWystapienie-1;i++)
				
				while ((line = in.readLine()) != null) 
					if (line.contains(text))
						break;
			// TODO Auto-generated catch block
			while ((line = in.readLine()) != null) 
				if (line.contains(text))
					return line; 
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * getNextLineAfterText(String text, long ileZa, long ktoreWystapienieTextu)
	 * 
	 * zwraca ktoras z koleii lini wystepujaca po lini z danym tekstem 
	 * @param getNextLineAfterText(String text, long ileZa, long ktoreWystapienieTextu)
	 * @return String
	 */
	public String getNextLineAfterText(String text, long ileZa, long ktoreWystapienieTextu)
	{
		String line=null;
		BufferedReader in=new BufferedReader(getPage());
		try{
			for(long i=0;i<ktoreWystapienieTextu;i++)
				
				while ((line = in.readLine()) != null) 
					if (line.contains(text))
						break;
			// TODO Auto-generated catch block
			for(long i=0;i<ileZa;i++)
				if((line = in.readLine()) == null)
					return null;
			return line; 
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
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
   }
}

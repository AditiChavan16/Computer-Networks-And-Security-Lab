import java.io.*;  
import java.net.*;  
public class client {  
public static void main(String[] args) {  
try{      
Socket s=new Socket("10.10.13.94",6666);  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
dout.writeUTF("Hello Server");  
dout.flush();  
dout.close();  
s.close();  
}catch(Exception e){System.out.println(e);}  
}  
}  

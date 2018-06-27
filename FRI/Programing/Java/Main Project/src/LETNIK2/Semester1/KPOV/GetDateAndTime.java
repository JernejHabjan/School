package LETNIK2.Semester1.KPOV;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.util.Date;
public class GetDateAndTime {

    public static void main (String[] args) throws Exception{

    String hostName = "ntp1.arnes.si";
    int portNumber = 37;

    try {
        Socket echoSocket = new Socket(hostName, portNumber);
        InputStream in = echoSocket.getInputStream();
        byte[] buf = new byte[4];
        in.read(buf);

        ByteBuffer b = ByteBuffer.wrap(buf);
        long t;
        t = b.getInt();
        t= t & 0b0000000000000000000000000000000011111111111111111111111111111111l;
        t = t - 2208988800l;
        System.out.println(t);
        Date d = new Date(t*1000);
        System.out.println(d);
        echoSocket.close();

        }
        catch(Exception ex){
            System.out.println();
        }
    }

}
package com.JHY;

/**
 * Created by JiHongyu on 2016/1/26.
 */
import java.net.*;
import java.io.*;
public class FileSender {
    final int BUFFERSIZE = 1024;
    final int port = 12345;
    byte[] buffer = new byte[BUFFERSIZE];
    String filepath;
    String destinationIP;
    public FileSender(String filepath, String ip){
        this.filepath = filepath;
        this.destinationIP = ip;
    }

    public boolean doing() throws IOException{
        File file = new File(filepath);
        if(!file.exists()) return false;

        Socket client = new Socket(destinationIP,port);
        FileInputStream finput = new FileInputStream(file);

        OutputStream sout = client.getOutputStream();
        InputStream  sin  = client.getInputStream();

        //send file name
        String filename = file.getName();
        sout.write(filename.getBytes());
        sin.read(buffer,0,1);
        //send file length
        long filelength = file.length();
        sout.write(Long.toString(filelength).getBytes());
        sin.read(buffer,0,1);

        //send file data
        int sendsize = 0;
        int currentsize = 0;
        try {
            while((currentsize = finput.read(buffer,sendsize,BUFFERSIZE))!=-1)
            {
                sout.write(buffer,0,currentsize);
            }
        } catch (IOException e) {
            sout.close();
            finput.close();
            e.printStackTrace();
            return false;
        }
        sout.flush();
        sout.close();
        finput.close();
        return true;
    }

}

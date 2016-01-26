package com.JHY;
/**
 * Created by JiHongyu on 2016/1/26.
 */
import java.io.*;
import java.net.*;

public class FileSendJob implements Job {
    final int BUFFERSIZE = 1024;
    Socket socket;
    String filepath;
    byte[] buffer = new byte[BUFFERSIZE];
    public FileSendJob(Socket s,String filepath){
        this.socket = s;
        this.filepath = filepath;
    }

    @Override
    public boolean doing() throws IOException {
        int sendsize = 0;
        int currentsize = 0;
        FileInputStream file = new FileInputStream(filepath);
        OutputStream out = socket.getOutputStream();
        try {
            while((currentsize = file.read(buffer,sendsize,BUFFERSIZE))!=-1)
            {
                out.write(buffer,0,currentsize);
            }
        } catch (IOException e) {
            out.close();
            file.close();
            e.printStackTrace();
            return false;
        }
        out.flush();
        out.close();
        file.close();
        return true;
    }
}

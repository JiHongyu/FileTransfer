package com.JHY;

/**
 * Created by JiHongyu on 2016/1/26.
 */
import java.net.*;
import java.io.*;
import java.util.LongSummaryStatistics;

public class FileReceiver {

    final int BUFFERSIZE = 1024;
    final int port = 12345;
    byte[] buffer = new byte[BUFFERSIZE];

    String fileDir;
    public FileReceiver(String fileDir){
        this.fileDir = fileDir;
    }
    public void doing() throws IOException {
        ServerSocket server = new ServerSocket(port);

        while (true){
            Socket remote = server.accept();
            receiving(remote);
        }
    }
    private boolean receiving(Socket s) throws IOException {

        System.out.println("A client connecting...");
        OutputStream sout = s.getOutputStream();
        InputStream  sin  = s.getInputStream();

        int datalength = 0;
        //receive file name
        datalength =  sin.read(buffer);
        String filename = new String(buffer,0,datalength);
        System.out.println("File name: " + filename);
        datalength =  sin.read(buffer);
        sout.write(1);
        //receive file length
        datalength = sin.read(buffer);
        long fileLength = Long.parseLong(new String(buffer,0,datalength));
        System.out.println("File length: " + fileLength);
        sout.write(1);

        //create new file
        File newfile = new File(fileDir+filename);
        if(newfile.exists()) newfile.delete();
        newfile.createNewFile();

        //receive file data
        FileOutputStream fout = new FileOutputStream(newfile);
        datalength = 0;
        while ((datalength = sin.read(buffer,0,BUFFERSIZE))!=-1){
            fout.write(buffer,0,datalength);
        }

        sin.close();
        sout.close();
        fout.flush();
        fout.close();
        return true;
    }
}

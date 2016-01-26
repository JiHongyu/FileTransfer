package com.JHY;

/**
 * Created by JiHongyu on 2016/1/26.
 */
import java.net.*;
import java.io.*;
public class FileReceiveJob implements Job {
    ServerSocket server;
    String fileDir;
    String filename;
    public FileReceiveJob(ServerSocket server,String fileDir,String filename){
        this.server = server;
        this.fileDir = fileDir;
        this.filename = filename;
    }

    @Override
    public boolean doing() throws IOException {
        File newfile = new File(fileDir+filename);
        if(newfile.exists()) newfile.delete();
        newfile.createNewFile();

        return false;

    }
}

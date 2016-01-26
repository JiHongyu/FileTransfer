package com.JHY;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Hi.display();
        System.out.println(System.getProperty("user.dir"));
        if(args.length != 1) return;
        if(args[0].equals("cli")){
            FileSender sender = new FileSender("./test/test","127.0.0.1");
            sender.doing();
        }else if(args[0].equals("ser")){
            FileReceiver receiver = new FileReceiver("./test/1/");
            receiver.doing();
        }else{
            return;
        }
    }
}

class Hi{
    public static void display(){
        System.out.println("hello");
    }
}
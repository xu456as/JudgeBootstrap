package me.fetonxu;



import me.fetonxu.daemon.server.DaemonServer;

public class ServerMain {
    public static void main(String[] args){

        DaemonServer daemonServer = new DaemonServer(8999, 4);
        daemonServer.start();
    }
}

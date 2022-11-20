package edu.ucam.comunicaciones;

import edu.ucam.principal.User;

import java.io.*;
import java.net.*;

public class Server_comandos {

    public void ejecutar() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            while(true) {
                System.out.println("Esperando conexión...");
                Socket socket = serverSocket.accept();
                System.out.println("Conexión recibida... " + socket.getRemoteSocketAddress());


                ServerThread serverThread = new ServerThread(socket);

                serverThread.start();

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        User u=new User("admin","admin");
        (new Server_comandos()).ejecutar();

    }

}

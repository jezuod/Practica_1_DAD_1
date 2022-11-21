package edu.ucam.comunicaciones;

import edu.ucam.principal.User;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Server_comandos {
    private static ArrayList<User> User_conectados=new ArrayList<User>();
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
        User u1=new User("prueba","prueba");
        (new Server_comandos()).ejecutar();

    }

}

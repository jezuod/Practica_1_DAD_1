package edu.ucam.comunicaciones;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {

            System.out.println("Hilo lanzado...");
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));


            String lineaLeida = "";

            while(!(lineaLeida=br.readLine()).equalsIgnoreCase("QUIT")) {
                pw.println("Server :"+lineaLeida);
                pw.flush();
            }

            System.out.println("Finalizado");


        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}

package edu.ucam.comunicaciones;

import edu.ucam.comando.comando_cliente;

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
            String palabras[];
            while(!(lineaLeida=br.readLine()).equalsIgnoreCase("QUIT")) {
                palabras=lineaLeida.split("\\W+");/*
                The \\W+ will match all non-alphabetic characters occurring one or more times. So there is no need to replace

                */
                comando_cliente comand=new comando_cliente(palabras[0],palabras[2],palabras[1]);
                pw.println("Server :"+comand.getNumber()+" "+comand.getComando()+" "+ comand.getInformacion_adicional());
                pw.flush();
            }

            System.out.println("Finalizado");


        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}

package edu.ucam.comunicaciones;

import edu.ucam.comando.comando_cliente;
import edu.ucam.comando.comando_servidor;

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
                comando_cliente comand_cliente=new comando_cliente(palabras[0],palabras[2],palabras[1]);
                comand_cliente.ejecutar();
                comando_servidor c_server=comand_cliente.getComand_server();
                //comando_servidor comand_server;
                pw.println("SERVER : "+c_server.getTipo_respuesta()+" "+c_server.getNumber()+" "+c_server.getCod_respuesta()+" "+c_server.getInformacion_adicional());
                //pw.println("Server :"+comand_cliente.getNumber()+" "+comand_cliente.getComando()+" "+ comand_cliente.getInformacion_adicional());
                pw.flush();


            }

            System.out.println("Finalizado");


        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}

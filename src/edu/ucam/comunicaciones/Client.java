package edu.ucam.comunicaciones;
import edu.ucam.comando.comando_cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private int num; //num envios comandos
    public void ejecutar()
    {

        try {
            num=1;
            System.out.println("Lanzando conexión ....");
            Socket socket =  new Socket("127.0.0.1",5000);
            System.out.println("Conexión establecida ..."+ socket.getRemoteSocketAddress());

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner keyboard= new Scanner(System.in);
            String line="";
            String palabras[];
            while(!(line= keyboard.nextLine()).equalsIgnoreCase("SALIR")){
                palabras=line.split("\\W+");/*
                The \\W+ will match all non-alphabetic characters occurring one or more times. So there is no need to replace

                */
                comando_cliente comand=new comando_cliente(Integer.toString(num),palabras[1],palabras[0]);
                pw.println(comand.getNumber()+" "+comand.getComando()+" "+ comand.getInformacion_adicional());
                num++;
                pw.flush();

                System.out.println("\t"+ br.readLine());
            }

            pw.println(line);
            pw.flush();

            System.out.println("Finalizado");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void main(String[] args) {
        (new Client()).ejecutar();


    }


}

package edu.ucam.comunicaciones;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void ejecutar()
    {

        try {
            System.out.println("Lanzando conexión ....");
            Socket socket =  new Socket("127.0.0.1",5000);
            System.out.println("Conexión establecida ..."+ socket.getRemoteSocketAddress());

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner keyboard= new Scanner(System.in);
            String line="";

            while(!(line= keyboard.nextLine()).equalsIgnoreCase("SALIR")){
                pw.println(line);
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

}

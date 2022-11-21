package edu.ucam.comunicaciones;

import edu.ucam.comando.comando_cliente;
import edu.ucam.comando.comando_servidor;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    private int user; //usuario metido bien 1  no 0
    private int sesion;//sesion activa 1  no 0
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    private comando_cliente Comando_cliente_almacenado;
    private comando_cliente Comando_usuario;

    public comando_cliente getComando_usuario() {
        return Comando_usuario;
    }

    public void setComando_usuario(comando_cliente comando_usuario) {
        Comando_usuario = comando_usuario;
    }

    public comando_cliente getComando_cliente_almacenado() {
        return Comando_cliente_almacenado;
    }

    public void setComando_cliente_almacenado(comando_cliente comando_cliente_almacenado) {
        Comando_cliente_almacenado = comando_cliente_almacenado;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getSesion() {
        return sesion;
    }

    public void setSesion(int sesion) {
        this.sesion = sesion;
    }

    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {
            setUser(0);setSesion(0);//arrancamos sin sesion ni user
            System.out.println("Hilo lanzado...");
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));


            String lineaLeida = "";
            String palabras[];
            while(!(lineaLeida=br.readLine()).equalsIgnoreCase("SALIR")) {
                palabras=lineaLeida.split("\\W+");/*
                The \\W+ will match all non-alphabetic characters occurring one or more times. So there is no need to replace

                */
                comando_cliente comand_cliente=new comando_cliente(palabras[0],palabras[2],palabras[1]);
                //si el comando anterior era de tipo USER nos interesa saber el usuario introducido, entonces se lo pasamos al siguiente comando que suponemos que es PASS.
                //ya que para la comprobación de la contraseña se deberia de tener el usuario también
                //en el protocolo no se especifica de que manera hacerlo
                if(comand_cliente.getComando().equals("USER")) setComando_usuario(comand_cliente);
                if(comand_cliente.getComando().equals("PASS")) comand_cliente.setComand_cliente_opcional(getComando_usuario());

                comand_cliente.ejecutar();

                comando_servidor c_server=comand_cliente.getComand_server();


                //almacenamos el comando anterior
                setComando_cliente_almacenado(comand_cliente);


                pw.println("SERVER : "+c_server.getTipo_respuesta()+" "+c_server.getNumber()+" "+c_server.getCod_respuesta()+" "+c_server.getInformacion_adicional());
                //manejo de user y sesion --> no se si hay alguna mas limpia u optima de manejarlo
                if(comand_cliente.getComando().equals("USER")&&comand_cliente.ejecutar()==1) setUser(1); user_id=comand_cliente.getComand_server().getUser();
                if(getUser()==1&&comand_cliente.ejecutar()==1&&comand_cliente.getComando().equals("PASS")) setSesion(1);
                //pw.println("Server :"+comand_cliente.getNumber()+" "+comand_cliente.getComando()+" "+ comand_cliente.getInformacion_adicional());
                pw.flush();


            }

            System.out.println("Finalizado");


        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}

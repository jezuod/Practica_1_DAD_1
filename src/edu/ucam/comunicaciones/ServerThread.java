package edu.ucam.comunicaciones;

import edu.ucam.comando.comando_cliente;
import edu.ucam.comando.comando_servidor;
import edu.ucam.principal.User;
import edu.ucam.sesiones.Sesion;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private Sesion sesion;

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


    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        try {

            System.out.println("Hilo lanzado...");
            BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            sesion=new Sesion();

            String lineaLeida = "";
            String palabras[];
            comando_cliente comand_cliente;
            comando_servidor c_server;
            while(!(lineaLeida=br.readLine()).equalsIgnoreCase("SALIR")) {
                palabras=lineaLeida.split("\\W+");/*
                The \\W+ will match all non-alphabetic characters occurring one or more times. So there is no need to replace

                */
                //manejo de comandos con información adicional o no

                if(palabras.length==2){
                    comand_cliente=new comando_cliente(palabras[0],palabras[1]);
                }
                else{
                    comand_cliente=new comando_cliente(palabras[0],palabras[2],palabras[1]);
                }


                try{
                    //le pasamos la lista de comando ejecutados antes de que el siguiente comando puede necesitarla
                    comand_cliente.setLista_comandos_emitidos(sesion.getLista_comandos_emitidos());
                    comand_cliente.setLista_comandos_introducidos(sesion.getLista_comandos_introducidos());
                    comand_cliente.setSesion(sesion);
                    comand_cliente.ejecutar();
                    c_server=comand_cliente.getComand_server();
                    //lo almacenamos
                    sesion.getLista_comandos_introducidos().add(comand_cliente);
                    sesion.getLista_comandos_emitidos().add(c_server);

                    pw.println("SERVER : "+c_server.getNumber()+" "+c_server.getTipo_respuesta()+" "+c_server.getCod_respuesta()+" "+c_server.getInformacion_adicional());

                }
                catch (Exception e)
                {
                    System.out.println("ERROR ");
                }
                finally {
                    pw.flush();
                }

            }


        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}

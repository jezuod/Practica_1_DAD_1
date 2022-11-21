package edu.ucam.sesiones;

import edu.ucam.comando.comando_cliente;
import edu.ucam.comando.comando_servidor;
import edu.ucam.comunicaciones.Client;
import edu.ucam.principal.User;

import java.util.ArrayList;

public class Sesion {
    private static ArrayList<Sesion> Sesiones = new ArrayList<Sesion>();

    public static ArrayList<Sesion> getSesiones() {
        return Sesiones;
    }

    private int sesion_iniciada;
    private User user;
    private ArrayList<comando_cliente> lista_comandos_introducidos=new ArrayList<comando_cliente>();
    private ArrayList<comando_servidor> lista_comandos_emitidos=new ArrayList<comando_servidor>();

    public int getSesion_iniciada() {
        return sesion_iniciada;
    }

    public void setSesion_iniciada(int sesion_iniciada) {
        this.sesion_iniciada = sesion_iniciada;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<comando_cliente> getLista_comandos_introducidos() {
        return lista_comandos_introducidos;
    }

    public void setLista_comandos_introducidos(ArrayList<comando_cliente> lista_comandos_introducidos) {
        this.lista_comandos_introducidos = lista_comandos_introducidos;
    }

    public ArrayList<comando_servidor> getLista_comandos_emitidos() {
        return lista_comandos_emitidos;
    }

    public void setLista_comandos_emitidos(ArrayList<comando_servidor> lista_comandos_emitidos) {
        this.lista_comandos_emitidos = lista_comandos_emitidos;
    }

    public Sesion() {
        Sesiones.add(this);
        sesion_iniciada=0;
    }


}

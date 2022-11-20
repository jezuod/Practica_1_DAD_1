package edu.ucam.comando;

public class comando_cliente extends comando{
    private String name;
    private String pass;
    private String id;
    private String id_jugador;
    private String id_club;

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getId() {
        return id;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public String getId_club() {
        return id_club;
    }

    public comando_cliente(int number, String instruccion, String name, String pass, String id, String id_jugador, String id_club) {
        super(number, instruccion);
        this.name = name;
        this.pass = pass;
        this.id = id;
        this.id_jugador = id_jugador;
        this.id_club = id_club;
    }
}

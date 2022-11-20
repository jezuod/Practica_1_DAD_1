package edu.ucam.comando;

public abstract class comando {
    private int number; // ej : <number>
    private String instruccion; // ej : USER/PASS/EXIT/ADDCLUB

    public comando(int number, String instruccion) {
        this.number = number;
        this.instruccion = instruccion;
    }

    public int getNumber() {
        return number;
    }

    public String getInstruccion() {
        return instruccion;
    }
}

package edu.ucam.comando;

public abstract class comando {
    private String number;
    private String informacion_adicional;

    public String getNumber() {
        return number;
    }

    public String getInformacion_adicional() {
        return informacion_adicional;
    }

    public comando(String number, String informacion_adicional) {
        this.number = number;
        this.informacion_adicional = informacion_adicional;
    }
}

package edu.ucam.comando;

public class comando_cliente extends comando{
    private static String[] supportedCommands = {"USER", "PASS", "EXIT","ADDCLUB","GETCLUB"};
    private String comando;

    public String getComando() {
        return comando;
    }

    public comando_cliente(String number, String informacion_adicional, String comando) {
        super(number, informacion_adicional);
        this.comando = comando;
    }
}

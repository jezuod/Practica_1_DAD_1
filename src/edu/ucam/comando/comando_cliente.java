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
    public int findCommand() {
        for (var i = 0; i < supportedCommands.length; i++) {
            if (this.comando.equals(supportedCommands[i])) return i;
        }
        return -1; //Not found
    }
}

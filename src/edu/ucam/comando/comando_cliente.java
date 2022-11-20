package edu.ucam.comando;

import edu.ucam.principal.User;

public class comando_cliente extends comando{
    private static String[] supportedCommands = {"USER", "PASS", "EXIT","ADDCLUB","GETCLUB"};
    private String comando;
    private comando_servidor Comand_server;
    public String getComando() {
        return comando;
    }

    public comando_cliente(String number, String informacion_adicional, String comando) {

        super(number, informacion_adicional);
        this.comando = comando;
    }
    public int check() { //función para poder chekear si el comando esta en la lista de comandos permitidos/implementados
        for (var i = 0; i < supportedCommands.length; i++) {
            if (this.comando.equals(supportedCommands[i])) return i;
        }
        return -1; //Not found
    }

    public comando_servidor getComand_server() {
        return Comand_server;
    }

    public void setComand_server(comando_servidor comand_server) {
        Comand_server = comand_server;
    }

    public void ejecutar()
    {
        //comprobar validez
        if(this.check()!=(-1))
        {
            if(getComando().equals("USER"))
                if((new User().checkear_usuarios(getInformacion_adicional(),null))!=-1)
                {
                    System.out.println("Comando válido");
                    setComand_server(new comando_servidor(getNumber(),"Envíe contraseña",'O',21));
                }
                else
                    setComand_server(new comando_servidor(getNumber(),"Not user",'F',41));

        }
        else
            System.out.println("Comando no válido");

    }

}

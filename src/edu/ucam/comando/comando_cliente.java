package edu.ucam.comando;

import edu.ucam.principal.User;

public class comando_cliente extends comando{
    private static String[] supportedCommands = {"USER", "PASS", "EXIT","ADDCLUB","GETCLUB"};
    private String comando;
    private comando_servidor Comand_server;
    private comando_cliente Comand_cliente_opcional;

    public comando_cliente getComand_cliente_opcional() {
        return Comand_cliente_opcional;
    }

    public void setComand_cliente_opcional(comando_cliente comand_cliente_opcional) {
        Comand_cliente_opcional = comand_cliente_opcional;
    }

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

    public int ejecutar()
    {
        //comprobar validez
        if(this.check()!=(-1))
        {
            if(getComando().equals("USER"))
            {
                if((new User().checkear_usuarios(getInformacion_adicional(),null))!=-1)
                {
                    System.out.println("Comando válido");
                    setComand_server(new comando_servidor(getNumber(),"Envíe contraseña",'O',21));
                    Comand_server.setUser(getInformacion_adicional());
                    return 1;
                }
                else
                    setComand_server(new comando_servidor(getNumber(),"Not user",'F',41));return -1;
            }
            if(getComando().equals("PASS"))
            {
                if((new User().checkear_usuarios(getComand_cliente_opcional().getInformacion_adicional(),getInformacion_adicional()))!=-1)
                {
                    System.out.println("Comando válido");
                    setComand_server(new comando_servidor(getNumber(),"Welcome "+getComand_cliente_opcional().getInformacion_adicional(),'O',22));

                    return 1;
                }
                else
                    setComand_server(new comando_servidor(getNumber(),"Prueba de nuevo",'F',42));return -1;
            }

        }
        return -1;

    }


}

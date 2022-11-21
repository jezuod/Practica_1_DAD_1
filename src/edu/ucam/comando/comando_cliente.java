package edu.ucam.comando;


import edu.ucam.principal.User;
import edu.ucam.sesiones.Sesion;

import java.util.ArrayList;

import static edu.ucam.principal.User.checkear_usuarios;
import static edu.ucam.principal.User.conseguir_user;
import static edu.ucam.sesiones.Sesion.getSesiones;


public class comando_cliente extends comando{
    private static String[] supportedCommands = {"USER", "PASS", "EXIT","ADDCLUB","GETCLUB","SESIONES"};
    private String comando;
    private ArrayList<comando_cliente> lista_comandos_introducidos=new ArrayList<comando_cliente>();
    private ArrayList<comando_servidor> lista_comandos_emitidos=new ArrayList<comando_servidor>();
    private Sesion sesion;

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
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

    private comando_servidor Comand_server;


    public String getComando() {
        return comando;
    }

    public comando_cliente(String number, String informacion_adicional, String comando) {

        super(number, informacion_adicional);
        this.comando = comando;
    }
    public comando_cliente(String number, String comando) {

        super(number, null);
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
        try
        {
            if(this.check()!=(-1))
            {
                if(getComando().equals("USER"))
                {
                    if((checkear_usuarios(getInformacion_adicional(),null))!=-1)
                    {
                        setComand_server(new comando_servidor(getNumber(),"Envíe contraseña",'O',21));
                       // Comand_server.setUser(getInformacion_adicional());

                        return 1;
                    }
                    else
                        setComand_server(new comando_servidor(getNumber(),"Not user",'F',41));return -1;
                }
                if(getComando().equals("PASS"))
                {
                    String usuario_temporal=null;
                    for(int i=0;i<getLista_comandos_introducidos().size();i++)
                    {
                        if(getLista_comandos_introducidos().get(i).getComando().equals("USER"))
                        {
                            usuario_temporal=getLista_comandos_introducidos().get(i).getInformacion_adicional();
                        }
                    }
                    if((checkear_usuarios(usuario_temporal,getInformacion_adicional())==1))
                    {
                        setComand_server(new comando_servidor(getNumber(),"Welcome ",'O',22));
                        //metemos el usario en la sesion
                        getSesion().setUser(conseguir_user(usuario_temporal,getInformacion_adicional()));
                        getSesion().setSesion_iniciada(1);
                        //lo metemos en usuarios conectados
                        //getUser_conectados().add(usuario);
                        return 1;
                    }
                    else
                        setComand_server(new comando_servidor(getNumber(),"Prueba de nuevo",'F',42));return -1;
                }
                if(getComando().equals("EXIT")){
                    setComand_server(new comando_servidor(getNumber(),"Bye ",'O',23));
                    //quitariamos al usuario de la sesión
                    getSesion().setUser(null);
                    getSesion().setSesion_iniciada(0);
                    //lo sacamso de usuarios conectados
                       // for(int i=0;i<getUser_conectados().size();i++)
                        {
                          //  if(getUser_conectados().get(i).getUser_id().equals(usuario.getUser_id()))
                                //lo borramos de usuarios conectados
                               // getUser_conectados().remove(i);
                        }


                    return 1;

                }
                if(getComando().equals("SESIONES")){
                    if(getSesion().getSesion_iniciada()==1)
                    {
                        setComand_server(new comando_servidor(getNumber(),"Num sesiones abiertas : "+getSesiones().size()+" Bye",'O',24));
                    }
                    else
                        setComand_server(new comando_servidor(getNumber(),"Error Sin sesión inciada",'F',44));
                }
                /* comprobar que usuario esta en la sesión despues del exit
                if(getComando().equals("SESION_USER")){
                    if(getSesion().getUser()!=null){
                        setComand_server(new comando_servidor(getNumber(),"Usuario de la sesión : "+getSesion().getUser().getUser_id(),'O',24));
                    }
                    else
                        setComand_server(new comando_servidor(getNumber(),"Usuario de la sesión : "+null,'F',44));

                }

                 */
                if(getComando().equals("ADDCLUB"))
                {
                    //requiere que el usuario este autenticado --> ÚLTIMO COMANDO PASS DEBE DE DAR OK
                    //PREOK
                        //OK
                    //FAILED
                }
                if(getComando().equals("GETCLUB")){
                    //requiere que el usuario este autenticado --> ÚLTIMO COMANDO PASS DEBE DE DAR OK
                    //PREOK
                        //OK
                    //FAILED
                }
            }
            return -1;
        }catch(Exception e){
            System.out.println("ERROR");
            return 0;
        }
        finally {
            return 0;
        }

    }

}

package edu.ucam.principal;

import edu.ucam.comunicaciones.Client;

import java.util.ArrayList;

public class User {
    private static ArrayList<User> users=new ArrayList<User>(); //tener acceso a todos los usuarios

    public User() {

    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    //SISTEMA DE USUARIO
    private String user_id; // ID USUARIO
    private String user_password; // CONTRASEÑA
    private Boolean admin; //ADMIN O NO
    public String getUser_id() {
        return user_id;
    }
    public String getUser_password() {
        return user_password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public User(String user_id, String user_password) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.admin=false;
        users.add(this);
    }



    public User(String user_id, String user_password, Boolean admin) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.admin = admin;
        users.add(this);
    }
    //por seguridad, vamos a checkear sin mandar nuestra lista de usuarios fuera de la clase
    public static int checkear_usuarios(String user_id,String user_password)
    {
        int check,i;
        check=-1;
        if(user_password==null) //chequeo solo de que este el usuario
            for(i=0;i<users.size();i++)
                if(user_id.equals(users.get(i).user_id))
                    check=1;
        if (user_password!=null)//caso que pasemso usuario y contraseña
        {
            for(i=0;i<users.size();i++)
                if((user_id.equals(users.get(i).user_id))&&(user_password.equals(users.get(i).user_password)))
                    check=1;
        }


        return check;
    }
    public static User conseguir_user(String user_id,String user_password){
        int i;
        if (user_password!=null)//caso que pasemso usuario y contraseña
        {
            for(i=0;i<users.size();i++)
                if((user_id.equals(users.get(i).user_id))&&(user_password.equals(users.get(i).user_password)))
                    return users.get(i);
        }

    return null;
    }




}

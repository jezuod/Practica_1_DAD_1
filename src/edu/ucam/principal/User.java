package edu.ucam.principal;

public class User {
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
    }

    public User(String user_id, String user_password, Boolean admin) {
        this.user_id = user_id;
        this.user_password = user_password;
        this.admin = admin;
    }
}

package ar.com.bago.model.user;

import ar.com.bago.model.BackEndObject;

public class Credential extends BackEndObject {

    private static final long serialVersionUID = 8673721154418820146L;

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

package ar.com.bago.model.user;

import java.util.Set;

import ar.com.bago.model.BackEndObject;

public class UserLogin extends BackEndObject {

    private static final long serialVersionUID = -8800101637213980485L;

    private User user;

    private Set<String> permissions;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

}

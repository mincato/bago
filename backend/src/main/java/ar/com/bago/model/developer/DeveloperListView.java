package ar.com.bago.model.developer;

import ar.com.bago.model.BackEndEntity;

public class DeveloperListView extends BackEndEntity {

    private static final long serialVersionUID = 4094341771190595311L;

    private String name;
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}

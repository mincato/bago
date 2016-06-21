package ar.com.bago.model.developer;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ar.com.bago.model.BackEndEntity;
import ar.com.bago.rest.json.JSONDateDeserialize;
import ar.com.bago.rest.json.JSONDateSerialize;

public class Developer extends BackEndEntity {

    private static final long serialVersionUID = 6686724366732219305L;

    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private Seniority seniority;

    @JsonDeserialize(using = JSONDateDeserialize.class)
    @JsonSerialize(using = JSONDateSerialize.class)
    private Date dateEntry;

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

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

    public Date getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(Date dateEntry) {
        this.dateEntry = dateEntry;
    }

}

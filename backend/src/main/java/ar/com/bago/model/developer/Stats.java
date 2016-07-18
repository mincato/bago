package ar.com.bago.model.developer;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Stats {

    @JsonIgnore
    private int seniorityValue;

    private int developers;

    public int getDevelopers() {
        return developers;
    }

    public void setDevelopers(int developers) {
        this.developers = developers;
    }

    public Seniority getSeniority() {
        return Seniority.get(seniorityValue);
    }

    public int getSeniorityValue() {
        return seniorityValue;
    }

    public void setSeniorityValue(int seniorityValue) {
        this.seniorityValue = seniorityValue;
    }

}

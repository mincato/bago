package ar.com.bago.model.developer;

import org.apache.commons.lang3.builder.EqualsBuilder;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Stats stats = (Stats) obj;
        return new EqualsBuilder().append(seniorityValue, stats.seniorityValue).append(developers, stats.developers)
                .isEquals();
    }

}

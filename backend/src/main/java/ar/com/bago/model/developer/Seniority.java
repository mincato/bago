package ar.com.bago.model.developer;

public enum Seniority {
    JUNIOR, SEMI_SENIOR, SENIOR;

    private static Seniority[] seniorities = values();

    public static Seniority get(int seniority) {
        return seniorities[seniority];
    }
}

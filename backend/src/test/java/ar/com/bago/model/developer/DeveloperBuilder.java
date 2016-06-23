package ar.com.bago.model.developer;

import ar.com.bago.common.random.RandomInteger;
import ar.com.bago.model.developer.Developer;
import ar.com.bago.model.developer.DeveloperListView;
import ar.com.bago.model.developer.Seniority;

public class DeveloperBuilder {

    public static Developer createGerman() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("German");
        developer.setLastName("Hohmann");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createPablo() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Pablo");
        developer.setLastName("Blauer");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createAdrian() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Adrian");
        developer.setLastName("Paredes");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createDarthVader() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Darth");
        developer.setLastName("Vader");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createKyloRen() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Kylo");
        developer.setLastName("Ren");
        developer.setSeniority(Seniority.JUNIOR);
        return developer;
    }

    public static Developer createDarthMaul() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Darth");
        developer.setLastName("Maul");
        developer.setSeniority(Seniority.SEMI_SENIOR);
        return developer;
    }

    public static DeveloperListView createKyloRenListView() {
        DeveloperListView developer = new DeveloperListView();
        developer.setId(RandomInteger.getNext());
        developer.setName("Kylo");
        developer.setLastName("Ren");
        return developer;
    }

    public static DeveloperListView createDarthVaderListView() {
        DeveloperListView developer = new DeveloperListView();
        developer.setId(RandomInteger.getNext());
        developer.setName("Darth");
        developer.setLastName("Vader");
        return developer;
    }

    public static DeveloperListView createDarthMaulListView() {
        DeveloperListView developer = new DeveloperListView();
        developer.setId(RandomInteger.getNext());
        developer.setName("Darth");
        developer.setLastName("Maul");
        return developer;
    }

}

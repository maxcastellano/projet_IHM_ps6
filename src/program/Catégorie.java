package program;

public enum Catégorie {

    Entrée ("Entrée"),
    Plat ("Plat"),
    Dessert ("Dessert");

    private String name="";

    Catégorie(String name){
        this.name = name;
    }

    public String toSTring(){
        return name;
    }
}

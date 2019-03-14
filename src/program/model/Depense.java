package program.model;

public class Depense {

    private String date;
    private String nom;
    private long prix;

    public  Depense (String date, String nom, long prix){

            this.nom = nom;
            this.date = date;
            this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public long getPrix() {
        return prix;
    }

    public String getDate() {
        return date;
    }
}

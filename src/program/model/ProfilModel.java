package program.model;

public class ProfilModel {

    private String name, firstname, email, city;
    private double seuil;

    public ProfilModel(String name, String firstname, String email, String city, double seuil){

        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.city = city;
        this.seuil = seuil;
    }

    public double getSeuil() {
        return seuil;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getName() {
        return name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

}

package model;

/**
 * Person soyut (abstract) sınıfı: Doğrudan new ile üretilemez.
 * Ortak alanları ve davranışları taşır.
 */
public abstract class Person {

    protected Long id;
    protected String ad;
    protected String soyad;
    protected String email;
    protected String telefon;

    public Person() {
    }

    public Person(Long id, String ad, String soyad, String email, String telefon) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefon = telefon;
    }

    // Alt sınıfların kendilerine göre doldurması ZORUNLU olan soyut metot
    public abstract String getRol();

    // Alt sınıflar tarafından ezilebilecek (override) temel davranış
    public void bilgiGoster() {
        System.out.println("ID: " + id + " | Ad Soyad: " + ad + " " + soyad + " | Rol: " + getRol() + " | Email: " + email);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}
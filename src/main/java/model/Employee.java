package model;

public class Employee extends Person {

    private String departman;
    private Double maas;

    public Employee() {
        super();
    }

    public Employee(Long id, String ad, String soyad, String email, String telefon, String departman, Double maas) {
        super(id, ad, soyad, email, telefon);
        this.departman = departman;
        this.maas = maas;
    }

    @Override
    public String getRol() {
        return "Çalışan (" + departman + ")";
    }

    @Override
    public void bilgiGoster() {
        super.bilgiGoster();
        System.out.println("  -> Departman: " + departman + " | Maaş: " + maas + " TL");
    }

    public void calis() {
        System.out.println(ad + " " + soyad + " (" + departman + ") görevine başladı.");
    }

    public String getDepartman() { return departman; }
    public void setDepartman(String departman) { this.departman = departman; }
    public Double getMaas() { return maas; }
    public void setMaas(Double maas) { this.maas = maas; }
}
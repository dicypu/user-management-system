package model;

public class Admin extends Person {

    private Integer yetkiSeviyesi;

    public Admin() {
        super();
    }

    public Admin(Long id, String ad, String soyad, String email, String telefon, Integer yetkiSeviyesi) {
        super(id, ad, soyad, email, telefon);
        this.yetkiSeviyesi = yetkiSeviyesi;
    }

    @Override
    public String getRol() {
        return "Sistem Yöneticisi (Admin)";
    }

    @Override
    public void bilgiGoster() {
        super.bilgiGoster();
        System.out.println("  -> Yetki Seviyesi: " + yetkiSeviyesi + "/5 (Tam Erişim)");
    }

    public void kullaniciEngelle(Person hedef) {
        System.out.println("[ADMIN İŞLEMİ] " + ad + ", " + hedef.getAd() + " kullanıcısını askıya aldı.");
    }

    public Integer getYetkiSeviyesi() { return yetkiSeviyesi; }
    public void setYetkiSeviyesi(Integer yetkiSeviyesi) { this.yetkiSeviyesi = yetkiSeviyesi; }
}
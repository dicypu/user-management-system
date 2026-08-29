package model;

public class Customer extends Person {

    private String musteriNumarasi;
    private Integer sadakatPuani;

    public Customer() {
        super();
    }

    public Customer(Long id, String ad, String soyad, String email, String telefon, String musteriNumarasi, Integer sadakatPuani) {
        super(id, ad, soyad, email, telefon);
        this.musteriNumarasi = musteriNumarasi;
        this.sadakatPuani = sadakatPuani;
    }

    @Override
    public String getRol() {
        return "Müşteri";
    }

    @Override
    public void bilgiGoster() {
        super.bilgiGoster();
        System.out.println("  -> Müşteri No: " + musteriNumarasi + " | Puan: " + sadakatPuani);
    }

    public void siparisVer(String urun) {
        System.out.println(ad + " adlı müşteri '" + urun + "' siparişi verdi.");
    }

    public String getMusteriNumarasi() { return musteriNumarasi; }
    public void setMusteriNumarasi(String musteriNumarasi) { this.musteriNumarasi = musteriNumarasi; }
    public Integer getSadakatPuani() { return sadakatPuani; }
    public void setSadakatPuani(Integer sadakatPuani) { this.sadakatPuani = sadakatPuani; }
}
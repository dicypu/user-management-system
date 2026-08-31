package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Kullanıcı oluştururken istemciden beklenen veri şablonu.
 * ID, durum veya tarih alanlarını içermez (Over-posting koruması).
 */
public class UserRequestDto {

    @NotBlank(message = "Kullanıcı adı boş bırakılamaz!")
    @Size(min = 2, max = 50, message = "Kullanıcı adı 2 ile 50 karakter arasında olmalıdır!")
    private String ad;

    @NotBlank(message = "Kullanıcı soyadı boş bırakılamaz!")
    @Size(min = 2, max = 50, message = "Kullanıcı soyadı 2 ile 50 karakter arasında olmalıdır!")
    private String soyad;

    @NotBlank(message = "E-posta alanı boş bırakılamaz!")
    @Email(message = "Geçerli bir e-posta formatı giriniz!")
    private String email;

    @NotBlank(message = "Telefon numarası boş bırakılamaz!")
    private String telefon;

    public UserRequestDto() {
    }

    public UserRequestDto(String ad, String soyad, String email, String telefon) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefon = telefon;
    }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}
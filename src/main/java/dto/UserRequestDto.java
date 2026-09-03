package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Kullanıcı Oluşturma ve Güncelleme İstek Modeli")
public class UserRequestDto {

    @Schema(description = "Kullanıcının adı", example = "Emirhan")
    @NotBlank(message = "Ad alanı boş bırakılamaz.")
    @Size(min = 2, max = 50, message = "Ad en az 2, en fazla 50 karakter olmalıdır.")
    private String ad;

    @Schema(description = "Kullanıcının soyadı", example = "Yavuz")
    @NotBlank(message = "Soyad alanı boş bırakılamaz.")
    @Size(min = 2, max = 50, message = "Soyad en az 2, en fazla 50 karakter olmalıdır.")
    private String soyad;

    @Schema(description = "Kurumsal e-posta adresi", example = "emirhan@thinx.com")
    @NotBlank(message = "E-posta alanı boş bırakılamaz.")
    @Email(message = "Geçerli bir e-posta formatı giriniz.")
    private String email;

    @Schema(description = "İletişim telefon numarası", example = "+905551234567")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Telefon numarası geçerli bir uluslararası formatta olmalıdır.")
    private String telefon;

    public UserRequestDto() {
    }

    public UserRequestDto(String ad, String soyad, String email, String telefon) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefon = telefon;
    }

    // Getters and Setters
    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }
    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }
}
package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "KULLANICILAR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AD", nullable = false, length = 50)
    private String ad;

    @Column(name = "SOYAD", nullable = false, length = 50)
    private String soyad;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "TELEFON", length = 20)
    private String telefon;

    @Column(name = "DURUM", nullable = false)
    private Boolean durum;

    @Column(name = "KAYIT_TARIHI", updatable = false)
    private LocalDateTime tarih;

    @PrePersist
    protected void onCreate() {
        this.tarih = LocalDateTime.now();
        if (this.durum == null) {
            this.durum = true;
        }
    }
}
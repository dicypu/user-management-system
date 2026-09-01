package repository;

import entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // E-posta ile kullanıcı bulma
    Optional<UserEntity> findByEmail(String email);

    // E-posta sistemde var mı kontrolü (Performanslı varlık kontrolü)
    boolean existsByEmail(String email);

    // İsme göre büyük/küçük harf duyarsız arama
    List<UserEntity> findByAdContainingIgnoreCase(String ad);

    // Sadece aktif/pasif kullanıcıları listeleme
    List<UserEntity> findByDurum(Boolean durum);
}
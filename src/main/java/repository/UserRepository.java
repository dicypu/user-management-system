package repository;

import entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    // Gerçek DB'ye geçene kadar thread-safe in-memory veritabanı simülasyonu
    private final Map<Long, UserEntity> database = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1L);

    public UserEntity save(UserEntity entity) {
        if (entity.getId() == null) {
            entity.setId(idGenerator.getAndIncrement());
        }
        database.put(entity.getId(), entity);
        return entity;
    }

    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public List<UserEntity> findAll() {
        return new ArrayList<>(database.values());
    }

    public boolean existsByEmail(String email) {
        return database.values().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public boolean deleteById(Long id) {
        return database.remove(id) != null;
    }
}
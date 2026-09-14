package service;

import dto.UserRequestDto;
import dto.UserResponseDto;
import entity.UserEntity;
import exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        log.info("Yeni kullanıcı kayıt işlemi başlatıldı. Email: {}", dto.getEmail());

        UserEntity entity = UserEntity.builder()
                .ad(dto.getAd())
                .soyad(dto.getSoyad())
                .email(dto.getEmail())
                .telefon(dto.getTelefon())
                .durum(true)
                .build();

        UserEntity saved = userRepository.save(entity);
        log.info("Kullanıcı başarıyla veritabanına mühürlendi. Atanan ID: {}", saved.getId());
        return mapToDto(saved);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        log.debug("Tüm kullanıcı kayıtları Oracle XE üzerinden sorgulanıyor...");
        List<UserEntity> users = userRepository.findAll();
        log.info("Toplam {} adet kullanıcı kaydı listelendi.", users.size());

        return users.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        log.debug("Kullanıcı sorgulanıyor. ID: {}", id);
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID'si " + id + " olan kullanıcı bulunamadı."));
        return mapToDto(entity);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        log.info("Kullanıcı güncelleme talebi alındı. ID: {}", id);
        UserEntity existingUser = findUserByIdOrThrow(id);
        updateEntityFields(existingUser, dto);
        UserEntity savedUser = userRepository.save(existingUser);
        log.info("Kullanıcı başarıyla güncellendi. ID: {}", savedUser.getId());
        return mapToDto(savedUser);
    }

    private UserEntity findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID'si " + id + " olan kullanıcı bulunamadı."));
    }

    private void updateEntityFields(UserEntity entity, UserRequestDto dto) {
        entity.setAd(dto.getAd());
        entity.setSoyad(dto.getSoyad());
        entity.setEmail(dto.getEmail());
        entity.setTelefon(dto.getTelefon());
    }

    @Transactional
    public void deleteUser(Long id) {
        log.warn("Kullanıcı silme operasyonu tetiklendi! Hedef ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Silinecek kullanıcı bulunamadı. ID: " + id);
        }
        userRepository.deleteById(id);
        log.info("Kullanıcı veritabanından kalıcı olarak silindi. ID: {}", id);
    }

    private UserResponseDto mapToDto(UserEntity entity) {
        return UserResponseDto.builder()
                .id(entity.getId())
                .ad(entity.getAd())
                .soyad(entity.getSoyad())
                .email(entity.getEmail())
                .telefon(entity.getTelefon())
                .durum(entity.getDurum())
                .tarih(entity.getTarih())
                .build();
    }
}
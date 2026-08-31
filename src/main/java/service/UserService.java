package service;

import dto.UserRequestDto;
import dto.UserResponseDto;
import entity.UserEntity;
import exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor Injection (Lombok olmadan açık kurumsal standart)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto requestDto) {
        // İş Kuralı: Aynı e-posta ile ikinci kayıt açılamaz
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Bu e-posta adresi (" + requestDto.getEmail() + ") sistemde zaten kayıtlı!");
        }

        // RequestDTO -> Entity Dönüşümü
        UserEntity entity = new UserEntity();
        entity.setAd(requestDto.getAd());
        entity.setSoyad(requestDto.getSoyad());
        entity.setEmail(requestDto.getEmail());
        entity.setTelefon(requestDto.getTelefon());
        entity.setDurum(true); // Varsayılan aktif
        entity.setTarih(LocalDateTime.now());

        UserEntity savedEntity = userRepository.save(entity);

        // Entity -> ResponseDTO Dönüşümü
        return mapToResponseDto(savedEntity);
    }

    public UserResponseDto getUserById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID'si " + id + " olan kullanıcı sistemde bulunamadı!"));
        return mapToResponseDto(entity);
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    // Mapping Yardımcı Metodu
    private UserResponseDto mapToResponseDto(UserEntity entity) {
        return new UserResponseDto(
                entity.getId(),
                entity.getAd(),
                entity.getSoyad(),
                entity.getEmail(),
                entity.getTelefon(),
                entity.getDurum(),
                entity.getTarih()
        );
    }
}
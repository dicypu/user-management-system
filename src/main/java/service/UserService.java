package service;

import dto.UserRequestDto;
import dto.UserResponseDto;
import entity.UserEntity;
import exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("Bu e-posta adresi (" + requestDto.getEmail() + ") sistemde zaten kayıtlı!");
        }

        UserEntity entity = new UserEntity();
        entity.setAd(requestDto.getAd());
        entity.setSoyad(requestDto.getSoyad());
        entity.setEmail(requestDto.getEmail());
        entity.setTelefon(requestDto.getTelefon());

        UserEntity savedEntity = userRepository.save(entity);
        return mapToResponseDto(savedEntity);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID'si " + id + " olan kullanıcı sistemde bulunamadı!"));
        return mapToResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> searchUsersByName(String name) {
        return userRepository.findByAdContainingIgnoreCase(name).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

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
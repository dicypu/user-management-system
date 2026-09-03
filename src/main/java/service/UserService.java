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
    public UserResponseDto createUser(UserRequestDto dto) {
        UserEntity entity = new UserEntity();
        entity.setAd(dto.getAd());
        entity.setSoyad(dto.getSoyad());
        entity.setEmail(dto.getEmail());
        entity.setTelefon(dto.getTelefon());
        entity.setDurum(true);

        UserEntity saved = userRepository.save(entity);
        return mapToDto(saved);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ID'si " + id + " olan kullanıcı bulunamadı."));
        return mapToDto(entity);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Güncellenecek kullanıcı bulunamadı. ID: " + id));

        entity.setAd(dto.getAd());
        entity.setSoyad(dto.getSoyad());
        entity.setEmail(dto.getEmail());
        entity.setTelefon(dto.getTelefon());

        UserEntity updated = userRepository.save(entity);
        return mapToDto(updated);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Silinecek kullanıcı bulunamadı. ID: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserResponseDto mapToDto(UserEntity entity) {
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
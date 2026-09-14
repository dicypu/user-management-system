package service;

import dto.UserRequestDto;
import dto.UserResponseDto;
import entity.UserEntity;
import event.UserCreatedEvent;
import exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private UserService userService;

    private UserEntity sampleUser;
    private UserRequestDto sampleRequestDto;

    @BeforeEach
    void setUp() {
        sampleUser = UserEntity.builder()
                .id(1L)
                .ad("Emirhan")
                .soyad("Yavuz")
                .email("emirhan@thinx.com")
                .telefon("05551112233")
                .durum(true)
                .tarih(LocalDateTime.now())
                .build();

        sampleRequestDto = UserRequestDto.builder()
                .ad("Emirhan")
                .soyad("Yavuz")
                .email("emirhan@thinx.com")
                .telefon("05551112233")
                .build();
    }

    @Test
    @DisplayName("createUser: Geçerli DTO verildiğinde kullanıcı kaydedilmeli ve olay fırlatılmalıdır")
    void createUser_ShouldSaveUserAndPublishEvent_WhenDtoIsValid() {
        // Given (Ön Koşullar)
        when(userRepository.save(any(UserEntity.class))).thenReturn(sampleUser);

        // When (Eylem)
        UserResponseDto response = userService.createUser(sampleRequestDto);

        // Then (Doğrulama & Savlar)
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("emirhan@thinx.com", response.getEmail());

        // Bağımlılık Etkileşim Doğrulamaları
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(eventPublisher, times(1)).publishEvent(any(UserCreatedEvent.class));
    }

    @Test
    @DisplayName("getUserById: Kayıt mevcut olduğunda doğru DTO dönmelidir")
    void getUserById_ShouldReturnUserResponseDto_WhenUserExists() {
        // Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));

        // When
        UserResponseDto response = userService.getUserById(1L);

        // Then
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Emirhan", response.getAd());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getUserById: Kayıt bulunamadığında UserNotFoundException fırlatmalıdır")
    void getUserById_ShouldThrowException_WhenUserDoesNotExist() {
        // Given
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(99L)
        );

        assertTrue(exception.getMessage().contains("99"));
        verify(userRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("updateUser: Mevcut kullanıcı güncellenmeli ve yeni bilgiler dönmelidir")
    void updateUser_ShouldUpdateFieldsAndReturnDto_WhenUserExists() {
        // Given
        UserRequestDto updateDto = UserRequestDto.builder()
                .ad("Ahmet")
                .soyad("Kaya")
                .email("ahmet@thinx.com")
                .telefon("05009998877")
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        UserResponseDto response = userService.updateUser(1L, updateDto);

        // Then
        assertNotNull(response);
        assertEquals("Ahmet", response.getAd());
        assertEquals("ahmet@thinx.com", response.getEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(sampleUser);
    }

    @Test
    @DisplayName("deleteUser: Kullanıcı veritabanında yoksa silme denenmemeli ve istisna fırlatmalıdır")
    void deleteUser_ShouldThrowException_WhenUserDoesNotExist() {
        // Given
        when(userRepository.existsById(50L)).thenReturn(false);

        // When & Then
        assertThrows(
                UserNotFoundException.class,
                () -> userService.deleteUser(50L)
        );

        // Delete metodunun asla çağrılmadığını kanıtla
        verify(userRepository, never()).deleteById(anyLong());
    }
}
package controller;

import dto.UserRequestDto;
import dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Kullanıcı Yönetimi", description = "CRUD operasyonlarını yöneten REST API uçları")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Yeni kullanıcı oluşturur", description = "Gelen DTO doğrulanır ve Oracle üzerinde Sequence ile ID atanarak kaydedilir.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Kullanıcı başarıyla oluşturuldu."),
            @ApiResponse(responseCode = "400", description = "Validasyon hatası veya geçersiz veri formatı.")
    })
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto dto) {
        UserResponseDto createdUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    @Operation(summary = "Tüm kullanıcıları listeler", description = "Veritabanındaki tüm kullanıcıları liste halinde döner.")
    @ApiResponse(responseCode = "200", description = "Kullanıcılar başarıyla listelendi.")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID ile kullanıcı getirir", description = "Belirtilen tekil ID'ye sahip kullanıcıyı getirir.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Kullanıcı bulundu."),
            @ApiResponse(responseCode = "404", description = "Belirtilen ID ile kullanıcı bulunamadı.")
    })
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mevcut kullanıcıyı günceller", description = "ID'si verilen kullanıcının tüm bilgilerini günceller.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Kullanıcı başarıyla güncellendi."),
            @ApiResponse(responseCode = "400", description = "Geçersiz güncelleme parametreleri."),
            @ApiResponse(responseCode = "404", description = "Güncellenecek kullanıcı bulunamadı.")
    })
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kullanıcıyı siler", description = "ID'si verilen kullanıcıyı sistemden kalıcı olarak kaldırır.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Kullanıcı başarıyla silindi (İçerik dönmez)."),
            @ApiResponse(responseCode = "404", description = "Silinecek kullanıcı bulunamadı.")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
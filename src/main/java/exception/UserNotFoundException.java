package exception;

/*
 * Belirtilen kriterlere uygun kullanıcı bulunamadığında fırlatılan custom runtime exception.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
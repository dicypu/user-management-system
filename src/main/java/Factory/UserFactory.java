package factory;

import model.Admin;
import model.Customer;
import model.Employee;
import model.Person;

public class UserFactory {

    public enum UserRole {
        EMPLOYEE,
        CUSTOMER,
        ADMIN
    }

    public static Person createPerson(UserRole role, Long id, String ad, String soyad, String email, String telefon) {
        if (role == null) {
            throw new IllegalArgumentException("Kullanıcı rolü boş bırakılamaz!");
        }

        switch (role) {
            case EMPLOYEE:
                return new Employee(id, ad, soyad, email, telefon, "Yazılım", 45000.0);
            case CUSTOMER:
                return new Customer(id, ad, soyad, email, telefon, "CUST-" + id, 250);
            case ADMIN:
                return new Admin(id, ad, soyad, email, telefon, 5);
            default:
                throw new IllegalArgumentException("Geçersiz rol tipi: " + role);
        }
    }
}
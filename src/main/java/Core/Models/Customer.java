package Core.Models;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    private final UUID id;
    private String username;
    private String email;
    private LocalDate dateOfBirth;

    public Customer(
        UUID id,
        String username,
        String email,
        LocalDate dateOfBirth
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
    public Customer(Customer customer) {
        this.id = customer.id;
        this.username = new String(customer.username);
        this.email = new String(customer.email);
        this.dateOfBirth = customer.dateOfBirth;
    }
    public UUID getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}


/*

o  ID

o  Nutzernamen

o  E-Mail

o  Geburtsdatum

*/
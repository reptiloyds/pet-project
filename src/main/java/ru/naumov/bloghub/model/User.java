package ru.naumov.bloghub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    @Size(min = 3, max = 20)
    private String username;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "password")
    @Size(min = 6, max = 20)
    private String password;
    @Column(name = "created_at")
    private Instant createdAt;
}

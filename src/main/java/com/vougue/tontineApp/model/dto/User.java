package com.vougue.tontineApp.model.dto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Table(name = "app_user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "user name required.")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "password required")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}
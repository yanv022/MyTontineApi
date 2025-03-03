package com.vougue.tontineApp.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;

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

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String username;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
    private String role;

}
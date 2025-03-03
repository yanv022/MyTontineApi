package com.vougue.tontineApp.repository;


import com.vougue.tontineApp.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
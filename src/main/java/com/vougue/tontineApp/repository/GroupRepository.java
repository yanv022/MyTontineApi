package com.vougue.tontineApp.repository;

import com.vougue.tontineApp.model.TontineGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<TontineGroup, Long> {
    @Query("SELECT g FROM TontineGroup g LEFT JOIN FETCH g.members WHERE g.id = :id")
    TontineGroup findGroupWithMembers(@Param("id") Long id);
}
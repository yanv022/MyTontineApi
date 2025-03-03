package com.vougue.tontineApp.controller;

import com.vougue.tontineApp.model.TontineGroup;
import com.vougue.tontineApp.model.dto.GroupUpdateDTO;
import com.vougue.tontineApp.service.TontineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping({"/api/tontine", "/api/tontine/"})
@RequiredArgsConstructor
public class TontineController {
    private final TontineService tontineService;

    @GetMapping // GET http://localhost:8080/api/tontine
    public ResponseEntity<List<TontineGroup>> getAllGroups() {
        return ResponseEntity.ok(tontineService.getAllGroups());
    }

    @GetMapping({"/{groupId}", "/{groupId}/"}) // GET http://localhost:8080/api/tontine/1
    public ResponseEntity<TontineGroup> getGroupById(@PathVariable Long groupId) {
        return ResponseEntity.ok(tontineService.getGroupById(groupId));
    }
    // Endpoint POST to create a new group
    @PostMapping
    public ResponseEntity<TontineGroup> createGroup(@RequestBody TontineGroup newGroup) {
        return ResponseEntity.ok(tontineService.createGroup(newGroup));
    }

    @PostMapping("/rotate/{groupId}")
    public ResponseEntity<String> rotateGroup(@PathVariable Long groupId) {
        tontineService.processRotation(groupId);
        return ResponseEntity.ok("Rotation réussie");
    }
    // Mise à jour partielle
    @PatchMapping({"/{groupId}", "/{groupId}/"}) // GET http://localhost:8080/api/tontine/1
    public ResponseEntity<TontineGroup> partialUpdateGroup(
            @PathVariable Long groupId,
            @RequestBody GroupUpdateDTO updateDTO) {

        return ResponseEntity.ok(tontineService.updateGroup(groupId, updateDTO));
    }

    // Mise à jour complète
    @PutMapping({"/{groupId}", "/{groupId}/"})
    public ResponseEntity<TontineGroup> fullUpdateGroup(
            @PathVariable Long groupId,
            @RequestBody TontineGroup updatedGroup) {

        return ResponseEntity.ok(tontineService.fullUpdateGroup(groupId, updatedGroup));
    }

    // Suppression
    @DeleteMapping({"/{groupId}", "/{groupId}/"})
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        tontineService.deleteGroup(groupId);
        return ResponseEntity.noContent().build();
    }
}
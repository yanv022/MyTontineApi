package com.vougue.tontineApp.service;

import com.vougue.tontineApp.model.TontineGroup;
import com.vougue.tontineApp.model.Member;
import com.vougue.tontineApp.model.dto.GroupUpdateDTO;
import com.vougue.tontineApp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TontineService {

    private final GroupRepository groupRepository;



    // Méthode 1 : Valide
    public List<TontineGroup> getAllGroups() {
        return groupRepository.findAll(); // ✅ Bon return
    }

    // Méthode 2 : Vérifier cette partie
    public void processRotation(Long groupId) {
        TontineGroup group = groupRepository.findGroupWithMembers(groupId);

        Member nextMember = group.getMembers().stream()
                .filter(m -> !m.isHasReceived())
                .min(Comparator.comparing(Member::getId))
                .orElseThrow(() -> new RuntimeException("Aucun membre éligible")); // ✅ Gestion d'erreur

        nextMember.setHasReceived(true);
        group.setRotationOrder(group.getRotationOrder() + 1);
        group.setNextPayout(LocalDate.now().plusWeeks(2));

        groupRepository.save(group);
    }
    public TontineGroup updateGroup(Long groupId, GroupUpdateDTO updateDTO) {
        TontineGroup existingGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Groupe ID " + groupId + " non trouvé"));

        if(updateDTO.getGroupName() != null) {
            existingGroup.setGroupName(updateDTO.getGroupName());
        }

        if(updateDTO.getTotalAmount() != null) {
            existingGroup.setTotalAmount(updateDTO.getTotalAmount());
        }

        if(updateDTO.getNextPayout() != null) {
            existingGroup.setNextPayout(updateDTO.getNextPayout());
        }

        return groupRepository.save(existingGroup);
    }
    public TontineGroup fullUpdateGroup(Long groupId, TontineGroup updatedGroup) {
        TontineGroup existingGroup = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Groupe non trouvé"));

        updatedGroup.setId(existingGroup.getId());
        return groupRepository.save(updatedGroup);
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    // Méthode pour récupérer un groupe par son ID
    public TontineGroup getGroupById(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Groupe ID " + groupId + " non trouvé"));
    }

    public TontineGroup createGroup(TontineGroup newGroup) {
        return groupRepository.save(newGroup);
    }
}
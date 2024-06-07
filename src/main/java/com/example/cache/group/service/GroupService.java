package com.example.cache.group.service;

import com.example.cache.group.domain.Group;
import com.example.cache.group.domain.GroupSave;
import com.example.cache.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    @Transactional
    public long saveGroup(GroupSave groupSave) {
        return groupRepository.save(groupSave);
    }

    @Cacheable(key = "#groupId", condition = "#groupId > 0", cacheManager = "cacheManager", cacheNames = "group")
    @Transactional(readOnly = true)
    public Group findGroup(long groupId) {
        return groupRepository.findById(groupId);
    }
}

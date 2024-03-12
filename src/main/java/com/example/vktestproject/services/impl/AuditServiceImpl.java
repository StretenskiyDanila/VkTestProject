package com.example.vktestproject.services.impl;

import com.example.vktestproject.entity.Audit;
import com.example.vktestproject.repository.AuditRepository;
import com.example.vktestproject.services.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public void save(String query, boolean isAccess) {
        var audit = Audit.builder()
                .query(query)
                .access(isAccess ? "Имеет доступ" : "Не имеет доступа")
                .build();
        auditRepository.save(audit);
    }

    @Override
    public List<Audit> getAllAudit() {
        return auditRepository.findAll();
    }
}

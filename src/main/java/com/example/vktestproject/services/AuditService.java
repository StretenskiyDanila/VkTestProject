package com.example.vktestproject.services;

import com.example.vktestproject.entity.Audit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuditService {

    void save(String query, boolean isAccess);
    List<Audit> getAllAudit();

}

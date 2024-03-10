package com.laurentiuspilca.ssia.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.laurentiuspilca.ssia.model.Document;
import com.laurentiuspilca.ssia.repositories.DocumentRepository;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {

    private final DocumentRepository documentRepository;

    public DocumentsPermissionEvaluator(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object target,
                                 Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        String code = targetId.toString();
        Document document = documentRepository.findDocument(code);

        String p = (String) permission;


        boolean admin =
                authentication.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals(p));

        return admin || document.getOwner().equals(authentication.getName());
    }
}

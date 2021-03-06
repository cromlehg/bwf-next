package com.blockwit.bwf.models.service;

import com.blockwit.bwf.models.entity.Permission;
import com.blockwit.bwf.models.repository.PermissionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Service
public class PermissionsService {

    public final static String PERMISSION_ADMIN = "ADMIN";

    public final static String PERMISSION_USER = "USER";

    private final PermissionRepository permissionRepository;

    public PermissionsService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    private static Permission getOrCreatePermission(PermissionRepository permissionRepository, String name) {
        return permissionRepository.findByName(name).orElseGet(() -> {
            Permission permission = new Permission();
            permission.setName(name);
            return permissionRepository.save(permission);
        });
    }

    public Set<Permission> getDefaultAdminPermissions() {
        return new HashSet<>(List.of(getOrCreatePermission(permissionRepository, PERMISSION_ADMIN)));
    }

    public Set<Permission> getDefaultUserPermissions() {
        return new HashSet<>(List.of(getOrCreatePermission(permissionRepository, PERMISSION_USER)));
    }

}

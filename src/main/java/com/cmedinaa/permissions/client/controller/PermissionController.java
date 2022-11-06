package com.cmedinaa.permissions.client.controller;


import com.cmedinaa.permissions.client.conversion.dto.PermissionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PermissionController {

    public ResponseEntity<List<PermissionDTO>> getPermissions();
}

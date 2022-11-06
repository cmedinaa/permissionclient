package com.cmedinaa.permissions.client.controller.impl;

import com.cmedinaa.permissions.client.controller.PermissionController;
import com.cmedinaa.permissions.client.conversion.dto.PermissionDTO;
import com.cmedinaa.permissions.client.services.ResourceProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionControllerImpl implements PermissionController {

    @Autowired
    ResourceProxyService resourceProxyService;

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<PermissionDTO>> getPermissions() {
        return Optional.ofNullable(resourceProxyService.getPermissions())
                .filter(permissions -> !CollectionUtils.isEmpty(permissions))
                .map(permissions -> ResponseEntity.ok(permissions))
                .orElse(ResponseEntity.internalServerError().build())
        ;
    }
}

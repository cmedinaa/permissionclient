package com.cmedinaa.permissions.client.services;

import com.cmedinaa.permissions.client.conversion.dto.PermissionDTO;

import java.util.List;

public interface ResourceProxyService {

    public List<PermissionDTO> getPermissions();
}

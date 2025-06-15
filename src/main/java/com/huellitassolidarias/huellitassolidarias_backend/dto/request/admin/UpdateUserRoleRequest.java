package com.huellitassolidarias.huellitassolidarias_backend.dto.request.admin;

import com.huellitassolidarias.huellitassolidarias_backend.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRoleRequest {
    private Role role;
}

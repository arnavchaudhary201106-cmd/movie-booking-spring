package com.example.practice.Controller;

import com.example.practice.Role;
import com.example.practice.dto.RoleRequest;
import com.example.practice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping
    public Role createRole(@RequestBody RoleRequest roleRequest) {
        return roleService.createRole(roleRequest);
    }
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
   @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
   }



}

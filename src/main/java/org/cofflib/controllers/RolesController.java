package org.cofflib.controllers;

import org.cofflib.dto.RolesDto;
import org.cofflib.services.RolesCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/roles")
public class RolesController {
    private final RolesCRUDService rolesService;

    public RolesController(RolesCRUDService rolesService) {
        this.rolesService = rolesService;
    }


    @GetMapping("/{id}")
    public RolesDto getRolesById(@PathVariable Integer id) {
        return rolesService.getById(id);
    }

    @GetMapping
    public Collection<RolesDto> getAllRoles() {
        return rolesService.getAll();
    }


    @PostMapping
    public void createRoles(@RequestBody RolesDto rolesDto) {
        rolesService.create(rolesDto);
    }

    @PutMapping("/{id}")
    public void updateRoles(@PathVariable Integer id, @RequestBody RolesDto rolesDto) {
        rolesService.update(rolesDto);
    }


    @DeleteMapping("/{id}")
    public void deleteRoles(@PathVariable Integer id) {
        rolesService.delete(id);

    }
}

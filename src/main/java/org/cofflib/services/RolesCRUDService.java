package org.cofflib.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cofflib.dto.RolesDto;
import org.cofflib.entity.Roles;
import org.cofflib.repositories.RolesRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@AllArgsConstructor
@Service
@Slf4j
public class RolesCRUDService implements CRUDService<RolesDto> {

    private final RolesRepository repository;

    @Override
    public RolesDto getById(Integer id) {
        log.info("Get byId " + id);
        Roles roles = repository.findById(id).orElseThrow();
        return mapToDto(roles);
    }

    @Override
    public Collection<RolesDto> getAll() {
        log.info("Get All");
        return repository.findAll()
                .stream()
                .map(RolesCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(RolesDto item) {
        log.info("Create");
        Roles roles = mapToEntity(item);
        repository.save(roles);
    }

    @Override
    public void update(RolesDto item) {
        log.info("Update");
        Roles roles = mapToEntity(item);
        repository.save(roles);

    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        repository.deleteById(id);

    }

    public static RolesDto mapToDto(Roles roles) {
        RolesDto rolesDto = new RolesDto();
        rolesDto.setId(roles.getId());
        rolesDto.setName(roles.getName());
        return rolesDto;
    }

    public static Roles mapToEntity(RolesDto rolesDto) {
        Roles roles = new Roles();
        roles.setId(rolesDto.getId());
        roles.setName(rolesDto.getName());
        return roles;
    }
}

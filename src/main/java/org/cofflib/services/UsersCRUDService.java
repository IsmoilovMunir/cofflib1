package org.cofflib.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cofflib.dto.UsersDto;
import org.cofflib.entity.Categories;
import org.cofflib.entity.Roles;
import org.cofflib.entity.Users;
import org.cofflib.repositories.RolesRepository;
import org.cofflib.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor
@Service
@Slf4j
public class UsersCRUDService implements CRUDService<UsersDto> {
    private final UsersRepository usersRepository;
    private final RolesRepository repository;


    @Override
    public UsersDto getById(Integer id) {
        log.info("Get byId " + id);
        Users users = usersRepository.findById(id).orElseThrow();
        return mapToDto(users);
    }

    @Override
    public Collection<UsersDto> getAll() {
        log.info("Get All");
        return usersRepository.findAll()
                .stream()
                .map(UsersCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(UsersDto usersDto) {
        log.info("Create ");
        Users users = mapToEntity(usersDto);
        Integer rolesId = usersDto.getRoleId();
        Roles roles = repository.findById(rolesId).orElseThrow();
        users.setRoles(roles);
        usersRepository.save(users);

    }

    @Override
    public void update(UsersDto item) {
        log.info("Update");
        Users users = mapToEntity(item);
        usersRepository.save(users);

    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        usersRepository.deleteById(id);

    }

    public static UsersDto mapToDto(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setFirstName(users.getFirstName());
        usersDto.setLastName(users.getLastName());
        usersDto.setRoleId(RolesCRUDService.mapToDto(users.getRoles()).getId());
        return usersDto;

    }

    public static Users mapToEntity(UsersDto usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());

        return users;
    }
}

package org.cofflib.services;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.cofflib.dto.UsersDto;
import org.cofflib.entity.Users;
import org.cofflib.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Collection;
import java.util.TreeMap;

@AllArgsConstructor
@Service
@Slf4j
public class UsersCRUDService implements CRUDService<UsersDto> {
    private final UsersRepository repository;

    @Override
    public UsersDto getById(Integer id) {
        log.info("Get byId " + id);
        Users users = repository.findById(id).orElseThrow();
        return mapToDto(users);
    }

    @Override
    public Collection<UsersDto> getAll() {
        log.info("Get All");
        return repository.findAll()
                .stream()
                .map(UsersCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(UsersDto item) {
        log.info("Create ");
        Users users = mapToEntity(item);
        repository.save(users);

    }

    @Override
    public void update(UsersDto item) {
        log.info("Update");
        Users users = mapToEntity(item);
        repository.save(users);

    }

    @Override
    public void delete(Integer id) {
        log.info("Delete");
        repository.deleteById(id);

    }

    public static UsersDto mapToDto(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setFirstName(users.getFirstName());
        usersDto.setLastName(users.getLastName());
        usersDto.setRoleId(users.getRoleId());
        return usersDto;

    }

    public static Users mapToEntity(UsersDto usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setFirstName(usersDto.getFirstName());
        users.setLastName(usersDto.getLastName());
        users.setRoleId(usersDto.getRoleId());

        return users;
    }
}

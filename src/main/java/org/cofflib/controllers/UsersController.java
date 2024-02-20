package org.cofflib.controllers;

import org.cofflib.dto.UsersDto;
import org.cofflib.services.UsersCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("users")
public class UsersController {
    private final UsersCRUDService usersService;

    public UsersController(UsersCRUDService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public UsersDto getUsersById(@PathVariable Integer id){
        return usersService.getById(id);
    }

    @GetMapping
    public Collection<UsersDto> getAllUsers(){
        return usersService.getAll();
    }

    @PostMapping
    public void createUsers(@RequestBody UsersDto usersDto){
        usersService.create(usersDto);
    }

    @PutMapping("/{id}")
    public void updateUsers(@PathVariable Integer id, @RequestBody UsersDto usersDto ){
        usersService.update(usersDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUsers(@PathVariable Integer id){
        usersService.delete(id);
    }
}

package org.cofflib.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer roleId;

}

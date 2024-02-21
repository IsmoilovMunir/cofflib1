package org.cofflib.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ExpensesDto {
    private Integer id;
    private String name;
    private Double cost;
    private Integer paymentId;
    private Integer categoriesId;
    private Integer userId;
    private Date createdAt;
}

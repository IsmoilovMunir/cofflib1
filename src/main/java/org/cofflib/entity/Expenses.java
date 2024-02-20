package org.cofflib.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Getter
@Setter
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "payment_id")
    private Integer paymentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "category_id")
    private Categories categories;

    @Column(name = "user_id")
    private Integer userId;

    @CreationTimestamp
    @Column(name = "creation_at")
    private LocalDateTime createdAt;


}

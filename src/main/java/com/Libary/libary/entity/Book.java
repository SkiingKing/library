package com.Libary.libary.entity;

import lombok.*;

import javax.persistence.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String description;

    private String book_category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

}

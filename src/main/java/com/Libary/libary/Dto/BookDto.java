package com.Libary.libary.Dto;


import com.Libary.libary.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {


    private Long id;
    @NotNull
    private String name;

    @NotNull
    private String author;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    private String book_category;
}

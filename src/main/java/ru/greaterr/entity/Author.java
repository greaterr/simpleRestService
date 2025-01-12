package ru.greaterr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
}

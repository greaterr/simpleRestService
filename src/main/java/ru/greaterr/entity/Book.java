package ru.greaterr.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@NoArgsConstructor
@DynamicUpdate
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    private Integer year;
}

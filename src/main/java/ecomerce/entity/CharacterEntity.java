package ecomerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "characters")
@Data
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;
}

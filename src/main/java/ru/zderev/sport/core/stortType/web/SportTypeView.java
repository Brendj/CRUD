package ru.zderev.sport.core.stortType.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_sport_type")
@NoArgsConstructor
public class SportTypeView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}

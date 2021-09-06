package ru.zderev.sport.core.team.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_team")
@NoArgsConstructor
public class TeamView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "sport_type_id")
    private Long sportTypeId;
}

package ru.zderev.sport.core.player.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_player")
@NoArgsConstructor
public class PlayerView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String firstData;

    private String lastData;

    @Column(name = "sport_type_id")
    private Long sportTypeId;

    @Column(name = "team_id")
    private Long teamId;

}

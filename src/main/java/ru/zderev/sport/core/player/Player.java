package ru.zderev.sport.core.player;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zderev.sport.core.stortType.SportType;
import ru.zderev.sport.core.team.Team;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_player")
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  name;

    private String firstData;

    private String lastData;

    @ManyToOne
    private Team team;

    @ManyToOne
    private SportType sportType;

}

package ru.zderev.sport.core.stortType;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zderev.sport.core.player.Player;
import ru.zderev.sport.core.team.Team;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "app_sport_type")
@NoArgsConstructor
public class SportType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "sportType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Team> teams;

    @OneToMany(mappedBy = "sportType", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Player> players;

}

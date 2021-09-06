package ru.zderev.sport.core.team;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.zderev.sport.core.player.Player;
import ru.zderev.sport.core.stortType.SportType;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "app_team")
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private SportType sportType;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Player> players;

}

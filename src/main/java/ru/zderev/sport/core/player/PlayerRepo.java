package ru.zderev.sport.core.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepo extends JpaRepository<Player, String> {

    Player getById(Long id);

    List<Player> findAllByTeamId(Long id);

    List<Player> findAllBySportTypeId(Long id);

    List<Player> findByFirstDataAfterAndLastDataBefore(String firstData, String lastData);

    void deleteById(Long id);

    void deleteAllByTeamId(Long Id);

    void deleteAllBySportTypeId(Long id);

}

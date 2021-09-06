package ru.zderev.sport.core.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zderev.sport.core.team.Team;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<Team, String> {

    Team getById(Long id);

    List<Team> findAllBySportTypeId(Long id);

    void deleteById(Long id);

    void deleteAllBySportTypeId(Long id);

}

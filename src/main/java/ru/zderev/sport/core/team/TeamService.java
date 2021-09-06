package ru.zderev.sport.core.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.zderev.sport.core.player.PlayerRepo;
import ru.zderev.sport.core.stortType.SportType;
import ru.zderev.sport.core.stortType.SportTypeService;
import ru.zderev.sport.core.team.convertor.TeamToTeamViewConverter;
import ru.zderev.sport.core.team.web.TeamBaseReq;
import ru.zderev.sport.core.team.web.TeamView;
import ru.zderev.sport.exception.sport.SportTypeEmptyException;
import ru.zderev.sport.exception.sport.SportTypeNameEmptyException;
import ru.zderev.sport.exception.team.TeamEmptyException;
import ru.zderev.sport.exception.team.TeamIdEmptyException;
import ru.zderev.sport.exception.team.TeamNameEmptyException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepo teamRepo;
    private final SportTypeService sportTypeService;
    private final PlayerRepo playerRepo;

    @Autowired
    public TeamService(TeamRepo teamRepo, SportTypeService sportTypeService, PlayerRepo playerRepo) {
        this.teamRepo = teamRepo;
        this.sportTypeService = sportTypeService;
        this.playerRepo = playerRepo;
    }

    public void create(String name, Long sportTypeId) {
        if (name == null || name.isEmpty()) throw new TeamNameEmptyException();
        if (sportTypeId == null) throw new SportTypeNameEmptyException();
        final SportType sportType = sportTypeService.getById(sportTypeId);
        if (sportType == null) throw new TeamEmptyException();
        final Team team = new Team();
        team.setName(name);
        team.setSportType(sportType);
        teamRepo.save(team);
    }

    public List<Team> findAll(Pageable pageable) {
        return teamRepo.findAll(pageable.getSort());
    }

    public List<Team> findAllBySportTypeId(Long id) {
        if (id == null || sportTypeService.getById(id) == null) throw new TeamIdEmptyException();
        sportTypeService.getById(id);
        return teamRepo.findAllBySportTypeId(id);
    }

    public Team getById(Long id) {
        if (id == null || teamRepo.getById(id) == null) throw new TeamIdEmptyException();
        return teamRepo.getById(id);
    }

    public TeamView update(Team team, TeamBaseReq req) {
        if (team == null || req == null) {
            throw new SportTypeEmptyException();
        }
        Team newTeam = this.prepare(team,req);
        Team tournamentSave = teamRepo.save(newTeam);
        return TeamToTeamViewConverter.teamToView(tournamentSave);
    }

    @Transactional
    public void deleteById(Long id ) {
        if (id == null || teamRepo.getById(id) == null) throw new TeamIdEmptyException();
        playerRepo.deleteAllByTeamId(id);
        teamRepo.deleteById(id);
    }

    public Team prepare(Team team, TeamBaseReq teamBaseReq){
        team.setName(teamBaseReq.getName());
        return team;
    }

}

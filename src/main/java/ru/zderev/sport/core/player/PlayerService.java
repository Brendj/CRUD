package ru.zderev.sport.core.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zderev.sport.core.history.HistoryService;
import ru.zderev.sport.core.player.convertor.PlayerToPlayerViewConverter;
import ru.zderev.sport.core.player.web.PlayerBaseReq;
import ru.zderev.sport.core.player.web.PlayerView;
import ru.zderev.sport.core.stortType.SportType;
import ru.zderev.sport.core.stortType.SportTypeService;
import ru.zderev.sport.core.team.Team;
import ru.zderev.sport.core.team.TeamService;
import ru.zderev.sport.exception.player.PlayerIdEmptyException;
import ru.zderev.sport.exception.player.PlayerNameEmptyException;
import ru.zderev.sport.exception.sport.SportTypeEmptyException;
import ru.zderev.sport.exception.sport.SportTypeNameEmptyException;
import ru.zderev.sport.exception.team.TeamEmptyException;
import ru.zderev.sport.exception.team.TeamNameEmptyException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepo playerRepo;
    private final HistoryService historyService;
    private final SportTypeService sportTypeService;
    private final TeamService teamService;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, HistoryService historyService, SportTypeService sportTypeService, TeamService teamService) {
        this.playerRepo = playerRepo;
        this.historyService = historyService;
        this.sportTypeService = sportTypeService;
        this.teamService = teamService;
    }

    public Player create(String name, String firstData, String lastData, Long teamId, Long sportTypeId) {
        if (name == null || name.isEmpty()) throw new PlayerNameEmptyException();
        if (teamId == null || teamService.getById(teamId) == null) throw new TeamNameEmptyException();
        if (sportTypeId == null || sportTypeService.getById(sportTypeId) == null) throw new SportTypeNameEmptyException();
        final SportType sportType = sportTypeService.getById(sportTypeId);
        if (sportType == null) throw new SportTypeEmptyException();
        final Team team = teamService.getById(teamId);
        if (team == null) throw new TeamEmptyException();
        if(firstData == null || firstData.isEmpty()) throw new SportTypeNameEmptyException();
        if(lastData == null || lastData.isEmpty()) throw new SportTypeNameEmptyException();
        final Player player = new Player();
        historyService.create(name, firstData, lastData, teamId, sportTypeId);
        player.setName(name);
        player.setFirstData(firstData);
        player.setLastData(lastData);
        player.setSportType(sportType);
        player.setTeam(team);
        return playerRepo.save(player);
    }

    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    public Player findById(Long id) {
        if (id == null || playerRepo.getById(id) == null) throw new PlayerIdEmptyException();
        return playerRepo.getById(id);
    }

    public List<Player> findAllByTeamId(Long id) {
        if (id == null || teamService.getById(id) == null) throw new PlayerIdEmptyException();
        Team team = teamService.getById(id);
        if (team == null) throw new TeamEmptyException();
        return playerRepo.findAllByTeamId(id);
    }

    public List<Player> findAllBySportTypeId(Long id) {
        if (id == null || sportTypeService.getById(id) == null) throw new PlayerIdEmptyException();
        SportType sportType = sportTypeService.getById(id);
        if (sportType == null) throw new SportTypeEmptyException();
        return playerRepo.findAllBySportTypeId(id);
    }

    public List<Player> findByBetweenFirstDataAndLastData(String firstData, String lastData) {
        return playerRepo.findByFirstDataAfterAndLastDataBefore(firstData, lastData);
    }

    public PlayerView update(Player player, PlayerBaseReq req) {
        if (player == null || req == null) {
            throw new SportTypeEmptyException();
        }
        Player newPlayer = this.prepare(player,req);
        Player tournamentSave = playerRepo.save(newPlayer);
        return PlayerToPlayerViewConverter.playerToView(tournamentSave);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || playerRepo.getById(id) == null) throw new PlayerIdEmptyException();
        playerRepo.deleteById(id);
    }

    public Player prepare(Player player, PlayerBaseReq teamBaseReq){
        player.setName(teamBaseReq.getName());
        return player;
    }

}

package ru.zderev.sport.core.stortType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import ru.zderev.sport.core.player.PlayerRepo;
import ru.zderev.sport.core.stortType.convertor.SportTypeToSportTypeViewConverter;
import ru.zderev.sport.core.stortType.web.SportTypeBaseReq;
import ru.zderev.sport.core.stortType.web.SportTypeView;
import ru.zderev.sport.core.team.TeamRepo;
import ru.zderev.sport.exception.sport.SportTypeEmptyException;
import ru.zderev.sport.exception.sport.SportTypeIdEmptyException;
import ru.zderev.sport.exception.sport.SportTypeNameEmptyException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SportTypeService {

    private final SportTypeRepo sportTypeRepo;
    private final PlayerRepo playerRepo;
    private final TeamRepo teamRepo;

    @Autowired
    public SportTypeService(SportTypeRepo sportTypeRepo, PlayerRepo playerRepo, TeamRepo teamRepo) {
        this.sportTypeRepo = sportTypeRepo;
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    public void create(String name) {
        if (name == null || name.isEmpty()) throw new SportTypeNameEmptyException();
        SportType sportType = new SportType();
        sportType.setName(name);
        sportTypeRepo.save(sportType);
    }

    public List<SportType> findAll(Pageable pageable) {
        return sportTypeRepo.findAll(pageable.getSort());
    }

    public SportType getById(Long id){
        if (id == null || sportTypeRepo.getById(id) == null) throw new SportTypeIdEmptyException();
        return sportTypeRepo.getById(id);
    }

    public SportTypeView update(SportType sportType, SportTypeBaseReq req) {
        if (sportType == null || req == null) {
            throw new SportTypeEmptyException();
        }
        SportType newSportType = this.prepare(sportType,req);
        SportType tournamentSave = sportTypeRepo.save(newSportType);
        return SportTypeToSportTypeViewConverter.sportTypeToView(tournamentSave);
    }

    @Transactional
    public void deleteById(Long id) {
        if (id == null || sportTypeRepo.getById(id) == null) throw new SportTypeIdEmptyException();
        playerRepo.deleteAllBySportTypeId(id);
        teamRepo.deleteAllBySportTypeId(id);
        sportTypeRepo.deleteById(id);
    }

    public SportType prepare(SportType sportType, SportTypeBaseReq sportTypeBaseReq){
        sportType.setName(sportTypeBaseReq.getName());
        return sportType;
    }

}

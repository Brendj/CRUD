package ru.zderev.sport.core.team.convertor;

import lombok.experimental.UtilityClass;
import ru.zderev.sport.core.team.Team;
import ru.zderev.sport.core.team.web.TeamView;

@UtilityClass
public class TeamToTeamViewConverter {
    public static TeamView teamToView(Team team) {
        if (team == null) return null;
        TeamView teamDto = new TeamView();
        teamDto.setName(team.getName());
        teamDto.setId(team.getId());
        teamDto.setSportTypeId(team.getSportType().getId());
        return teamDto;
    }
}

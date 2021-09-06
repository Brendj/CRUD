package ru.zderev.sport.core.team.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.zderev.sport.core.team.Team;
import ru.zderev.sport.core.team.TeamService;
import ru.zderev.sport.core.team.convertor.TeamToTeamViewConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @PostMapping
    public void create(@RequestBody TeamView teamView) {
        teamService.create(teamView.getName(), teamView.getSportTypeId());
    }

    @GetMapping
    @ResponseBody
    public List<TeamView> findAllBySportTypeId(
            @RequestParam(required = false) Long sportTypeId,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        if (sportTypeId == null) {
            return teamService.findAll(pageable)
                    .stream()
                    .map(TeamToTeamViewConverter::teamToView)
                    .collect(Collectors.toList());
        }
        return teamService.findAllBySportTypeId(sportTypeId)
                .stream()
                .map(TeamToTeamViewConverter::teamToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TeamView findById(@PathVariable Long id) {
        return TeamToTeamViewConverter.teamToView(teamService.getById(id));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public TeamView updateTeam(@PathVariable Long id,
                                         @RequestBody TeamBaseReq req) {
        Team team = teamService.getById(id);
        return teamService.update(team, req);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        teamService.deleteById(id);
    }

}
package ru.zderev.sport.core.player.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.zderev.sport.core.player.Player;
import ru.zderev.sport.core.player.PlayerService;
import ru.zderev.sport.core.player.convertor.PlayerToPlayerViewConverter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayerController  {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public void create(@RequestBody PlayerView playerView) {
        playerService.create(playerView.getName(), playerView.getFirstData(), playerView.getLastData(), playerView.getTeamId(), playerView.getSportTypeId());
    }

    @GetMapping
    public List<PlayerView> findFilter(
            @RequestParam(required = false) Long teamId,
            @RequestParam(required = false) Long sportTypeId,
            @RequestParam(required = false) String firstData,
            @RequestParam(required = false) String lastData,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        if(teamId != null) {
            return playerService.findAllByTeamId(teamId)
                    .stream()
                    .map(PlayerToPlayerViewConverter::playerToView)
                    .collect(Collectors.toList());
        } else if(sportTypeId != null) {
            return playerService.findAllBySportTypeId(sportTypeId)
                    .stream()
                    .map(PlayerToPlayerViewConverter::playerToView)
                    .collect(Collectors.toList());
        } else if(firstData != null || lastData != null) {
            return playerService.findByBetweenFirstDataAndLastData(firstData, lastData)
                    .stream()
                    .map(PlayerToPlayerViewConverter::playerToView)
                    .collect(Collectors.toList());
        }
        return playerService.findAll(pageable)
                .stream()
                .map(PlayerToPlayerViewConverter::playerToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlayerView findById(@PathVariable Long id) {
        return PlayerToPlayerViewConverter.playerToView(playerService.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public PlayerView updatePlayer(@PathVariable Long id,
                                    @RequestBody PlayerBaseReq req) {
        Player player = playerService.findById(id);
        return playerService.update(player, req);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        playerService.deleteById(id);
    }

}



package ru.zderev.sport.core.player.convertor;

import lombok.experimental.UtilityClass;
import ru.zderev.sport.core.player.Player;
import ru.zderev.sport.core.player.web.PlayerView;

@UtilityClass
public class PlayerToPlayerViewConverter {
    public static PlayerView playerToView(Player player) {
        if (player == null) return null;
        PlayerView playerDto = new PlayerView();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setFirstData(player.getFirstData());
        playerDto.setLastData(player.getLastData());
        playerDto.setSportTypeId(player.getSportType().getId());
        playerDto.setTeamId(player.getTeam().getId());
        return playerDto;
    }
}

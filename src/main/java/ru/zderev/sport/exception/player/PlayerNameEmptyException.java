package ru.zderev.sport.exception.player;

import ru.zderev.sport.exception.AbstractException;

import javax.persistence.EntityNotFoundException;

public class PlayerNameEmptyException extends AbstractException {

    public PlayerNameEmptyException() {
        super("Player name is empty!");
        throw new EntityNotFoundException("Player.NotFound");
    }

}

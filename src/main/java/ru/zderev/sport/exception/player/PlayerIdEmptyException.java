package ru.zderev.sport.exception.player;

import ru.zderev.sport.exception.AbstractException;
import ru.zderev.sport.util.MessageUtil;

import javax.persistence.EntityNotFoundException;

public class PlayerIdEmptyException extends AbstractException {

    private final MessageUtil messageUtil;

    public PlayerIdEmptyException() {
        super("Player id is empty!");
        throw new EntityNotFoundException("Player.NotFound");
    }

}

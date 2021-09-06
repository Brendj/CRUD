package ru.zderev.sport.exception.team;

import ru.zderev.sport.exception.AbstractException;

import javax.persistence.EntityNotFoundException;

public class TeamEmptyException extends AbstractException {
    public TeamEmptyException() {
        super("Team is empty!");
        throw new EntityNotFoundException("Team.NotFound");
    }
}


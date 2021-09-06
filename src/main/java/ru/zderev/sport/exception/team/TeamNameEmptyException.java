package ru.zderev.sport.exception.team;

import ru.zderev.sport.exception.AbstractException;

import javax.persistence.EntityNotFoundException;

public class TeamNameEmptyException extends AbstractException {
    public TeamNameEmptyException() {
        super("Sport type name is empty!");
        throw new EntityNotFoundException("Team.NotFound");
    }
}

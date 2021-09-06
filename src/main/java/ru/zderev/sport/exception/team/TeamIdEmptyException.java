package ru.zderev.sport.exception.team;

import ru.zderev.sport.exception.AbstractException;

import javax.persistence.EntityNotFoundException;

public class TeamIdEmptyException extends AbstractException {
    public TeamIdEmptyException() {
        super("Sport type id is empty!");
        throw new EntityNotFoundException("Team.NotFound");
    }
}

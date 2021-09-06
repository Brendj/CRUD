package ru.zderev.sport.exception.sport;

import ru.zderev.sport.exception.AbstractException;

import javax.persistence.EntityNotFoundException;

public class SportTypeNameEmptyException extends AbstractException {

    public SportTypeNameEmptyException() {
        super("Sport type name is empty!");
        throw new EntityNotFoundException("SportType.NotFound");
    }

}

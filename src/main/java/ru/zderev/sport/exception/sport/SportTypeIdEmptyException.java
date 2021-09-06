package ru.zderev.sport.exception.sport;

import ru.zderev.sport.exception.AbstractException;
import ru.zderev.sport.exception.EntityNotFoundException;

public class SportTypeIdEmptyException extends AbstractException {

    public SportTypeIdEmptyException() {
        super("Sport type id is empty!");
        throw new EntityNotFoundException("SportType.NotFound");
    }

}

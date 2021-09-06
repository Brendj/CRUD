package ru.zderev.sport.exception.sport;

import ru.zderev.sport.exception.AbstractException;
import javax.persistence.EntityNotFoundException;

public class SportTypeEmptyException extends AbstractException {
    public SportTypeEmptyException() {
        super("Sport type id is empty!");
        throw new EntityNotFoundException("SportType.NotFound");
    }
}

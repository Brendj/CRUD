package ru.zderev.sport.core.stortType.convertor;

import lombok.experimental.UtilityClass;
import ru.zderev.sport.core.stortType.SportType;
import ru.zderev.sport.core.stortType.web.SportTypeView;

@UtilityClass
public class SportTypeToSportTypeViewConverter {
    public static SportTypeView sportTypeToView(SportType sportType) {
        if (sportType == null) return null;
        SportTypeView sportTypeView = new SportTypeView();
        sportTypeView.setName(sportType.getName());
        sportTypeView.setId(sportType.getId());
        return sportTypeView;
    }
}

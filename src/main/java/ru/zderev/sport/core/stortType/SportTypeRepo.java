package ru.zderev.sport.core.stortType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportTypeRepo extends JpaRepository<SportType, String> {

    SportType getById(Long id);

    void deleteById(Long id);

}

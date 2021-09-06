package ru.zderev.sport.core.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<History, String> {

}

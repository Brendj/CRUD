package ru.zderev.sport.core.history;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    final private HistoryRepo historyRepo;

    public HistoryService(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    public void save(History history) {
        if (history == null) return;
        historyRepo.save(history);
    }

    public History create(String name, String firstData, String lastData, Long teamId, Long sportTypeId) {
        final History history = new History();
        history.setName(name);
        history.setFirstData(firstData);
        history.setLastData(lastData);
        history.setSportTypeId(sportTypeId);
        history.setTeamId(teamId);
        return historyRepo.save(history);
    }

    public List<History> findAll() {
        return historyRepo.findAll();
    }

}

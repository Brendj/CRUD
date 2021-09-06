package ru.zderev.sport.core.history.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zderev.sport.core.history.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryController {
    final private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    @PostMapping
    public void create(@RequestBody HistoryView historydto) {
        historyService.create(historydto.getName(), historydto.getFirstData(), historydto.getLastData(), historydto.getTeamId(), historydto.getSportTypeId());
    }
}
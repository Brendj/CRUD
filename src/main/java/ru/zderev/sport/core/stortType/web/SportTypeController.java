package ru.zderev.sport.core.stortType.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.zderev.sport.core.stortType.SportType;
import ru.zderev.sport.core.stortType.SportTypeService;
import ru.zderev.sport.core.stortType.convertor.SportTypeToSportTypeViewConverter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/sport-type")
public class SportTypeController {

    private final SportTypeService sportTypeService;

    public SportTypeController(SportTypeService sportTypeService) {
        this.sportTypeService = sportTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody String name) {
        sportTypeService.create(name);
    }

    @GetMapping
    @ResponseBody
    public List<SportTypeView> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return sportTypeService.findAll(pageable)
                .stream()
                .map(SportTypeToSportTypeViewConverter::sportTypeToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public SportTypeView getById(@PathVariable Long id) {
        return SportTypeToSportTypeViewConverter.sportTypeToView(sportTypeService.getById(id));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public SportTypeView updateSportType(@PathVariable Long id,
                                         @RequestBody SportTypeBaseReq req) {
        SportType sportType = sportTypeService.getById(id);
        return sportTypeService.update(sportType, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteById(@PathVariable Long id) {
        sportTypeService.deleteById(id);
    }

}

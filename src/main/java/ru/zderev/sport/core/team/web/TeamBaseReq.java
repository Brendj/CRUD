package ru.zderev.sport.core.team.web;

import lombok.Data;
import ru.zderev.sport.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TeamBaseReq extends BaseRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Long sportTypeId;

}

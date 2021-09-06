package ru.zderev.sport.core.player.web;

import lombok.Data;
import ru.zderev.sport.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

@Data
public class PlayerBaseReq extends BaseRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String firstData;

    @NotEmpty
    private String lastData;

}

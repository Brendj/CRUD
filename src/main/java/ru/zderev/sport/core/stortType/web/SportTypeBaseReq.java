package ru.zderev.sport.core.stortType.web;

import lombok.Data;
import ru.zderev.sport.base.BaseRequest;

import javax.validation.constraints.NotEmpty;

@Data
public class SportTypeBaseReq extends BaseRequest {

    @NotEmpty
    private String name;

}

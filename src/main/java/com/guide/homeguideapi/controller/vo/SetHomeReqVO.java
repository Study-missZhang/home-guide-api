package com.guide.homeguideapi.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "设置家位置请求参数")
public class SetHomeReqVO {

    @Schema(description = "家的纬度", requiredMode = Schema.RequiredMode.REQUIRED, example = "34.51814221")
    @NotNull
    private BigDecimal homeLatitude;

    @Schema(description = "家的经度", requiredMode = Schema.RequiredMode.REQUIRED, example = "10.15145141")
    @NotNull
    private BigDecimal homeLongitude;

    @Schema(description = "家的描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "美丽的小家")
    private String homeAddress;
}

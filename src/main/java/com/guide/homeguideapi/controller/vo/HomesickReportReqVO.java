package com.guide.homeguideapi.controller.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "想家上报记录请求参数")
public class HomesickReportReqVO {

    @Schema(description = "距离家的直线距离（公里）", example = "1024.50", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal currentDistance;

    @Schema(description = "想家时刻", example = "2024-01-01T12:00:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;
}

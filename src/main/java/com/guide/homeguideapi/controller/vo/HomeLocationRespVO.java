package com.guide.homeguideapi.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 获取家的位置返回响应
 *
 * @author zky
 */
@Schema(description = "家的位置信息")
@Data
public class HomeLocationRespVO {

    @Schema(description = "家的纬度", example = "34.265800")
    private BigDecimal homeLatitude;

    @Schema(description = "家的经度", example = "108.943100")
    private BigDecimal homeLongitude;

    @Schema(description = "家的文字地址描述", example = "陕西省西安市雁塔区xx街道xx号")
    private String homeAddress;
}

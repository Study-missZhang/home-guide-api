package com.guide.homeguideapi.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户基础信息实体类，对应数据库表 user_info
 *
 * @author zky
 */
@Data
@Schema(description = "用户基础信息")
public class UserInfo {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "微信用户唯一标识", example = "oXxxx_xxxxxxxxxxxxxxx")
    private String openid;

    @Schema(description = "绑定的手机号", example = "13800138000")
    private String phone;

    @Schema(description = "家-纬度", example = "34.265800")
    private BigDecimal homeLatitude;

    @Schema(description = "家-经度", example = "108.943100")
    private BigDecimal homeLongitude;

    @Schema(description = "家的文字地址描述", example = "陕西省西安市雁塔区xx街道xx号")
    private String homeAddress;

    @Schema(description = "注册时间", example = "2024-01-01T00:00:00")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2024-01-01T00:00:00")
    private LocalDateTime updateTime;
}

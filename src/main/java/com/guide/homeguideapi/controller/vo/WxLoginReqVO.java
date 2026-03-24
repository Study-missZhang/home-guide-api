package com.guide.homeguideapi.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 微信登录请求参数
 *
 * @author zky
 */
@Data
@Schema(description = "微信登录请求参数")
public class WxLoginReqVO {

    /** 微信登录返回的临时登录凭证 */
    @Schema(description = "小程序调用 wx.login() 获取的临时登录凭证，有效期5分钟", requiredMode = Schema.RequiredMode.REQUIRED, example = "023abc...")
    private String code;
}

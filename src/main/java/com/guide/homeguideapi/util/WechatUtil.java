package com.guide.homeguideapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 微信接口工具类，负责与微信服务器交互
 *
 * @author zky
 */
@Component
public class WechatUtil {

    /** 微信小程序 AppID */
    @Value("${wechat.appid}")
    private String appid;

    /** 微信小程序 AppSecret */
    @Value("${wechat.secret}")
    private String secret;

    /** HTTP 请求工具 */
    private final RestTemplate restTemplate = new RestTemplate();

    /** JSON 解析工具 */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 通过 code 换取微信用户的 openid
     * 微信接口返回 text/plain 类型，先取字符串再手动解析 JSON
     *
     * @param code 小程序 wx.login() 返回的临时登录凭证
     * @return 用户唯一标识 openid
     */
    public String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session"
                + "?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + code
                + "&grant_type=authorization_code";

        // 先以字符串接收，避免 Content-Type: text/plain 导致转换失败
        String response = restTemplate.getForObject(url, String.class);

        try {
            Map<String, Object> result = objectMapper.readValue(response, new TypeReference<>() {});

            // errcode 存在说明微信返回了错误
            if (result.containsKey("errcode")) {
                throw new RuntimeException("微信登录失败，errcode: " + result.get("errcode") + "，errmsg: " + result.get("errmsg"));
            }

            return (String) result.get("openid");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("微信接口响应解析失败: " + response, e);
        }
    }
}

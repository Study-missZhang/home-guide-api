package com.guide.homeguideapi.service;

import com.guide.homeguideapi.controller.vo.WxLoginReqVO;

/**
 * 登录 Service 接口
 *
 * @author zky
 */
public interface LoginService {

    String wxLogin(WxLoginReqVO wxLoginReqVO);
}

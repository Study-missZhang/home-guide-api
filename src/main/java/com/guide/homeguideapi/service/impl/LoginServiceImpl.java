package com.guide.homeguideapi.service.impl;

import com.guide.homeguideapi.controller.vo.WxLoginReqVO;
import com.guide.homeguideapi.dao.UserInfoDao;
import com.guide.homeguideapi.entity.UserInfo;
import com.guide.homeguideapi.service.LoginService;
import com.guide.homeguideapi.util.JwtUtil;
import com.guide.homeguideapi.util.WechatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    /** 用户信息DAO **/
    private final UserInfoDao userInfoDao;

    /** 微信工具类 **/
    private final WechatUtil wechatUtil;

    /** Jwt工具类 **/
    private final JwtUtil jwtUtil;

    @Override
    public String wxLogin(WxLoginReqVO wxLoginReqVO) {

        // 第一步 获取 openId
        String openId = wechatUtil.getOpenId(wxLoginReqVO.getCode());

        // 第二步 查库，没有则自动注册
        UserInfo userInfo = userInfoDao.findByOpenid(openId).orElseGet(() -> {
            UserInfo newUser = new UserInfo();
            newUser.setOpenid(openId);
            userInfoDao.insert(newUser);

            // 重新拿一次自增 id
            return userInfoDao.findByOpenid(openId).orElseThrow();
        });

        // 第三步 生成token
        return jwtUtil.generateToken(userInfo.getId());
    }
}

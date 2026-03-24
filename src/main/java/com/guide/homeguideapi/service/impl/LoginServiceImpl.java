package com.guide.homeguideapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guide.homeguideapi.controller.vo.WxLoginReqVO;
import com.guide.homeguideapi.entity.UserInfo;
import com.guide.homeguideapi.mapper.UserInfoMapper;
import com.guide.homeguideapi.service.LoginService;
import com.guide.homeguideapi.util.JwtUtil;
import com.guide.homeguideapi.util.WechatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 登录 Service 实现类
 *
 * @author zky
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    /** 用户信息 Mapper */
    private final UserInfoMapper userInfoMapper;

    /** 微信工具类 */
    private final WechatUtil wechatUtil;

    /** JWT 工具类 */
    private final JwtUtil jwtUtil;

    /**
     * 微信登录
     * 1. 用 code 换取 openid
     * 2. 查库，不存在则自动注册
     * 3. 生成 JWT token 返回
     *
     * @param wxLoginReqVO 登录请求参数
     * @return JWT token
     */
    @Override
    public String wxLogin(WxLoginReqVO wxLoginReqVO) {
        // 第一步：换取 openid
        String openId = wechatUtil.getOpenId(wxLoginReqVO.getCode());

        // 第二步：查库，没有则自动注册
        UserInfo userInfo = userInfoMapper.selectOne(
                new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getOpenid, openId)
        );

        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setOpenid(openId);
            userInfoMapper.insert(userInfo);
        }

        // 第三步：生成 token
        return jwtUtil.generateToken(userInfo.getId());
    }
}

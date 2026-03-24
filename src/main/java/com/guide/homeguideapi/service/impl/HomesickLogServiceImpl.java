package com.guide.homeguideapi.service.impl;

import com.guide.homeguideapi.dao.HomesickLogDao;
import com.guide.homeguideapi.entity.HomesickLog;
import com.guide.homeguideapi.service.HomesickLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 想家记录 Service 实现类
 *
 * @author zky
 */
@Service
@RequiredArgsConstructor
public class HomesickLogServiceImpl implements HomesickLogService {

    /** 想家记录 DAO */
    private final HomesickLogDao homesickLogDao;

    /**
     * 保存一条想家记录
     *
     * @param homesickLog 想家记录实体
     * @return 影响行数
     */
    @Override
    public int save(HomesickLog homesickLog) {
        return homesickLogDao.insert(homesickLog);
    }

    /**
     * 根据主键删除记录
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return homesickLogDao.deleteById(id);
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键ID
     * @return 想家记录（Optional 包装）
     */
    @Override
    public Optional<HomesickLog> getById(Long id) {
        return homesickLogDao.findById(id);
    }

    /**
     * 根据用户ID查询该用户所有想家记录
     *
     * @param userId 用户ID
     * @return 想家记录列表
     */
    @Override
    public List<HomesickLog> listByUserId(Long userId) {
        return homesickLogDao.findByUserId(userId);
    }

    /**
     * 查询所有想家记录
     *
     * @return 想家记录列表
     */
    @Override
    public List<HomesickLog> listAll() {
        return homesickLogDao.findAll();
    }
}

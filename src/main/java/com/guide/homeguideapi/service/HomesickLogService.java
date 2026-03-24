package com.guide.homeguideapi.service;

import com.guide.homeguideapi.entity.HomesickLog;
import java.util.List;
import java.util.Optional;

/**
 * 想家记录 Service 接口，定义想家记录相关业务逻辑
 *
 * @author zky
 */
public interface HomesickLogService {

    /**
     * 保存一条想家记录
     *
     * @param homesickLog 想家记录实体
     * @return 影响行数
     */
    int save(HomesickLog homesickLog);

    /**
     * 根据主键删除记录
     *
     * @param id 主键ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据主键查询记录
     *
     * @param id 主键ID
     * @return 想家记录（Optional 包装）
     */
    Optional<HomesickLog> getById(Long id);

    /**
     * 根据用户ID查询该用户所有想家记录
     *
     * @param userId 用户ID
     * @return 想家记录列表
     */
    List<HomesickLog> listByUserId(Long userId);

    /**
     * 查询所有想家记录
     *
     * @return 想家记录列表
     */
    List<HomesickLog> listAll();
}

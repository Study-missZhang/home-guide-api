package com.guide.homeguideapi.dao.impl;

import com.guide.homeguideapi.dao.HomesickLogDao;
import com.guide.homeguideapi.entity.HomesickLog;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 想家记录 DAO 实现类，基于 JdbcTemplate 操作 homesick_log 表
 *
 * @author zky
 */
@Repository
@RequiredArgsConstructor
public class HomesickLogDaoImpl implements HomesickLogDao {

    /** JdbcTemplate，用于执行 SQL 操作 */
    private final JdbcTemplate jdbcTemplate;

    /** 结果集映射器，将查询结果自动映射为 HomesickLog 对象 */
    private static final BeanPropertyRowMapper<HomesickLog> ROW_MAPPER =
            BeanPropertyRowMapper.newInstance(HomesickLog.class);

    /**
     * 新增一条想家记录
     * 插入 user_id、current_distance 字段，create_time 由数据库默认填充
     *
     * @param h 想家记录实体
     * @return 影响行数
     */
    @Override
    public int insert(HomesickLog h) {
        String sql = "INSERT INTO homesick_log (user_id, current_distance) VALUES (?, ?)";
        return jdbcTemplate.update(sql, h.getUserId(), h.getCurrentDistance());
    }

    /**
     * 根据主键删除记录
     *
     * @param id 主键ID
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM homesick_log WHERE id=?", id);
    }

    /**
     * 根据主键查询记录
     *
     * @param id 主键ID
     * @return 想家记录（Optional 包装）
     */
    @Override
    public Optional<HomesickLog> findById(Long id) {
        List<HomesickLog> list = jdbcTemplate.query(
                "SELECT * FROM homesick_log WHERE id=?", ROW_MAPPER, id);
        return list.stream().findFirst();
    }

    /**
     * 根据用户ID查询该用户所有想家记录，按时间升序排列
     *
     * @param userId 用户ID
     * @return 想家记录列表
     */
    @Override
    public List<HomesickLog> findByUserId(Long userId) {
        String sql = "SELECT * FROM homesick_log WHERE user_id=? ORDER BY create_time ASC";
        return jdbcTemplate.query(sql, ROW_MAPPER, userId);
    }

    /**
     * 查询所有想家记录
     *
     * @return 想家记录列表
     */
    @Override
    public List<HomesickLog> findAll() {
        return jdbcTemplate.query("SELECT * FROM homesick_log", ROW_MAPPER);
    }
}

package com.example.miaosha.miaosha002.dao;

import com.example.miaosha.miaosha002.entity.UserPassword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserPassword)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-04 22:31:42
 */
@Mapper
public interface UserPasswordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserPassword queryByUserId(Integer id);

    /**
     * 查询指定行数据
     *
     * @param userPassword 查询条件
     * @return 对象列表
     */
    List<UserPassword> queryAllByLimit(UserPassword userPassword);

    /**
     * 统计总行数
     *
     * @param userPassword 查询条件
     * @return 总行数
     */
    long count(UserPassword userPassword);

    /**
     * 新增数据
     *
     * @param userPassword 实例对象
     * @return 影响行数
     */
    int insert(UserPassword userPassword);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPassword> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserPassword> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserPassword> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserPassword> entities);

    /**
     * 修改数据
     *
     * @param userPassword 实例对象
     * @return 影响行数
     */
    int update(UserPassword userPassword);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}


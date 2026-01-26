package com.niuzhi.infrastructure.persistent.dao;

import com.niuzhi.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: IAwardDao
 * @Author itmei
 * @Package com.niuzhi.infrastructure.persistent.dao
 * @Date 2026/1/26 18:04
 * @description: 奖品表Dao
 */
@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}

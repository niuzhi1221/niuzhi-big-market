package com.niuzhi.infrastructure.persistent.dao;

import com.niuzhi.infrastructure.persistent.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: IRuleTreeNodeLineDao
 * @Author niuzhi
 * @Package com.niuzhi.infrastructure.persistent.dao
 * @Date 2026/1/29 11:50
 * @description: 规则树节点连线表DAO
 */
@Mapper
public interface IRuleTreeNodeLineDao {
    List<RuleTreeNodeLine> queryRuleTreeNodeLineListByTreeId(String treeId);
}

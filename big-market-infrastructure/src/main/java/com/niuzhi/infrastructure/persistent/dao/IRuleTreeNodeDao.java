package com.niuzhi.infrastructure.persistent.dao;

import com.niuzhi.infrastructure.persistent.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: IRuleTreeNodeDao
 * @Author niuzhi
 * @Package com.niuzhi.infrastructure.persistent.dao
 * @Date 2026/1/29 11:50
 * @description: 规则树节点表DAO
 */
@Mapper
public interface IRuleTreeNodeDao {
    List<RuleTreeNode> queryRuleTreeNodeListByTreeId(String treeId);
}

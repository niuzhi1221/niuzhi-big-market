package com.niuzhi.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Title: RuleTreeVO
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.model.vo
 * @Date 2026/1/28 19:03
 * @description: 规则树树根
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeVO {
    /** 规则树ID */
    private Integer treeId;
    /** 规则树名称 */
    private String treeName;
    /** 规则树描述 */
    private String treeDesc;
    /** 规则根节点 */
    private String treeRootRuleNode;
    /** 规则节点 */
    private Map<String, RuleTreeNodeVO> treeNodeMap;
}

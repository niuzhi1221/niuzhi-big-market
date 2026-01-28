package com.niuzhi.domain.strategy.service.rule.chain;

/**
 * @Title: AbstractLogicChain
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.rule.chain
 * @Date 2026/1/28 15:52
 * @description:
 */
public abstract class AbstractLogicChain implements ILogicChain {

    private ILogicChain next;

    @Override
    public ILogicChain appendNext(ILogicChain next) {
        this.next = next;
        return next;
    }

    @Override
    public ILogicChain next() {
        return next;
    }

    protected  abstract String ruleModel();
}

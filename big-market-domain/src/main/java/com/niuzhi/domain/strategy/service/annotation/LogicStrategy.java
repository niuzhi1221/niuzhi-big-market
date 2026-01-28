package com.niuzhi.domain.strategy.service.annotation;

import com.niuzhi.domain.strategy.service.rule.factory.DefaultLogicFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title: LogicStrategy
 * @Author niuzhi
 * @Package com.niuzhi.domain.strategy.service.annotation
 * @Date 2026/1/27 15:52
 * @description: 策略自定义注解
 */
@Target({ElementType.TYPE})     //  说明这个注解能用在什么地方，ElementType表示能用在类、接口、枚举上
@Retention(RetentionPolicy.RUNTIME) //  表示该注解在运行时依然存在,可以通过反射读取
public @interface LogicStrategy {       //  声明一个自定义注解类型:LogicStrategy
    DefaultLogicFactory.LogicModel logicMode(); //  使用该注解时, 必须指定一个LogicModel
}

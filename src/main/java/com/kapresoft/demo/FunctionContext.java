package com.kapresoft.demo;

import lombok.Builder;
import lombok.Value;

import org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry;

@Value
@Builder
public class FunctionContext<T, R> {

    Class<R> responseType;
    T requestInstance;
    BeanFactoryAwareFunctionRegistry.FunctionInvocationWrapper fn;

    public R apply() {
        return (R) fn.apply(requestInstance);
    }

}

/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yellowsneakers.core.secure;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.MethodParameter;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.yellowsneakers.core.bean.ClassKits;
import org.yellowsneakers.core.http.StatusInfo;
import org.yellowsneakers.generic.utils.StringUtils;

/**
 * 
 * @author tang
 *
 */
@Aspect
public class AuthAspect implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	/**
     * 表达式处理
     */
    private static final ExpressionParser elParser = new SpelExpressionParser();

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

    @Around("@annotation(preAuth)")
    public Object preAuth(ProceedingJoinPoint point, PreAuth preAuth) throws Throwable {
        boolean succeed = handleAuth(point, preAuth);
        if (succeed) {
            return point.proceed();
        }
        throw new SecureException(StatusInfo.UN_AUTHORIZED);
    }

    @Around("@within(preAuth)")
    public Object preAuthWithin(ProceedingJoinPoint point, PreAuth preAuth) throws Throwable {
        boolean succeed = handleAuth(point, preAuth);
        if (succeed) {
            return point.proceed();
        }
        throw new SecureException(StatusInfo.UN_AUTHORIZED);
    }

    /**
     * 处理权限
     * @param point 切点
     * @param preAuth PreAuth注解
     */
    private boolean handleAuth(ProceedingJoinPoint point, PreAuth preAuth) {
        // 判断表达式
        String condition = preAuth.value();
        if (StringUtils.isBlank(condition)) {
            Expression expression = elParser.parseExpression(condition);
            StandardEvaluationContext context = getEvaluationContext(point);
            return expression.getValue(context, Boolean.class);
        }
        return false;
    }

    /**
     * 获取方法上的参数
     * @param point 切点
     * @return {SimpleEvaluationContext}
     */
    private StandardEvaluationContext getEvaluationContext(ProceedingJoinPoint point) {
        StandardEvaluationContext context = new StandardEvaluationContext(new AuthFunc());
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            MethodParameter methodParam = ClassKits.getMethodParameter(method, i);
            Object value = args[i];
            context.setVariable(methodParam.getParameterName(), value);
        }
        return context;
    }

}

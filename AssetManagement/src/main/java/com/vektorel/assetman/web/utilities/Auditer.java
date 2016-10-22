package com.vektorel.assetman.web.utilities;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.vektorel.assetman.web.entity.BaseEntity;

@Component("aopLogger")
@Aspect
public class Auditer implements ApplicationContextAware {

	
	@Before("execution(* com.vektorel.assetman.web.service.BaseDao.save(..))")
	public void logBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		BaseEntity b = (BaseEntity) args[0];
		b.setEkleyen("TT");
		b.setEklemeTarihi(new Date());
	}

	@After("execution(* com.vektorel.assetman.web.service.BaseDao.update(..))")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("logAfter() Triggered");
		System.out.println(joinPoint.getSignature().toString());
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {

	}



}

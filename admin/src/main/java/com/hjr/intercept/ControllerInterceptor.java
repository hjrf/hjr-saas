package com.hjr.intercept;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 */
@Aspect
@Component
public class ControllerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);
	/**
	 * 拦截器具体实现
	 * @param pjp
	 * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
	 */
	@Around("execution(* com.xh.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)") //指定拦截器规则
	public Object Interceptor(ProceedingJoinPoint pjp){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String beginTime = df.format(new Date());
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod(); //获取被拦截的方法
		String methodName = method.getName(); //获取被拦截的方法名	
		logger.info(beginTime+"请求开始，方法：{}", methodName);		
		Object result = null;
		//继续
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
	}
}
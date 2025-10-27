package com.SecurityService.aop;

import java.util.Arrays;

//import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.springLataleLauncher..*(..))")
    public void applicationPackagePointcut() {}

    @Before("applicationPackagePointcut()")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering: {}", joinPoint.getSignature());
    }

    @After("execution(* com.springLataleLauncher..*(..))")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        System.out.println("Method " + joinPoint.getSignature().getName() + " finished execution.");
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        logger.debug("Exited: {} with result: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {} with message: {}", joinPoint.getSignature(), e.getMessage());
    }

	// For all rest controllers
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerLayer() {}

    // For all services
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceLayer() {}

    // Match all methods inside the project package
    @Pointcut("execution(* com.springLataleLauncher..*(..))")
    public void allApplicationMethods() {}

    // Combined pointcut for Controllers and Services
    @Pointcut("controllerLayer() || serviceLayer()")
    public void monitoredComponents() {}

    @Around("monitoredComponents() && allApplicationMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        long start = System.currentTimeMillis();
        logger.info("[START] {}.{}({})", className, methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;

            // Fine-grained control
            if (className.endsWith("Controller")) {
                logger.info("[SUCCESS] {}.{} returned: {} in {}ms", className, methodName, result, duration);
            } else if (className.endsWith("Service")) {
                logger.debug("[SERVICE OK] {}.{} in {}ms", className, methodName, duration);
            }

            return result;

        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                logger.warn("[WARN] {}.{} illegal argument: {}", className, methodName, Arrays.toString(args));
            } else if (e instanceof RuntimeException) {
                logger.error("[ERROR] {}.{} Runtime: {}", className, methodName, e.getMessage(), e);
            } else {
                logger.error("[FAILURE] {}.{} Unexpected: {}", className, methodName, e.getMessage(), e);
            }
            throw e;
        }
    }
}

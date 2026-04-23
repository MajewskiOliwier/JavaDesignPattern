package allnew.okk.shop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// Week 11 - Aspect Oriented Programming (AOP)
@Aspect
@Component
public class ShopManagementAspect {

    private static final String LOG_FACADE_ENTER = "[AOP LOG] Entering facade method: ";
    private static final String LOG_ERROR_PREFIX = "[AOP ERROR] Exception in ";
    private static final String LOG_ERROR_SUFFIX = ": ";
    private static final String LOG_GUARD_PREFIX = "[AOP GUARD] Validating security context for command: ";

    // Aspect 1: Activity Logging
    @Pointcut("execution(* allnew.okk.shop.facade.*.*(..))")
    private void shopFacadeMethods() {}

    @Before("shopFacadeMethods()")
    public void logFacadeActivity(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logMethodEntry(methodName);
    }

    private void logMethodEntry(String methodName) {
        System.out.println(LOG_FACADE_ENTER + methodName);
    }

    // Aspect 2: Global Exception Logging
    @AfterThrowing(pointcut = "execution(* allnew.okk.shop.facade..*(..)) || execution(* allnew.okk.shop.command..*(..))", throwing = "exception")
    public void handleShopExceptions(JoinPoint joinPoint, Exception exception) {
        String location = joinPoint.getSignature().toShortString();
        logException(location, exception);
    }

    private void logException(String location, Exception exception) {
        System.err.println(LOG_ERROR_PREFIX + location + LOG_ERROR_SUFFIX + exception.getMessage());
    }

    // Aspect 3: Security & State Validation Guard
    @Before("execution(* allnew.okk.shop.command.ShopCommand.execute(..))")
    public void validateCommandExecution(JoinPoint joinPoint) {
        String commandName = joinPoint.getSignature().toShortString();
        logSecurityCheck(commandName);
    }

    private void logSecurityCheck(String commandName) {
        System.out.println(LOG_GUARD_PREFIX + commandName);
    }
}
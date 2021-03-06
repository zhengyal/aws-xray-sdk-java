package com.amazonaws.xray.spring.aop;

import com.amazonaws.xray.entities.Subsegment;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.HashMap;
import java.util.Map;

public class XRayInterceptorUtils {

    public static Object conditionalProceed(ProceedingJoinPoint pjp) throws Throwable {
        if (pjp.getArgs().length == 0) {
            return pjp.proceed();
        } else {
            return pjp.proceed(pjp.getArgs());
        }
    }

    public static Map<String, Map<String, Object>> generateMetadata(ProceedingJoinPoint pjp, Subsegment subsegment) {
        final Map<String, Map<String, Object>> metadata = new HashMap<>();
        final Map<String, Object> classInfo = new HashMap<>();
        classInfo.put("Class", pjp.getTarget().getClass().getSimpleName());
        metadata.put("ClassInfo", classInfo);
        return metadata;
    }


}

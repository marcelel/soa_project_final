package com.agh.edu.server.interceptor;

import com.agh.edu.api.dto.ElementDto;
import com.agh.edu.server.dao.ElementDao;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Map;

@NewElementAudited
@Interceptor
public class NewElementAuditedInterceptor {

    @Inject
    private ElementDao elementDao;

    @AroundInvoke
    public Object newElementAuditMethod(InvocationContext invocationContext) throws Exception {
        Map<String, Object> contextData = invocationContext.getContextData();
        Object[] parameters = invocationContext.getParameters();
        ElementDto elementDto = (ElementDto) parameters[0];
        Integer maxPowerFromCategory = elementDao.getMaxPowerFromCategory(elementDto.getCategoryId());
        if (maxPowerFromCategory != null && maxPowerFromCategory < elementDto.getPower()) {
            elementDto.setPower(maxPowerFromCategory);
        }
        return invocationContext.proceed();
    }
}

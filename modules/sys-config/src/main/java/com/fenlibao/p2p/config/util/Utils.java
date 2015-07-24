package com.fenlibao.p2p.config.util;

import com.fenlibao.p2p.config.annotation.PropMap;
import com.fenlibao.p2p.config.domain.Config;
import org.springframework.util.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/7/3.
 */
public final class Utils {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 根据target属性的注解将
     *
     * @param constants
     * @param target
     */
    public static void setMapValue(List<Config> constants, Object target) {

        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            PropMap propMap = field.getAnnotation(PropMap.class);
            String targetKey = field.getName();
            if (propMap != null && !"".equals(propMap.value())) {
                targetKey = propMap.value();
            }
            for (Config constant : constants) {
                String key = constant.getKey();
                if (key.equals(targetKey)) {
                    Object value = constant.getValue();
                    org.springframework.util.ReflectionUtils.makeAccessible(field);
                    Class clazz = field.getType();
                    if (clazz == Boolean.class) {
                        value = new Boolean(value.toString());
                    }
                    org.springframework.util.ReflectionUtils.setField(field, target, value);
                    break;
                }
            }
        }
        validate(target);
    }

    /**
     * 校验数据
     * @param target
     */
    public static void validate(Object target) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
        boolean error = constraintViolations.size() > 0;
        if (error)
            Assert.isTrue(!error, constraintViolations.iterator().next().getMessage());
    }
}
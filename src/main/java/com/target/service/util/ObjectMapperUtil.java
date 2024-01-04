package com.target.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {

    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, Object> convertObjectToMap(Object obj) {
        return objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        return objectMapper;
    }

    public List<Field> getAllFields(Class clazz) {
        List<Field> result = new ArrayList<>();
        if (clazz != null) {
            result = new ArrayList<>(getAllFields(clazz.getSuperclass()));
            List<Field> filteredFields = Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> !field.getName().equals("id"))
                    .collect(Collectors.toList());
            result.addAll(filteredFields);
        }
        return result;
    }

}

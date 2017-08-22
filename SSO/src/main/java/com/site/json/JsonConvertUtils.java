package com.site.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.commons.lang.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.site.exception.impl.BaseExceptions;
import com.site.exception.impl.SystemException;

public class JsonConvertUtils {
	

    private static Logger logger = LoggerFactory.getLogger(JsonConvertUtils.class);

    private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 把json字符串转成java对象
	 */
	public static<T> T toObject(String json, Class<T> objectClass) {
		if(StringUtils.isEmpty(json)){
			return null;
		}

		try {
            T result = mapper.readValue(json.getBytes("utf-8"), objectClass);
            return result;
        } catch (JsonParseException | UnrecognizedPropertyException ex) {
            logger.error("JSON解析为[" + ClassUtils.getShortClassName(objectClass) + "]出错!", ex);
            throw new SystemException(BaseExceptions.JsonTransError, ex);
		} catch (IOException ex) {
            throw new SystemException(BaseExceptions.JsonTransError, ex);
		}
	}

    @SuppressWarnings("unchecked")
	public static<T> Map<String, Object> toMap(String json) {
        if(StringUtils.isEmpty(json)){
            return null;
        }

        try {
            JsonNode node = mapper.readTree(json);
            return mapper.convertValue(node, Map.class);
        } catch (IOException ex) {
			throw new SystemException(BaseExceptions.JsonTransError, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
	public static<T> Map<String, Object> toMap(Object obj) {
      if (obj == null){
        return null;
      }
      try {
          JsonNode node = mapper.readTree(mapper.writeValueAsString(obj));
          return mapper.convertValue(node, Map.class);
      } catch (IOException ex) {
          throw new SystemException(BaseExceptions.JsonTransError, ex);
      }
  }
	
	public static<T> List<T> toObjectList(String json, Class<T> objectClass) {
		if(StringUtils.isEmpty(json)){
			return null;
		}

		try {
			List<T> result = new ArrayList<T>();
			JsonNode listNode = mapper.readTree(json);
			for(JsonNode objNode : listNode){
				T t = mapper.convertValue(objNode, objectClass);
				result.add(t);
			}

			return result;
        } catch (JsonParseException | UnrecognizedPropertyException ex) {
            logger.error("JSON解析为[" + ClassUtils.getShortClassName(objectClass) + "]出错!", ex);
            throw new SystemException(BaseExceptions.JsonTransError, ex);
        } catch (IOException e) {
			throw new SystemException(BaseExceptions.JsonTransError, e);
		}
	}

    /**
     * 把对象转成json字符串
     */
    public static String toJson(Object obj) {
        if (obj == null){
            return null;
        }

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
        	throw new SystemException(BaseExceptions.JsonTransError, e);
        }
    }
}

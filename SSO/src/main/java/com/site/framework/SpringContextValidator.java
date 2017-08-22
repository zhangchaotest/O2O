package com.site.framework;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.site.common.BeanUtils;


public class SpringContextValidator {
	final static Logger logger = LoggerFactory.getLogger(SpringContextValidator.class);

	public static void detectNullFields(ApplicationContext context) throws IllegalArgumentException,
			IllegalAccessException {
		String[] names = context.getBeanDefinitionNames();

		logger.warn("检查开始");
		for (String name : names) {
			Object bean = context.getBean(name);
			List<String> fields = BeanUtils.detectNullFields(bean);
			for (String fname : fields) {
				logger.warn(name + "." + fname + "=null");
			}
		}
		logger.warn("检查结束");
	}
}

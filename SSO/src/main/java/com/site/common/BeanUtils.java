package com.site.common;

import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;




public class BeanUtils {
	/**
	 * 验证传入对象是有含有null的属性
	 *
	 * @param bean
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> detectNullFields(Object bean) throws IllegalArgumentException, IllegalAccessException {
		List fields = getFieldsUpTo(bean.getClass(), Object.class);
		ArrayList nullFields = new ArrayList();
		Iterator var4 = fields.iterator();

		while(var4.hasNext()) {
			Field f = (Field)var4.next();
			f.setAccessible(true);
			Object value = f.get(bean);
			if(value == null) {
				nullFields.add(f.getName());
			}
		}

		return nullFields;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Field> getFieldsUpTo(Class<?> startClass, Class<?> exclusiveParent) {
		List currentClassFields = toList(startClass.getDeclaredFields());
		Class parentClass = startClass.getSuperclass();
		if(parentClass != null && (exclusiveParent == null || !parentClass.equals(exclusiveParent))) {
			List parentClassFields = getFieldsUpTo(parentClass, exclusiveParent);
			currentClassFields.addAll(0, parentClassFields);
		}

		return currentClassFields;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> toList(T[] array) {
		if(array == null) {
			return Collections.emptyList();
		} else {
			ArrayList list = new ArrayList();

			for(int i = 0; i < array.length; ++i) {
				list.add(array[i]);
			}
			return list;
		}
	}

	/**
	 * 如果字符串是null，则返回""
	 * @param s
	 * @return
	 */
	public static String killNull(String s) {
		return s == null?"":s;
	}
	private static SqlSessionFactory sqlSessionFactory=null;
	private static Reader reader;
	private static BeanUtils instance=new BeanUtils();
	
	public static BeanUtils getInstace(){
		if(sqlSessionFactory == null){
			init();
		}
		return instance;
	}
	private static void init(){
		try{
			reader = Resources.getResourceAsReader("application-context.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public SqlSessionFactory getSqlSessionFactory(){
		if(sqlSessionFactory == null){
			init();
		}
		return  sqlSessionFactory;
	}
}

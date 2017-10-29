package com.kxcar.wxcar.util.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Json序列化工具
 * 
 * @author http://blog.csdn.net/xxd851116
 * @date 2014年3月26日 下午1:21:59
 */
@SuppressWarnings("unchecked")
public class JsonHelper {
	private static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 将对象序列化为JSON字符串
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	public static String serialize(Object object) throws Exception {
//		Writer write = new StringWriter();
//		objectMapper.writeValue(write, object);
		return objectMapper.writeValueAsString(object);
	}

	/**
	 * 将JSON字符串反序列化为对象
	 * 
	 * @param object
	 * @return JSON字符串
	 * @throws Exception
	 */
	public static <T> T deserialize(String json, Class<T> typeRef) throws Exception {
		return objectMapper.readValue(json, typeRef);
	}
}

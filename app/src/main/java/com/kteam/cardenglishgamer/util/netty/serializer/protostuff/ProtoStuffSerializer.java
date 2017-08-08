package com.kteam.cardenglishgamer.util.netty.serializer.protostuff;

import com.kteam.cardenglishgamer.util.netty.serializer.Serializer;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;


/**
 * 具体实现
 * @author BazingaLyn
 */

public class ProtoStuffSerializer implements Serializer {

	private static Map<String, Schema> cachedSchema = new ConcurrentHashMap();

	@SuppressWarnings("unchecked")
	public <T> byte[] writeObject(T obj) {
		LinkedBuffer buffer = LinkedBuffer.allocate();
		try {
			Schema schema = RuntimeSchema.getSchema(obj.getClass());
			byte[] bytes = ProtobufIOUtil.toByteArray(obj, schema, buffer);
			System.out.println("byte:"+ Arrays.toString(bytes));
			return bytes;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	public <T> T readObject(byte[] bytes, Class<T> clazz) {
		T obj;
		try {
			obj = clazz.newInstance();
			Schema schema = RuntimeSchema.getSchema(obj.getClass());
			ProtobufIOUtil.mergeFrom(bytes, obj, schema);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		return obj;
	}
}

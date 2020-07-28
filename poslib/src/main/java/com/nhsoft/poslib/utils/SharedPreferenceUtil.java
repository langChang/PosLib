package com.nhsoft.poslib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.nhsoft.poslib.RetailPosManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author zou.sq
 * SharedPreference操作类
 */
public class SharedPreferenceUtil {

    private static final int    INVALID_CODE = -1;

    /**
     * 根据KEY 查询字符串值
     *
     * @param key 键名字
     * @return String 返回的字符串值
     */
    public static String getStringValueByKey(String fileName, String key) {
        String value = "";
        if (!StringUtil.isNullOrEmpty(key)) {
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            value = sharedPreferences.getString(key, "");
        }
        return value;
    }

    /**
     * 根据KEY 查询Boolean值
     *
     * @param key 键名
     * @return boolean 返回的boolean值
     */
    public static boolean getBooleanValueByKey(String fileName, String key) {
        boolean value = false;
        if (!StringUtil.isNullOrEmpty(key)) {
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            value = sharedPreferences.getBoolean(key,false);
        }
        return value;
    }

    /**
     * 根据KEY 查询Integer值
     *
     * @param key 键名
     * @return int 查询Integer值
     */
    public static int getIntegerValueByKey(String fileName, String key) {
        int value = INVALID_CODE;
        if (!StringUtil.isNullOrEmpty(key)) {
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            value = sharedPreferences.getInt(key, INVALID_CODE);
        }
        return value;
    }

    /**
     * 根据KEY 查询Long值
     *
     * @param key 键名
     * @return int 查询Long值
     */
    public static long getLongValueByKey(String fileName, String key) {
        long value = INVALID_CODE;
        if (!StringUtil.isNullOrEmpty(key)) {
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            value = sharedPreferences.getLong(key, INVALID_CODE);
        }
        return value;
    }

    /**
     * 根据KEY 查询float值
     *
     * @param key 键名
     * @return float 查询float值
     */
    public static float getFloatValueByKey(String fileName, String key) {
        float value = INVALID_CODE;
        if (!StringUtil.isNullOrEmpty(key)) {
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            value = sharedPreferences.getFloat(key, INVALID_CODE);
        }
        return value;
    }

    /**
     * 避免多个地方同时修改所以加synchronized; 保存本地数据，在读取的时候就不需要加了；
     *
     * @param key   键名
     * @param value 需要存储的值
     */
    public static synchronized void saveValue(String fileName, String key, Object value) {
        if (!StringUtil.isNullOrEmpty(key)) {
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            Editor editor = sharedPreferences.edit();
            if (value != null) {
                if (value instanceof String) {
                    editor.putString(key, String.valueOf(value));
                } else if (value instanceof Integer) {
                    editor.putInt(key, (Integer) value);
                } else if (value instanceof Boolean) {
                    editor.putBoolean(key, (Boolean) value);
                } else if (value instanceof Float) {
                    editor.putFloat(key, (Float) value);
                } else if (value instanceof Long) {
                    editor.putLong(key, (Long) value);
                }
                editor.apply();
            }
        }
    }

    /**
     * 避免多个地方同时修改所以加synchronized;
     *
     * @param fileName 存储的文件名
     * @param key      待移除数据的key
     */
    public static synchronized void removeValue(String fileName, String key) {
        if (!StringUtil.isNullOrEmpty(key)) {
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            Editor editor = sharedPreferences.edit();
            editor.remove(key);
            editor.apply();
        }
    }

    /**
     * 如果同一个对象可以在SharePreference中保存多个实例，如WeiboInfoModel; 可以有Sina，QQ，RenRen
     * 直接将对象保存，字段类型只能为String,Integer,Long,Boolean,Float,Double类型，如果有其它类型，
     * 那么需要另做对象保存；
     *
     * @param fileName 文件名
     * @param obj      保存的对象
     */
    public static synchronized void saveObject(String fileName, Object obj) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getName() == null) {
                    continue;
                }
                if (field.get(obj) == null) {
                    editor.remove(field.getName());
                    continue;
                }
                // 如果类型是String// 如果类型是Integer// 如果类型是Boolean 是封装类
                if ("class java.lang.String".equals(field.getType().toString())) {
                    editor.putString(field.getName(), field.get(obj).toString());
                } else if ("class java.lang.Integer".equals(field.getType().toString())
                    || "int".equals(field.getType().toString())) {
                    editor.putInt(field.getName(), Integer.parseInt(field.get(obj).toString()));
                } else if ("class java.lang.Boolean".equals(field.getType().toString())
                    || "boolean".equals(field.getType().toString())) {
                    editor.putBoolean(field.getName(), Boolean.parseBoolean(field.get(obj).toString()));
                } else if ("class java.lang.Long".equals(field.getType().toString())
                    || "long".equals(field.getType().toString())) {
                    editor.putLong(field.getName(), Long.parseLong(field.get(obj).toString()));
                } else if ("class java.lang.Double".equals(field.getType().toString())
                    || "double".equals(field.getType().toString())
                    || "class java.lang.Float".equals(field.getType().toString())
                    || "float".equals(field.getType().toString())) {
                    editor.putFloat(field.getName(), Float.parseFloat(field.get(obj).toString()));
                } else if ("class java.util.ArrayList".equals(field.getType().toString())
                    || "interface java.util.List".equals(field.getType().toString())) {
                    editor.putString(field.getName(), new Gson().toJson(field.get(obj)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        editor.apply();
    }

    /**
     * @param fileName 文件名
     * @param clazz    类型
     * @return Object 获取到的对象
     */
    public static Object getObject(String fileName, Class<?> clazz) {
        Object object = null;
        try {
            object = clazz.newInstance();
            SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Class<?> typeClass = field.getType();
                Constructor<?> con;
                Object valueObj = null;
                // 如果类型是String// 如果类型是Integer// 如果类型是Boolean 是封装类
                if ("class java.lang.String".equals(typeClass.toString())) {
                    con = typeClass.getConstructor(String.class);
                    String value = sharedPreferences.getString(field.getName(), "");
                    valueObj = con.newInstance(value);
                } else if ("class java.lang.Integer".equals(typeClass.toString())) {
                    con = typeClass.getConstructor(String.class);
                    Integer value = sharedPreferences.getInt(field.getName(), -1);
                    valueObj = con.newInstance(value + "");
                } else if ("int".equals(typeClass.toString())) {
                    valueObj = sharedPreferences.getInt(field.getName(), -1);
                } else if ("class java.lang.Boolean".equals(typeClass.toString())) {
                    con = typeClass.getConstructor(String.class);
                    Boolean value = sharedPreferences.getBoolean(field.getName(), false);
                    valueObj = con.newInstance(value + "");
                } else if ("boolean".equals(typeClass.toString())) {
                    valueObj = sharedPreferences.getBoolean(field.getName(), false);
                } else if ("class java.lang.Long".equals(typeClass.toString())) {
                    con = typeClass.getConstructor(String.class);
                    Long value = sharedPreferences.getLong(field.getName(), -1);
                    valueObj = con.newInstance(value + "");
                } else if ("long".equals(typeClass.toString())) {
                    valueObj = sharedPreferences.getLong(field.getName(), -1);
                } else if ("class java.lang.Double".equals(typeClass.toString())
                    || "class java.lang.Float".equals(typeClass.toString())) {
                    con = typeClass.getConstructor(String.class);
                    Float value = sharedPreferences.getFloat(field.getName(), -1f);
                    valueObj = con.newInstance(value + "");
                } else if ("double".equals(typeClass.toString()) || "float".equals(typeClass.toString())) {
                    valueObj = sharedPreferences.getFloat(field.getName(), -1f);
                } else if ("clazz java.util.ArrayList".equals(typeClass.toString())
                    || "interface java.util.List".equals(typeClass.toString())) {
                    String value = sharedPreferences.getString(field.getName(), "");

                    Type genericType = field.getGenericType();
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) genericType;
                        valueObj = new Gson().fromJson(value, parameterizedType);
                    }
                }
                if (valueObj != null) {
                    if (!Modifier.toString(field.getModifiers()).endsWith("final")) {
                        field.set(object, valueObj);
                    }
                }
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 清除当前Model的本地存储；
     *
     * @param fileName 文件名字
     */
    public static void clearObject(String fileName) {
        SharedPreferences sharedPreferences = RetailPosManager.sContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

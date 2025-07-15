package com.json.extract.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:franksoongyn@gmail.com">yexian</a>
 * @version 1.0
 * @date 2024/07/22
 * @Description 列表工具类
 */
public class ListUtil {

    private static Log logger = LogFactory.getLog(ListUtil.class);

    /**
     * 列表转换成逗号分割的字符串  只针对基础数据类型
     *
     * @param list  基础数据类型list
     * @return
     */
    public static String join(List list) {
        return join(list, ",");
    }

    /**
     * 以某个字符间隔拼装起来  只针对基础数据类型
     * @param list  任意list对象
     * @param c     间隔字符
     * @return
     */
    public static String join(List list, String c) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object each : list) {
            if (first) {
                first = false;
            } else {
                sb.append(c);
            }
            sb.append(each);
        }
        return sb.toString();
    }

    /**
     * 比如：List<eventDO> 转成 Map<eventId, eventDO>
     *
     * @param list           bean对象list
     * @param keyNameInList  map的key取值的字段
     * @param <T>            泛型
     * @return
     */
    public static <K, T> Map<K, T> listToMap(List<T> list, String keyNameInList) {
        Map<K, T> map = new HashMap<>(2);
        if (isEmpty(list)) {
            return map;
        }

        Class<?> clazz = list.get(0).getClass();
        Method method = getBeanMethod(clazz, keyNameInList, "get");
        if (method == null) {
            return map;
        }

        map = list.stream().collect(Collectors.toMap(t->findKeyValue(t,method),t->t));

        /*map = list.stream().collect(HashMap::new, (m, v) -> {
            try {
                @SuppressWarnings("unchecked")
                K k = (K)method.invoke(v);
                m.put(k, v);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                logger.error("list to map failed , cause : "+e.getMessage());
            }
        }, HashMap::putAll);*/
        return map;
    }

    private static  <K, T> K findKeyValue(T object,Method method){
        try {
            K k = (K)method.invoke(object);
            return k;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            logger.error("list to map failed , cause : "+e.getMessage());
        }
        return null;
    }

    /**
     * 从list<EventDO> 提取eventDO数据变成 list<Long> eventIdList 或者其他的字段信息，只能取1列
     * @param list             bean对象list
     * @param keyNameInList    map的key的取值字段
     * @param <T>              泛型
     * @param <F>              泛型
     * @return
     */
    public static <T, F> List<F> fetchFieldsAsList(List<T> list, String keyNameInList) {
        List<F> fieldList = new ArrayList<>();
        if (isEmpty(list)) {
            return fieldList;
        }

        Class<?> clazz = list.get(0).getClass();
        Method method = getBeanMethod(clazz, keyNameInList, "get");
        if (method == null) {
            return fieldList;
        }

        fieldList = list.stream().map(t -> {
            try {
                @SuppressWarnings("unchecked")
                F f = (F)method.invoke(t);
                return f;
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }).distinct().collect(Collectors.toList());
        return fieldList;
    }

    /**
     * 从list<EventDO> 提取eventDO数据变成 list<Long> eventIdList 或者其他的字段信息，只能取1列 去重
     * 注意！该方法返回的list与原list顺序不一致，而是按字母序重排，如需保持顺序，请使用
     * @param list             bean对象list
     * @param keyNameInList    map的key的取值字段
     * @param <T>              泛型
     * @param <F>              泛型
     * @return
     */
    @Deprecated
    public static <T, F> List<F> fetchDistinctFieldsAsList(List<T> list, String keyNameInList) {
        List<F> fieldList = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return fieldList;
        }

        Set<F> fieldSet = new TreeSet<>();
        fetchDistinctFieldsToList(list, keyNameInList, fieldSet);
        fieldList.addAll(fieldSet);
        return fieldList;
    }

    /**
     * 从list<EventDO> 提取eventDO数据变成 list<Long> eventIdList 或者其他的字段信息，只能取1列 去重
     * 注意！该方法返回的list与原list顺序不一致，而是按字母序重排，如需保持顺序，请使用
     * @param list             bean对象list
     * @param keyNameInList    map的key的取值字段
     * @param <T>              泛型
     * @param <F>              泛型
     * @param useAlphabetOrder 是否以字母序重排
     * @return
     */
    public static <T, F> List<F> fetchDistinctFields(List<T> list, String keyNameInList, Boolean useAlphabetOrder) {
        List<F> fieldList = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return fieldList;
        }

        Set<F> fieldSet;
        if (useAlphabetOrder != null && useAlphabetOrder) {
            // type T may be non-comparable
            fieldSet = new TreeSet<>();
        } else {
            fieldSet = new LinkedHashSet<>();
        }

        fetchDistinctFieldsToList(list, keyNameInList, fieldSet);
        fieldList.addAll(fieldSet);
        return fieldList;
    }

    /**
     * 从list<EventDO> 提取eventDO数据变成 List<Long> eventIdList 或者其他的字段信息的集合，只能取1列 不去重
     * @param list            bean对象list
     * @param keyNameInList   map的key的取值字段
     * @param collection      集合数据
     * @param <T>             泛型
     * @param <F>             泛型
     */
    public static <T, F> void fetchDistinctFieldsToList(List<T> list, String keyNameInList, Collection<F> collection) {

        if (list == null || list.size() == 0) {
            return;
        }

        Class<?> clazz = list.get(0).getClass();
        Method method = getBeanMethod(clazz, keyNameInList, "get");
        if (method == null) {
            return;
        }

        for (T obj : list) {
            try {
                F key = (F)method.invoke(obj);
                if (key == null) {
                    continue;
                }
                collection.add(key);
            } catch (IllegalAccessException | InvocationTargetException ignore) {
                ignore.printStackTrace();
            }
        }
    }

    private static Method getBeanMethod(Class<?> clazz, String propertyName, String prefix) {
        char[] cs = propertyName.toCharArray();
        cs[0] -= 32;
        String methodName = prefix + String.valueOf(cs);
        try {
            return clazz.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /**
     * 检测是否为空列表
     *
     * @param list 参数值
     * @param <T>  泛型
     * @return true:空数组
     */
    public static <T> boolean isEmpty(List<T> list) {
        return !(list != null && list.size() > 0);
    }

    /**
     * 检测是否为空列表
     *
     * @param list 参数值
     * @param <T>  泛型
     * @return true:空数组
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && list.size() > 0;
    }
}

package com.json.extract.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gl
 * @date 2024/1/12 11:12
 */
public final class ExcelListener<T> extends AnalysisEventListener<T>{

    /**
     * 自定义用于暂时存储data
     * 可以通过实例获取该值
     */
    private List<T> datas = new ArrayList<>();

    /**
     * 每解析一行都会回调invoke()方法
     *
     * @param object  读取后的数据对象
     * @param context 内容
     */
    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Object object, AnalysisContext context) {
        T map = (T) object;
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(map);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源
        //注意不要调用datas.clear(),否则getDatas为null
    }

    /**
     * 返回数据
     *
     * @return 返回读取的数据集合
     **/
    public List<T> getData() {
        return datas;
    }

    /**
     * 设置读取的数据集合
     *
     * @param data 设置读取的数据集合
     **/
    public void setData(List<T> data) {
        this.datas = data;
    }

}


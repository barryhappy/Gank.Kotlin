package com.barryzhang.gankkotlin.entities;

import java.util.List;
import java.util.Map;

/**
 * 这个类用Java而不是Kotlin的原因:
 * 在Kotlin下,泛型`<Map<String,List<GankItem>>>`中的GankItem在用Gson解析的时候,
 * 不能正确的被转为GankItem,(而是被解析成了Map类型)。
 * ——引起这种现象的原因暂时未知。
 * 使用Java代替Kotlin则不会有这种问题,这一点也反映了使用Kotlin进行Android开发的优点:
 * ——当遇到Kotlin做不到的事情的时候,依然可以很方便地切换回Java来做。
 *
 */
public class DailyGankEntity extends BaseHttpResponseEntity<Map<String,List<GankItem>>> {
    private List<String> category ;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}

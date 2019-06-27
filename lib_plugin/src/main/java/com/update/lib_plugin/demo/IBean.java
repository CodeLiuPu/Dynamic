package com.update.lib_plugin.demo;

/**
 * @author : liupu
 * date   : 2019/6/26
 * desc   :
 */
public interface IBean {
    String getName();

    void setName(String name);

    void register(ICallback callback);
}

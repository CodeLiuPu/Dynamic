package com.update.plugin;

import com.update.lib_plugin.IBean;
import com.update.lib_plugin.ICallback;

/**
 * @author : liupu
 * date   : 2019/6/26
 * desc   :
 */
public class Bean implements IBean {
    private String name = "Update";

    private ICallback callback;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void register(ICallback callback) {
        this.callback = callback;
        clickButton();
    }

    public void clickButton(){
        callback.sendResult("Hello : " + this.name);
    }
}

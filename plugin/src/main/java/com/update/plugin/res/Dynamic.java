package com.update.plugin.res;

import android.content.Context;

import com.update.lib_plugin.res.IDynamic;
import com.update.plugin.R;

/**
 * @author : liupu
 * date   : 2019/6/27
 * desc   :
 */
public class Dynamic implements IDynamic {
    @Override
    public String getStringForResId(Context context) {
        return context.getResources().getString(R.string.plugin_hello);
    }
}

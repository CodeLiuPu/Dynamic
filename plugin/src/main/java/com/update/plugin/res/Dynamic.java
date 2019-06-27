package com.update.plugin.res;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.update.lib_plugin.res.IDynamic;
import com.update.plugin.R;

/**
 * @author : liupu
 * date   : 2019/6/27
 * desc   :
 */
public class Dynamic implements IDynamic {

    @Override
    public String getString(Context context) {
        return context.getResources().getString(R.string.plugin_hello);
    }

    @Override
    public Drawable getImageDrawable(Context context) {
        return context.getResources().getDrawable(R.mipmap.ic_laun);
    }
}

package com.update.dynamic;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.update.lib_plugin.res.IDynamic;

public class ResActivity extends BaseActivity {

    TextView tv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);

        // 带资源文件的调用
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResources(pluginName1);
                changeView(pluginName1);
            }
        });

    }

    public void changeView(String pluginName){
        Class mLoadClassDynamic = null;
        try {
            mLoadClassDynamic = plugins.get(pluginName).getClassLoader().loadClass("com.update.plugin.res.Dynamic");
            Object dynamicObject = mLoadClassDynamic.newInstance();
            IDynamic dynamic = (IDynamic) dynamicObject;
            String content = dynamic.getString(activity);
            tv.setText(content);

            Drawable drawable = dynamic.getImageDrawable(activity);
            iv.setBackground(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

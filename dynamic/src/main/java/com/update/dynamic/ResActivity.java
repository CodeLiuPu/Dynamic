package com.update.dynamic;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.update.lib_plugin.res.IDynamic;

public class ResActivity extends BaseActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        tv = findViewById(R.id.tv);

        // 带资源文件的调用
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResources(plugins.get(pluginName1).getDexPath());
                Class mLoadClassDynamic = null;
                try {
                    mLoadClassDynamic = plugins.get(pluginName1).getClassLoader().loadClass("com.update.plugin.res.Dynamic");
                    Object dynamicObject = mLoadClassDynamic.newInstance();
                    IDynamic dynamic = (IDynamic) dynamicObject;
                    String content = dynamic.getStringForResId(activity);
                    tv.setText(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}

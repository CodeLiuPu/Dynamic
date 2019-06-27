package com.update.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.update.dynamic.helper.GlobalCache;
import com.update.lib_plugin.demo.IBean;
import com.update.lib_plugin.demo.ICallback;

import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class DemoActivity extends AppCompatActivity {
    private static final String TAG = "Hook DemoActivity";
    private DexClassLoader classLoader = null;
    Activity activity;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        activity = this;
        tv = findViewById(R.id.tv);

        classLoader = new DexClassLoader(GlobalCache.dexpath,
                GlobalCache.fileRelease.getAbsolutePath(), null, getClassLoader());

        //普通调用,反射的方式
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class mLoadClassBean;
                try {
                    mLoadClassBean = classLoader.loadClass("com.update.plugin.demo.Bean");
                    Object beanObject = mLoadClassBean.newInstance();

                    Method getNameMethod = mLoadClassBean.getMethod("getName");
                    getNameMethod.setAccessible(true);
                    String name = (String) getNameMethod.invoke(beanObject);

                    tv.setText(name);
                    Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.e(TAG, "msg:" + e.getMessage());
                }
            }
        });

        // 带参数调用
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class mLoadClassBean;
                try {
                    mLoadClassBean = classLoader.loadClass("com.update.plugin.demo.Bean");
                    Object beanObject = mLoadClassBean.newInstance();

                    IBean bean = (IBean) beanObject;
                    bean.setName("Hello");
                    tv.setText(bean.getName());
                    Toast.makeText(getApplicationContext(), bean.getName(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.e(TAG, "msg:" + e.getMessage());
                }
            }
        });

        // 带回调函数的调用
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class mLoadClassBean;
                try {
                    mLoadClassBean = classLoader.loadClass("com.update.plugin.demo.Bean");
                    Object beanObject = mLoadClassBean.newInstance();
                    IBean bean = (IBean) beanObject;
                    ICallback callback = new ICallback() {
                        @Override
                        public void sendResult(String result) {
                            tv.setText(result);
                        }
                    };
                    bean.register(callback);

                } catch (Exception e) {
                    Log.e(TAG, "msg:" + e.getMessage());
                }
            }
        });

    }
}

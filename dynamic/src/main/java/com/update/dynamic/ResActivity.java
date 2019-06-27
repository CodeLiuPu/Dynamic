package com.update.dynamic;

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

public class ResActivity extends AppCompatActivity {

    private DexClassLoader classLoader = null;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        tv = findViewById(R.id.tv);

        classLoader = new DexClassLoader(GlobalCache.dexpath,
                GlobalCache.fileRelease.getAbsolutePath(), null, getClassLoader());

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

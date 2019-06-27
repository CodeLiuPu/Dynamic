package com.update.dynamic;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.update.dynamic.helper.GlobalCache;
import com.update.lib_plugin.demo.IBean;
import com.update.lib_plugin.demo.ICallback;
import com.update.lib_plugin.res.IDynamic;

import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class ResActivity extends AppCompatActivity {
    private static final String TAG = "Hook ResActivity";
    Activity activity;

    private DexClassLoader classLoader = null;
    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        activity = this;
        tv = findViewById(R.id.tv);

        classLoader = new DexClassLoader(GlobalCache.dexpath,
                GlobalCache.fileRelease.getAbsolutePath(), null, getClassLoader());

        // 带资源文件的调用
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadResources();
                Class mLoadClassDynamic = null;
                try {
                    mLoadClassDynamic = classLoader.loadClass("com.update.plugin.res.Dynamic");
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

    private void loadResources(){
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = am.getClass().getMethod("addAssetPath", String.class);
            method.invoke(am,GlobalCache.dexpath);
            mAssetManager = am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        mResources = new Resources(mAssetManager,super.getResources().getDisplayMetrics(),super.getResources().getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(super.getTheme());
    }

    @Override
    public Resources getResources() {
        boolean isNull = null == mResources;
        Log.e(TAG,"mResources is null? " + isNull);
        if (isNull) {
            return super.getResources();
        }
        return mResources;
    }

    @Override
    public Resources.Theme getTheme() {
        boolean isNull = null == mTheme;
        Log.e(TAG,"mTheme is null? " + isNull);
        if (isNull) {
            return super.getTheme();
        }
        return mTheme;
    }

    @Override
    public AssetManager getAssets() {
        boolean isNull = null == mAssetManager;
        Log.e(TAG,"mAssetManager is null? " + isNull);
        if (isNull) {
            return super.getAssets();
        }
        return mAssetManager;
    }
}

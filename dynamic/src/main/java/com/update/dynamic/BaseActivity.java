package com.update.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.update.dynamic.helper.Utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import dalvik.system.DexClassLoader;

/**
 * @author : liupu
 * date   : 2019/6/27
 * desc   :
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected static final String TAG = "Hook Activity";

    public static final String pluginName1 = "plugin-debug.apk"; //apk名称

    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;

    protected Map<String, PluginInfo> plugins = new HashMap<>();

    Activity activity;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Utils.extractAssets(newBase, pluginName1);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        generatePluginInfo(pluginName1);
    }

    protected void generatePluginInfo(String pluginName) {
        File extractFile = this.getFileStreamPath(pluginName);
        //plugin文件地址
        String dexPath = extractFile.getPath();
        //释放目录
        File  fileRelease = getDir("dex", Context.MODE_PRIVATE);
        DexClassLoader  classLoader = new DexClassLoader(dexPath,
                fileRelease.getAbsolutePath(), null, getClassLoader());
        plugins.put(pluginName,new PluginInfo(dexPath,classLoader));
    }

    protected void loadResources(String dexPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = am.getClass().getMethod("addAssetPath", String.class);
            method.invoke(am, dexPath);
            mAssetManager = am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        mResources = new Resources(mAssetManager, super.getResources().getDisplayMetrics(), super.getResources().getConfiguration());
        mTheme = mResources.newTheme();
        mTheme.setTo(super.getTheme());
    }

    @Override
    public Resources getResources() {
        return mResources == null ? super.getResources() : mResources;
    }

    @Override
    public Resources.Theme getTheme() {
        return mTheme == null ? super.getTheme() : mTheme;
    }

    @Override
    public AssetManager getAssets() {
        return mAssetManager == null ? super.getAssets() : mAssetManager;
    }
}

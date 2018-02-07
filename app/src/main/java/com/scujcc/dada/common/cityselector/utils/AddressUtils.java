package com.scujcc.dada.common.cityselector.utils;

import android.content.Context;
import android.content.res.AssetManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scujcc.dada.common.cityselector.model.ProvinceModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by  范朝波 on 2018/1/26.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class AddressUtils {

    public List<ProvinceModel> getProvinceList(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName),"utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(stringBuilder.toString(), new TypeToken<List<ProvinceModel>>(){}.getType());
    }
}

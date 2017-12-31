package com.scujcc.dada;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by  范朝波 on 2017/12/30.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */
public class Test extends DataSupport {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
    //获取当前时间
    Date date = new Date(System.currentTimeMillis());

    String mString = simpleDateFormat.format(date);



}

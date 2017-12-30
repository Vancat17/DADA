package com.scujcc.dada;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by  范朝波 on 2017/12/30.
 * 微信    ：family997722
 * QQ号    ：1136836811
 */

public class Test extends Activity {
    private void nnnn() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data.getExtras();
        String param = bundle.getString("param");
    }
}

package br.com.migotto.light.util;

import android.content.Context;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by appCivico on 25/10/2017.
 */

public class Message {
    public static void toast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void snack(View view, CharSequence charSequence){
        Snackbar.make(view, charSequence, BaseTransientBottomBar.LENGTH_SHORT).show();
    }
}

package hm.uheath.com.jsbridge.bean.test.call;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.google.gson.Gson;

import hm.uheath.com.jsbridge.JsAction;
import hm.uheath.com.jsbridge.view.X5WebView;

/**
 * TODO:
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class JsCall extends JsAction {
    public static final String ACTION = "call";

    @Override
    public void handleAction(Activity context, String jsonStr, X5WebView webView, String callback) {
        JsMsgCallEntity msgCallEntity = new Gson().fromJson(jsonStr, JsMsgCallEntity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.requestPermissions(new String[]{"android.permission.CALL_PHONE"}, 10001);
        }
        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + msgCallEntity.getData().getNumber()));
        context.startActivity(phoneIntent);
    }
}
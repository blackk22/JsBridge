package hm.uheath.com.jsbridge;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import com.google.gson.Gson;

import hm.uheath.com.jsbridge.view.X5WebView;

/**
 * TODO:
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public abstract class JsAction {
    public abstract void handleAction(Activity context,
                                      String jsonStr,
                                      X5WebView webView,
                                       String callback
    );

    public void callback(Activity context,final X5WebView webView, final String callback, final Object result){
        //TODO 切换到主线程，调用js方法
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (null==result||null==callback||"".equals(callback))return;
                String resultStr= new Gson().toJson(result);
                Log.i("HHHHH","callBack = "+callback);
                String url = "javascript:"+callback+"("+resultStr+")";
                webView.loadUrl(url);
            }
        });



    }
}

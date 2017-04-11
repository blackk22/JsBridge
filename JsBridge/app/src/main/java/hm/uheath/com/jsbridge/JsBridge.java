package hm.uheath.com.jsbridge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import hm.uheath.com.jsbridge.utils.HandleJsMessage;
import hm.uheath.com.jsbridge.view.X5WebView;

/**
 * TODO:
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class JsBridge {
    HandleJsMessage mHandleJsMessage;

    private JsBridge() {
    }

    public static JsBridge getInstance() {
        return JsBridgeHolder.getJsBridgeInstance;

    }

    private static final class JsBridgeHolder {
        private final static JsBridge getJsBridgeInstance = new JsBridge();
    }

    public JsBridge init(Activity activity, X5WebView webView){
        mHandleJsMessage = new HandleJsMessage(activity, webView);
        settingWebView(activity,webView);
        return this;
    }

    /**
     * 添加交互类型
     * @param action
     * @param jsActionImp
     * @return
     */

    public JsBridge addJsAction(@Nullable String action, @Nullable Class<? extends JsAction> jsActionImp) {
        if (mHandleJsMessage != null)
            mHandleJsMessage.getActionMap().put(action, jsActionImp);
        return this;
    }
    public void callBackJs(Activity activity,X5WebView webView,Object result){
        if(mHandleJsMessage != null){
            JsAction jsAction = mHandleJsMessage.getmJsAction();
            if(null == jsAction){
                return;
            }
            jsAction.callback(activity,webView,mHandleJsMessage.jsCallback,result);

        }
    }

    /**
     * 暴露给js一个统一的接口
     *
     * @param webView
     */
    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void addJavascriptInterface(X5WebView webView) {
        webView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void sendMessage(String jsonStr) {
                mHandleJsMessage.handle(jsonStr);
            }
        }, "native");
    }


    public void destroy() {
        mHandleJsMessage.destroy();
    }


    private void settingWebView(Activity activity, X5WebView webView) {
        webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        addJavascriptInterface(webView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

}

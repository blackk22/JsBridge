package hm.uheath.com.jsbridge.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import hm.uheath.com.jsbridge.JsAction;
import hm.uheath.com.jsbridge.JsMessage;
import hm.uheath.com.jsbridge.view.X5WebView;

/**
 * TODO:处理消息类
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class HandleJsMessage {
    private Activity mContext;
    private X5WebView mWebView;
    private Map<String, Class<? extends JsAction>> mActionMap;
    public JsAction mJsAction;
    public String jsCallback;
    public HandleJsMessage() {
        mActionMap = new HashMap<>();

    }

    public JsAction getmJsAction() {
        return mJsAction;
    }

    public HandleJsMessage(Activity context, X5WebView webView) {
        this();
        mContext = context;
        mWebView = webView;

    }



    /**
     *  处理js传递过来的数据
     * @param jsonStr js传递的数据
     * @return 是否处理
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public boolean handle(String jsonStr) {
        JsMessage jsMessage = new Gson().fromJson(jsonStr, JsMessage.class);
        String action = jsMessage.getAction();
        jsCallback = jsMessage.getCallback();
        if (null == jsMessage.getAction())
            return false;
        if (HandleAction(jsonStr, action, mActionMap)) return true;
        return false;
    }

    /**
     *  根据js传递过来的action将事件分发下去
     * @param jsonStr js传递的数据
     * @param action js意图
     * @param map js意图集合
     * @return 是否处理存在处理次意图的接口
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean HandleAction(String jsonStr, String action, Map<String, Class<? extends JsAction>> map) {
        for (String mapAction : map.keySet()) {
            if (mapAction.equals(action)) {
                try {
                    mJsAction = map.get(mapAction).newInstance();
                    if (mJsAction != null) {
                        mJsAction.handleAction(mContext, jsonStr,mWebView,jsCallback);
                    }
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    public Map<String, Class<? extends JsAction>> getActionMap() {
        return mActionMap;
    }

    public void destroy() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        if (mContext != null) mContext = null;
    }
}

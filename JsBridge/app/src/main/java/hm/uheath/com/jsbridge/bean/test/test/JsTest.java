package hm.uheath.com.jsbridge.bean.test.test;

import android.app.Activity;
import android.widget.Toast;

import hm.uheath.com.jsbridge.HandleResult;
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
public class JsTest extends JsAction {
    public static final  String ACTION = "actionTest";

    @Override
    public void handleAction(Activity context, String jsonStr, X5WebView webView, String callback) {
        HandleResult msg = new HandleResult();
        TestEntity entity = new TestEntity("测试文字!!");
        msg.setData(entity);
        Toast.makeText(context,"js调用Android成功",Toast.LENGTH_LONG).show();
        // do some things

        this.callback(context,webView,callback,msg);
    }
}

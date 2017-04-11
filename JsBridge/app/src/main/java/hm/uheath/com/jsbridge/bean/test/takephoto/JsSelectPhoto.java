package hm.uheath.com.jsbridge.bean.test.takephoto;

import android.app.Activity;
import android.content.Intent;
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
public class JsSelectPhoto extends JsAction {
    public static final String ACTION="selectphoto";
    @Override
    public void handleAction(Activity context, String jsonStr, X5WebView webView, String callback) {
        JsMsgPhotoEntity photoEntity = new Gson().fromJson(jsonStr, JsMsgPhotoEntity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"}, 10000);
        }
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(intent, JsMsgPhotoEntity.SELECTREQUESTCODE);
    }
}

package hm.uheath.com.jsbridge.bean.test.takephoto;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;

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
public class JsTakePhoto extends JsAction {
    public static final String ACTION="takephoto";
    @Override
    public void handleAction(Activity context, String jsonStr, X5WebView webView, String callback) {

        JsMsgPhotoEntity photoEntity = new Gson().fromJson(jsonStr, JsMsgPhotoEntity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"}, 10000);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("autofocus",true); // 自动对焦
        context.startActivityForResult(intent, JsMsgPhotoEntity.TAKEREQUESTCODE);

    }
}

package hm.uheath.com.jsbridge;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hm.uheath.com.jsbridge.bean.test.takephoto.JsMsgPhotoEntity;
import hm.uheath.com.jsbridge.bean.test.takephoto.JsSelectPhoto;
import hm.uheath.com.jsbridge.bean.test.takephoto.JsTakePhoto;
import hm.uheath.com.jsbridge.bean.test.takephoto.PhotoEntity;
import hm.uheath.com.jsbridge.bean.test.test.JsTest;
import hm.uheath.com.jsbridge.bean.test.call.JsCall;
import hm.uheath.com.jsbridge.utils.Utils;
import hm.uheath.com.jsbridge.view.X5WebView;

/**
 * TODO:
 * 本demo使用x5内核，并对js与原生交互进行简单封装
 *
 *
 * @author hm
 * @version 4.0.0
 * @date 2017/4/10
 * @copyright uhealth.com
 */
public class MainActivity extends AppCompatActivity {
    X5WebView mWebView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        JsBridge.getInstance().init(this,mWebView);
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == JsMsgPhotoEntity.TAKEREQUESTCODE) {
            takePhoto(data);
        } else if (requestCode == JsMsgPhotoEntity.SELECTREQUESTCODE) {
            selectPhoto(data);
        }
    }
    /**
     * 拍照
     *
     * @param data 图片数据
     */
    private void takePhoto(Intent data) {
        Bundle bundle = data.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
        String imagePath = Utils.saveBitmapToFile(bitmap,null);
        String smallImagePath = Utils.saveBitmapToFile(Utils.getSmallBitmap(imagePath),"1");

        PhotoEntity photoEntity =new PhotoEntity();
        photoEntity.setImagePath(imagePath);
        photoEntity.setSmImagePath(smallImagePath);


        //回调js 返回数据
        HandleResult msg = new HandleResult();
        msg.setData(photoEntity);
        JsBridge.getInstance().callBackJs(this,mWebView,msg);

    }
    /**
     * 图库选择
     *
     * @param data 图片数据
     */
    private void selectPhoto(Intent data) {
        Uri selectImageUri = data.getData();
        String[] filePathColumn = new String[]{MediaStore.Images.Media.DATA};//要查询的列
        Cursor cursor = getContentResolver().query(selectImageUri, filePathColumn, null, null, null);
        String pirPath = null;
        String smallImagePath=null;
        while (cursor.moveToNext()) {
            pirPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));//所选择的图片路径
            smallImagePath = Utils.saveBitmapToFile(Utils.getSmallBitmap(pirPath),null);
        }
        cursor.close();
        PhotoEntity photoEntity =new PhotoEntity();
        photoEntity.setImagePath(pirPath);
        photoEntity.setSmImagePath(smallImagePath);

        //回调js 返回数据
        HandleResult msg = new HandleResult();
        msg.setData(photoEntity);
        JsBridge.getInstance().callBackJs(this,mWebView,msg);
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        JsBridge.getInstance().destroy();
    }
    private void initData() {
        JsBridge.getInstance().addJsAction(JsTest.ACTION,JsTest.class)
                .addJsAction(JsSelectPhoto.ACTION, JsSelectPhoto.class)
                .addJsAction(JsTakePhoto.ACTION, JsTakePhoto.class)
                .addJsAction(JsCall.ACTION,JsCall.class);
    }

    private void initView() {
        mWebView = (X5WebView) findViewById(R.id.webView);
        mWebView.loadUrl("file:///android_asset/demo.html");

    }
}

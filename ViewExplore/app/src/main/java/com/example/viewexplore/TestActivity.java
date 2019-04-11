package com.example.viewexplore;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class TestActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    TbsReaderView trv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        linearLayout = findViewById(R.id.ll);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .READ_EXTERNAL_STORAGE}, 1);
        }
    }

    public void ppt(View view) {
        trv = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        linearLayout.addView(trv, new LinearLayout.LayoutParams(-1, -1));
        File file = new File(Environment.getExternalStorageDirectory(), "testppt.pptx");
        Log.d("Lpp", "file.exists(): " + file.exists());
        Bundle localBundle = new Bundle();
        localBundle.putString("filePath", file.toString());
        localBundle.putString("tempPath", Environment.getExternalStorageDirectory() + "/" +
                "TbsReaderTemp");
        boolean bool = trv.preOpen("ppt", false);
        if (bool) {
            trv.openFile(localBundle);
        }
    }

    public void chooseFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            File file = uriToFile(uri, this);
            Log.d("Lpp", "getName: "+file.getName());
            Log.d("Lpp", "file: " + file);
            Log.d("Lpp", "file.exists: " + file.exists());
            Log.d("Lpp", "file.canRead: " + file.canRead());
            Log.d("Lpp", "onActivityResult: " + uri.getPath());
        }
    }

    public static File uriToFile(Uri uri, Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append
                        ("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new
                        String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images
                        .ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();
            if (path != null) {
                return new File(path);
            } else return null;
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        if (trv != null) {
            trv.onStop();
        }
        super.onDestroy();
    }

    public void pdf(View view) {
        TbsReaderView trv = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        linearLayout.addView(trv, new LinearLayout.LayoutParams(-1, -1));
        File file = new File(Environment.getExternalStorageDirectory(), "test.pdf");
        Log.d("Lpp", "file.exists(): " + file.exists());
        Bundle localBundle = new Bundle();
        localBundle.putString("filePath", file.toString());
        localBundle.putString("tempPath", Environment.getExternalStorageDirectory() + "/" +
                "TbsReaderTemp");
        boolean bool = trv.preOpen("pdf", false);
        if (bool) {
            trv.openFile(localBundle);
        }
    }

    public void word(View view) {
        TbsReaderView trv = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        linearLayout.addView(trv, new LinearLayout.LayoutParams(-1, -1));
        File file = new File(Environment.getExternalStorageDirectory(), "testword.docx");
        Log.d("Lpp", "file.exists(): " + file.exists());
        Bundle localBundle = new Bundle();
        localBundle.putString("filePath", file.toString());
        localBundle.putString("tempPath", Environment.getExternalStorageDirectory() + "/" +
                "TbsReaderTemp");
        boolean bool = trv.preOpen("doc", false);
        if (bool) {
            trv.openFile(localBundle);
        }
    }

}

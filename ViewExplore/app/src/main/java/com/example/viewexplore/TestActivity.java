package com.example.viewexplore;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

public class TestActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        linearLayout = findViewById(R.id.ll);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .READ_EXTERNAL_STORAGE},1);
        }
    }

    public void pdf(View view) {
        TbsReaderView trv = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        linearLayout.addView(trv,new LinearLayout.LayoutParams(-1,-1));
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
        linearLayout.addView(trv,new LinearLayout.LayoutParams(-1,-1));
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

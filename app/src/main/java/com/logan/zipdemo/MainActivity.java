package com.logan.zipdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.logan.lib7zip.VclZipApi;

import java.io.File;


@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {

    private String writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private String inputFilePath = Environment.getExternalStorageDirectory()+ File.separator+"aaa/test.zip";
    private String outputFilePath = Environment.getExternalStorageDirectory()+File.separator+"unZipFile" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //申请读写权限
        if (this.checkSelfPermission(writePermission) != PackageManager.PERMISSION_GRANTED){
            this.requestPermissions(new String[]{writePermission},100);
        }


        Button btnExtract = findViewById(R.id.btn_extract);
        btnExtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int result = VclZipApi.extractFile(inputFilePath, outputFilePath) ;
                        Log.i("ddlog", "run: "+result);
                    }
                }).start();
            }
        });
    }


}

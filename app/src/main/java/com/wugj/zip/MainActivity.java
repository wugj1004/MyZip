package com.wugj.zip;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDbFile(this,"zhanghao.jpg");
    }


    /**
     * 在/data/data/下创建一个res文件夹
     */
    private void copyDbFile(Context context, String fileName) {
        InputStream in = null;
        FileOutputStream out = null;
        String path = context.getFilesDir().getAbsolutePath()+"/res/";


        //创建文件夹
        File filePath = new File(path);
        if (!filePath.exists())
            filePath.mkdirs();


        File file = new File(path ,fileName);
        if (file.exists())
            return;

        try {
            in = context.getAssets().open(fileName); // 从assets目录下复制
            out = new FileOutputStream(file);
            int length = -1;
            byte[] buf = new byte[1024];
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}

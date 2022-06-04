package com.example.youtubevideodownloader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.renderscript.Sampler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {
    Button but;
    String link_ , resolution_ , format_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        but = (Button) findViewById(R.id.download);
        final EditText Link = findViewById(R.id.linkinput);
        final EditText Resolution = findViewById(R.id.resinput);
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Python py = Python.getInstance();
                PyObject pyobj =py.getModule("video");
                if(Link.getText().toString() != null){
                    link_ = Link.getText().toString();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter the Video Link", Toast.LENGTH_LONG).show();
                }

                if(Resolution.getText().toString() != null){

                    resolution_ = Resolution.getText().toString();
                }
                else{
                    resolution_ = "360p";
                }
                Link.clearFocus();
                Resolution.clearFocus();

                String folder_main = ".download_files";

                File f = new File(Environment.getExternalStorageDirectory() , folder_main);
                if (!f.exists()){
                    f.mkdirs();
                }

                String second_folder = ".video_files";
                File f2 = new File(Environment.getExternalStorageDirectory() , second_folder);
                if(!f2.exists()){
                    f2.mkdirs();
                }
                System.out.print(link_);
                pyobj.callAttr("download_video" ,link_ , resolution_ );
                Toast.makeText(MainActivity.this, "Video Downloaded", Toast.LENGTH_LONG).show();

                String delete_path = new String(Environment.getExternalStorageDirectory() + "/" + folder_main);
                String second_delete_path = new String(Environment.getExternalStorageDirectory() + "/" + second_folder);
                deleteFiles(delete_path);
                deleteFiles(second_delete_path);
            }
        });
    }

    private  static void deleteFiles(String path) {

        File file = new File(path);
        if(file.exists()){
            String deleteCmd = "rm -r " + path;
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(deleteCmd);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
//    public static boolean deleteDir(File dir){
//        if(dir.isDirectory()){
//            String[] children = dir.list();
//            for (int i = 0;i< children.length;i++){
//                boolean success = deleteDir(new File(dir , children[i]));
//                if(!success){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}

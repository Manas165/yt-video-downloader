package com.example.youtubevideodownloader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.io.IOException;
import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {
    Button but;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = 0;
        but = (Button) findViewById(R.id.download);
        final EditText Link = findViewById(R.id.linkinput);
        final EditText Resolution = findViewById(R.id.resinput);
        final EditText Format = findViewById(R.id.formatinput);
        final TextView tv = findViewById(R.id.link);
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Python py = Python.getInstance();
                PyObject pyobj =py.getModule("video");
                PyObject obj = pyobj.callAttr("download_video" ,Link.getText().toString() , Resolution.getText().toString() , Format.getText().toString()  , value);
                pyobj.callAttr("download_video" ,Link.getText().toString() , Resolution.getText().toString() , Format.getText().toString()  , value);
                Check_for();
            }
        });

    }

    public void Check_for(){
        if(value != 1){
            Check_for();
        }
        else{
            Toast.makeText(this , "Video Downloaded" , Toast.LENGTH_LONG ).show();
            value  = 0 ;
        }
    }


}
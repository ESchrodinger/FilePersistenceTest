package com.srcgzjiang.filepersistencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
//更新一下
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.edit);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);
    }

    public void save(String inputText){
        //文件输出流
        FileOutputStream  out = null;
        BufferedWriter writer = null;
        try{
            //data是给文件命名，Context.MODE_PRIVATE覆盖原内容
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            //最后如果writer没关就给它关上
            try {
                if (writer != null)
                    writer.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
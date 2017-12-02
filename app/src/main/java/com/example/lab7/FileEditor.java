package com.example.lab7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 王杏婷 on 2017/11/29.
 */

public class FileEditor extends AppCompatActivity{

    public Button save,load,clear,delete;
    private EditText filename,filedetail;
    String fileName,fileDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_editor);

        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
        clear = (Button) findViewById(R.id.clear);
        delete = (Button) findViewById(R.id.delete);
        filename = (EditText) findViewById(R.id.filename);
        filedetail = (EditText) findViewById(R.id.detail);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = filename.getText().toString();
                fileDetail = filedetail.getText().toString();
                if (!fileName.isEmpty()){
                    writeFile(fileName,fileDetail);
                }
                else {
                    Toast.makeText(getApplicationContext(), "保存失败", Toast.LENGTH_SHORT).show();
                }
                //openFileOutput()函数 用于写入数据，如果指定的文件不存在，则创建一个新的文件
                //MODE_PRIVATE：即仅打开文件可写入数据
                /*
                try(FileOutputStream fileOutputStream = openFileOutput("myFile.txt",MODE_PRIVATE)){
                    fileName = filename.getText().toString();
                    fileDetail = filedetail.getText().toString();
                    fileOutputStream.write(fileName.getBytes());
                    fileOutputStream.write(fileDetail.getBytes());
                    Toast.makeText(FileEditor.this, "Save successfully.", Toast.LENGTH_LONG).show();
                }
                catch (IOException e){
                    Toast.makeText(FileEditor.this,"Fail to save file",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }*/
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = filename.getText().toString();
                readFile(fileName);
                // openFileInput()函数用于打开一个与应用程序联系的私有文件输入流
                /*
                try(FileInputStream fileInputStream = openFileInput("myFile.txt")){
                    byte[] contents = new byte[fileInputStream.available()];
                    fileInputStream.read(contents);
                    filedetail.setText(new String(contents));
                    Toast.makeText(FileEditor.this, "Load successfully.", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(FileEditor.this,"Fail to load file",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }*/
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename.setText("");
                filedetail.setText("");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName = filename.getText().toString();
                if (!fileName.isEmpty()){
                    deleteFile(fileName);
                    filename.setText("");
                    filedetail.setText("");
                    Toast.makeText(getApplicationContext(),"删除成功", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }//end onCreate
    //写数据
    public void writeFile(String filename,String filedetail){
        try{
            FileOutputStream fileOutputStream = openFileOutput(filename,MODE_PRIVATE);
            byte[] bytes = filedetail.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //读数据
    public void readFile(String fileName) {
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            byte[] contents = new byte[fileInputStream.available()];
            fileInputStream.read(contents);
            filedetail.setText(new String(contents));
            Toast.makeText(FileEditor.this, "Load successfully.", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(FileEditor.this,"Fail to load file",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}

package com.example.lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button okBtn,clearBtn;
    private EditText newpassword,confirmpassword;
    private boolean tag=true;
    private String password1,password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial);

        okBtn=(Button)findViewById(R.id.ok );
        clearBtn=(Button)findViewById(R.id.clear);
        newpassword=(EditText) findViewById(R.id.newPassword);
        confirmpassword=(EditText) findViewById(R.id.confirmPassword);

        readAccount();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password1 = newpassword.getText().toString();
                password2 = confirmpassword.getText().toString();
                //有则打开，无则创建
                SharedPreferences sp = getSharedPreferences("info",MODE_PRIVATE);
                //处于编辑状态
                SharedPreferences.Editor editor = sp.edit();
                //存放数据
                editor.putString("password1",password1);
                editor.putString("password2",password2);
                //完成提交
                editor.commit();

                if (password2.equals("")&& password1.equals("")){
                    Toast.makeText(MainActivity.this,"Password cannot be empty.",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password1.equals(password2)){
                    Toast.makeText(MainActivity.this,"Password Mismatch.",Toast.LENGTH_LONG).show();
                    return;
                }
                if (password1.equals(password2)){
                    Intent intent = new Intent(MainActivity.this,FileEditor.class);
                    startActivity(intent);
                }
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tag==true){
                    newpassword.setText("");
                    confirmpassword.setText("");
                }else {
                    newpassword.setText("");
                }
            }
        });
    }//end onCreate()

    public void readAccount(){
        //获取
        SharedPreferences sp = getSharedPreferences("info",MODE_PRIVATE);
        //取出数据
        String pass1 = sp.getString("password1","");
        String pass2 = sp.getString("password2","");
        newpassword.setText(pass1);
        confirmpassword.setText(pass2);
        if (!pass2.equals("")){
            confirmpassword.setHint("");
            confirmpassword.setVisibility(View.INVISIBLE);
            newpassword.setHint("Password");
            newpassword.setText("");
            tag = false;
        }
    }
}

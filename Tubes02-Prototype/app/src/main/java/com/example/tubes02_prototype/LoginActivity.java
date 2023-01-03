package com.example.tubes02_prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tubes02_prototype.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements VolleyLogin.ILoginActivity{
    ActivityLoginBinding binding;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.getSupportActionBar().hide();

        binding.btnLogin.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        if(view == binding.btnLogin){
            String username = binding.etUsername.getText().toString();
            String pass = binding.etPass.getText().toString();
            String role =  binding.etRole.getText().toString();
            if(!username.equals("") && !pass.equals("") && !role.equals("")){
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra("email", username);
//                intent.putExtra("password", pass);
//                intent.putExtra("role", role);
//                //intent.putExtra("token", key);
//                startActivity(intent);
                volleyLogin("default.admin@domain.local", "mu8XyUogLi6Dk7", "admin");
                //Log.d("token", key);
//                if(!this.key.equals("E_AUTH_FAILED")){
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.putExtra("email", username);
//                    intent.putExtra("password", pass);
//                    intent.putExtra("role", role);
//                    intent.putExtra("token", key);
//                    startActivity(intent);
//                }else{
//                    Toast toast = Toast.makeText(this.getApplicationContext(), key, Toast.LENGTH_SHORT);
//                    toast.show();
//                }
            }else{
                Toast toast = Toast.makeText(this.getApplicationContext(), "Isi username dan password", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void volleyLogin(String email, String password, String role) {
        VolleyLogin login = new VolleyLogin(this, this);
        try {
            login.execute(email, password, role);
        }catch (Exception e){
            Log.d("error", "login error");
        }
    }

    public void getKeyLogin(String token) {
        this.key = token;
        if(!this.key.equals("E_AUTH_FAILED")){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("email", "default.admin@domain.local");
            intent.putExtra("password", "mu8XyUogLi6Dk7");
            intent.putExtra("role", "admin");
            intent.putExtra("token", key);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this.getApplicationContext(), key, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
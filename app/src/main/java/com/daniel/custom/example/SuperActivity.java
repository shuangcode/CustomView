package com.daniel.custom.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class SuperActivity extends Activity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void function2(){
        Toast.makeText(SuperActivity.this, "function", Toast.LENGTH_LONG).show();
    }
}

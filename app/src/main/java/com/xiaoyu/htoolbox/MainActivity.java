package com.xiaoyu.htoolbox;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateState();
    }

    protected void onResume() {
        super.onResume();
        updateState();
    }

    private void updateState() {
        if (Settings.System.canWrite(this)) {
            ImageView imageView = findViewById(R.id.imageView2);
            TextView textView = findViewById(R.id.textView2);
            imageView.setImageResource(R.drawable.ic_check);
            textView.setText(R.string.permission_granted);
        } else {
            ImageView imageView = findViewById(R.id.imageView2);
            TextView textView = findViewById(R.id.textView2);
            imageView.setImageResource(R.drawable.ic_error);
            textView.setText(R.string.permission_not_granted);
        }
    }

    public void onTextClicked(View view) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void onBtn1Clicked(View view) {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }
}
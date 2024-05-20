package com.xiaoyu.htoolbox;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ShareActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String origin = intent.getStringExtra(Intent.EXTRA_TEXT);
        String regex = "[^a-zA-Z0-9.:/_-]";
        if (origin != null) {
            String url = origin.replaceAll(regex, "");
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
        finish();
    }
}

package io.github.tripguider.jsonp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView tvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        tvView = (TextView) findViewById(R.id.txtTitle);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        //tvView.setText("It's " + title);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

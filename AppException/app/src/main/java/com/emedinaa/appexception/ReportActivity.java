package com.emedinaa.appexception;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ReportActivity extends ActionBarActivity {

    private Button butSendError;
    private TextView tviMessage;
    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        initUI();
        loadData();

    }

    private void initUI(){
        butSendError = (Button) findViewById(R.id.butSendReport);
        tviMessage = (TextView) findViewById(R.id.tviMessage);
    }

    private void loadData(){
        error = getIntent().getExtras().getString("error");
        getSupportActionBar().setTitle("REPORTE DE ERROR");
        tviMessage.setText(error);

        butSendError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getString(R.string.mail_error)});
                i.putExtra(Intent.EXTRA_SUBJECT, "Error App");
                i.putExtra(Intent.EXTRA_TEXT   , error);
                try {
                    startActivity(Intent.createChooser(i, "Enviando mail..."));
                } catch (android.content.ActivityNotFoundException ex) {

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

package edu.aku.hassannaqvi.epi_register_daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.AndroidManager;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVAActivity;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);
        bi.adminView.setVisibility(MainApp.admin ? View.VISIBLE : View.GONE);
        bi.username.setText("Welcome, " + MainApp.user.getFullname() + "!");
    }

    public void sectionPress(View view) {

        switch (view.getId()) {

            case R.id.openChildForm:
                //MainApp.cr = new FormCR();
                finish();
                startActivity(new Intent(this, SectionVAActivity.class));
                break;

            case R.id.dbm:
                startActivity(new Intent(this, AndroidManager.class));
                break;

        }
    }
}
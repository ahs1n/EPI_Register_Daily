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
import edu.aku.hassannaqvi.epi_register_daily.models.Form;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVAActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVBActivity;


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
                MainApp.form = new Form();
                finish();
                startActivity(new Intent(this, SectionVAActivity.class));
                break;

            case R.id.secA:
                MainApp.form = new Form();
                startActivity(new Intent(this, SectionVAActivity.class));
                break;

            case R.id.secB:
                MainApp.form = new Form();
                startActivity(new Intent(this, SectionVBActivity.class));
                break;

            case R.id.dbm:
                startActivity(new Intent(this, AndroidManager.class));
                break;

        }
    }
}
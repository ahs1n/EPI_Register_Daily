package edu.aku.hassannaqvi.epi_register_daily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.AndroidManager;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityMainBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVA;
import edu.aku.hassannaqvi.epi_register_daily.ui.ChangePasswordActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.SyncActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVAActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVBActivity;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(bi.toolbar);
        bi.toolbar.setSubtitle("Welcome, " + MainApp.user.getFullname() + (MainApp.admin ? " (Admin)" : "") + "!");
        bi.setCallback(this);
        bi.adminView.setVisibility(MainApp.admin ? View.VISIBLE : View.GONE);
//        bi.username.setText("Welcome, " + MainApp.user.getFullname() + "!");
        invalidateOptionsMenu();
    }

    public void sectionPress(View view) {

        switch (view.getId()) {

            case R.id.openForm:
                MainApp.formVA = new FormVA();
                finish();
                startActivity(new Intent(this, SectionVAActivity.class));
                break;

            case R.id.secA:
                MainApp.formVA = new FormVA();
                startActivity(new Intent(this, SectionVAActivity.class));
                break;

            case R.id.secB:
                MainApp.formVA = new FormVA();
                startActivity(new Intent(this, SectionVBActivity.class));
                break;

            case R.id.dbm:
                startActivity(new Intent(this, AndroidManager.class));
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_database:
                intent = new Intent(MainActivity.this, AndroidManager.class);
                startActivity(intent);
                break;

            case R.id.onSync:
                intent = new Intent(MainActivity.this, SyncActivity.class);
                startActivity(intent);
                break;
            case R.id.changePassword:
                intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        MenuItem action_database = menu.findItem(R.id.action_database);

        action_database.setVisible(MainApp.admin);
        return true;
    }
}
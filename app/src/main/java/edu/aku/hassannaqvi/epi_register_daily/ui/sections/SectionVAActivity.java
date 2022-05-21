package edu.aku.hassannaqvi.epi_register_daily.ui.sections;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formSEI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import edu.aku.hassannaqvi.epi_register_daily.MainActivity;
import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivitySectionVaBinding;

public class SectionVAActivity extends AppCompatActivity {
    private static final String TAG = "SectionVAActivity";
    ActivitySectionVaBinding bi;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_va);
        bi.setFormSEI(formSEI);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;

        MainApp.previousPage = formSEI.getVa04();
        setGPS();

/*        if (bi.va05acheck.isChecked()) {
            bi.va05ax.setText(MainApp.previousPage);
        } else {
            bi.va05ax.setText("");
        }*/
    }

    public void previousPage(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(bi.va04.getText()))
            return;

        /*if (bi.va05acheck.isChecked()){
            bi.va05ax.setText(form.getVa04());
        }*/

    }


    private boolean insertNewRecord() {
        if (!formSEI.getUid().equals("") || MainApp.superuser) return true;

        formSEI.populateMeta();

        long rowId = 0;
        try {
            rowId = db.addForm(formSEI);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.db_excp_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        formSEI.setId(String.valueOf(rowId));
        if (rowId > 0) {
            formSEI.setUid(formSEI.getDeviceId() + formSEI.getId());
            db.updatesFormColumn(TableContracts.FormsTable.COLUMN_UID, formSEI.getUid());
            return true;
        } else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private boolean updateDB() {
        if (MainApp.superuser) return true;

        int updcount = 0;
        try {
            updcount = db.updatesFormColumn(TableContracts.FormsTable.COLUMN_VA, formSEI.vAtoString());
        } catch (JSONException e) {
            Toast.makeText(this, R.string.upd_db + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    public void btnContinue(View view) {
        if (!formValidation()) return;
        if (!insertNewRecord()) return;
        if (updateDB()) {
            finish();
            startActivity(new Intent(this, SectionVBActivity.class).putExtra("complete", true));
        } else {
            Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
        }
    }


    public void btnEnd(View view) {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
        /*if (!Validator.emptyCheckingContainer(this, bi.GrpName))
            return false;

        if (bi.va05a.isChecked() && bi.va05ax.getText().toString().isEmpty()) {
            return Validator.emptyCustomTextBox(this, bi.va05ax, "Empty");
        }

        if (bi.va05b.isChecked() && bi.va05bx.getText().toString().isEmpty()) {
            return Validator.emptyCustomTextBox(this, bi.va05bx, "Empty");
        }

        return true;*/
    }


    @Override
    public void onBackPressed() {
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            formSEI.setGpsLat(lat);
            formSEI.setGpsLng(lang);
            formSEI.setGpsAcc(acc);
            formSEI.setGpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

}
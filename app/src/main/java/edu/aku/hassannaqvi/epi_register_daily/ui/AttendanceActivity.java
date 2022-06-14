package edu.aku.hassannaqvi.epi_register_daily.ui;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.attendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collection;

import edu.aku.hassannaqvi.epi_register_daily.MainActivity;
import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityAttendanceBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.Attendance;
import edu.aku.hassannaqvi.epi_register_daily.models.HealthFacilities;

public class AttendanceActivity extends AppCompatActivity {
    private static final String TAG = "AttendanceActivity";
    ActivityAttendanceBinding bi;
    private DatabaseHelper db;
    private ArrayList<String> healthFacilityNames, healthFacilityCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_attendance);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        attendance = new Attendance();
        bi.setForm(attendance);
        populateSpinner();
    }


    private void populateSpinner() {

        Collection<HealthFacilities> healthFacility = db.getHealthFacilityByUC(MainApp.user.getDist_id());

        healthFacilityNames = new ArrayList<>();
        healthFacilityCodes = new ArrayList<>();
        healthFacilityNames.add("...");
        healthFacilityCodes.add("...");

        for (HealthFacilities hf : healthFacility) {
            healthFacilityNames.add(hf.getHfName());
            healthFacilityCodes.add(hf.getHfCode());
        }

        if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu") || MainApp.user.getUserName().contains("test")) {
            healthFacilityNames.add("Test Facility 1");
            healthFacilityNames.add("Test Facility 2");
            healthFacilityNames.add("Test Facility 3");

            healthFacilityCodes.add("001");
            healthFacilityCodes.add("002");
            healthFacilityCodes.add("003");
        }
        // Apply the adapter to the spinner
        bi.facility.setAdapter(new ArrayAdapter<>(AttendanceActivity.this, R.layout.custom_spinner, healthFacilityNames));

        bi.facility.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    MainApp.selectedFacilityName = (healthFacilityNames.get(bi.facility.getSelectedItemPosition()));
                    attendance.setFacility(MainApp.selectedFacilityName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
    }


    private boolean insertNewRecord() {
        if (!attendance.getUid().equals("") || MainApp.superuser) return true;
        attendance.populateMeta();

        long rowId = 0;
        try {
            rowId = db.addAttandence(attendance);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.db_excp_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        attendance.setId(String.valueOf(rowId));
        if (rowId > 0) {
            attendance.setUid(attendance.getDeviceId() + attendance.getId());
            db.updatesAttendanceColumn(TableContracts.AttendanceTable.COLUMN_UID, attendance.getUid());
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
            updcount = db.updatesAttendanceColumn(TableContracts.AttendanceTable.COLUMN_ATT, attendance.aTTtoString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, R.string.upd_db + e.getMessage());
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
        setGPS();
        if (updateDB()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    @Override
    public void onBackPressed() {
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void setGPS() {
        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");

            if (lat == "0" && lang == "0") {
                Toast.makeText(this, "Could not obtained points", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Points set", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(GPSPref.getString("Time", "0"))).toString();

            attendance.setGpsLat(lat);
            attendance.setGpsLng(lang);
            attendance.setGpsAcc(acc);
            attendance.setGpsDT(date); // Timestamp is converted to date above

//            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }
}
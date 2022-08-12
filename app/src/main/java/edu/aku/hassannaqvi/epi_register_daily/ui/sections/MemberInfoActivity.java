package edu.aku.hassannaqvi.epi_register_daily.ui.sections;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import edu.aku.hassannaqvi.epi_register_daily.MainActivity;
import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityMemberInfoBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVB;
import edu.aku.hassannaqvi.epi_register_daily.ui.lists.RegisteredChildListActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.lists.RegisteredWomenListActivity;

public class MemberInfoActivity extends AppCompatActivity {
    private static final String TAG = "MemberInfoActivity";
    ActivityMemberInfoBinding bi;
    boolean b, group;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeUrdu);
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_member_info);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        setupListeners();
        setRange();

        MainApp.formVB.setUuid(MainApp.formVA.getUid());

        b = getIntent().getBooleanExtra("b", true);

        //formVA = db.getFormVA(UID);
        if (b) formVB = new FormVB();
        formVB.setVb01(String.valueOf(++MainApp.memberCount));

        group = getIntent().getBooleanExtra("group", true);
        if (group) {
            formVB.setVb03("2");
        } else formVB.setVb03("1");

        bi.setForm(formVB);


        formVB.setVillageCode(MainApp.workLocation.getWlVillageCode());
        formVB.setFacilityCode(MainApp.workLocation.getWlFacilityCode());
        formVB.setWlArea(MainApp.workLocation.getWlArea());

    }

    private void setRange() {
        bi.ageY.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bi.ageY.getText().toString().isEmpty()) return;

                if (formVB.getVb03().equals("1")) {
                    bi.ageY.setMaxvalue(49);
                    bi.ageY.setMinvalue(14);
                } else bi.ageY.setMaxvalue(2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

/*        if (MainApp.formVA.getUid().equals("")){
            try {
                MainApp.formVA = db.getFormByuid(MainApp.formVA.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }*/
/*
        String workLocationUID = sharedPref.getString("workLocationUID", "");
        try {
            MainApp.workLocation = db.getCurrentWorkLocation(workLocationUID);
            MainApp.selectedVillageCode = (MainApp.workLocation.getWlVillageCode());

        } catch (JSONException e) {
            Toast.makeText(this, "JSONException(WorkLocation): " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
    }

    private void setupListeners() {
        bi.vb03.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (bi.vb03a.isChecked()) {
                bi.vb04Name.setText(R.string.vb0402);
            } else bi.vb04Name.setText(R.string.vb0401);
        }));
    }


    //TODO: Won't insert if Opening Editable
    private boolean insertNewRecord() {
        if (!formVB.getUid().equals("") || MainApp.superuser) return true;
        formVB.populateMeta();

        long rowId = 0;
        try {
            rowId = db.addFormVB(formVB);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.db_excp_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        formVB.setId(String.valueOf(rowId));
        if (rowId > 0) {
            formVB.setUid(formVB.getDeviceId() + formVB.getId());
            db.updatesFormVBColumn(FormsVBTable.COLUMN_UID, formVB.getUid());
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
            updcount = db.updatesFormVBColumn(FormsVBTable.COLUMN_VB, formVB.vBtoString());
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
        setGPS();
        if (b) if (!insertNewRecord()) return;

        if (updateDB()) {
            finish();
            Toast.makeText(this, "Form saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, SectionVBActivity.class).putExtra("btn", false));
        } else {
            Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
        }
    }


    public void btnEnd(View view) {
        finish();
        MainApp.memberCount--;
        startActivity(new Intent(this, MainActivity.class));
    }


    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);
    }

    @Override
    public void onBackPressed() {
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        MainApp.memberCount--;
        if (group) {
            startActivity(new Intent(this, RegisteredChildListActivity.class));
        } else startActivity(new Intent(this, RegisteredWomenListActivity.class));
    }

    public void setGPS() {
/*        SharedPreferences GPSPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
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

            formVB.setGpsLat(lat);
            formVB.setGpsLng(lang);
            formVB.setGpsAcc(acc);
            formVB.setGpsDT(date); // Timestamp is converted to date above

            Toast.makeText(this, "Points set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setPoints: " + e.getMessage());
        }*/

    }
}
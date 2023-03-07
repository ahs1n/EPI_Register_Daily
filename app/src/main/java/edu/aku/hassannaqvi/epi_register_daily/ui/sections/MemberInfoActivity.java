package edu.aku.hassannaqvi.epi_register_daily.ui.sections;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.workLocation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import edu.aku.hassannaqvi.epi_register_daily.MainActivity;
import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityMemberInfoBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVB;
import edu.aku.hassannaqvi.epi_register_daily.models.VaccinesData;
import edu.aku.hassannaqvi.epi_register_daily.models.Villages;
import edu.aku.hassannaqvi.epi_register_daily.ui.lists.VaccinatedChildListActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.lists.VaccinatedWomenListActivity;

public class MemberInfoActivity extends AppCompatActivity {
    private static final String TAG = "MemberInfoActivity";
    ActivityMemberInfoBinding bi;
    boolean b, group;
    private DatabaseHelper db;
    private ArrayList<String> villageNames, villageCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeUrdu);
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_member_info);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        setupListeners();
        setRange();
        populateVillageSpinner();

        MainApp.flag = true;

        b = getIntent().getBooleanExtra("b", true);

        if (b) formVB = new FormVB();
        formVB.setVb01(String.valueOf(++MainApp.memberCount));

        group = getIntent().getBooleanExtra("group", true);
        if (group) {
            formVB.setVb03("2");
        } else formVB.setVb03("1");

        bi.setForm(formVB);
        initUI();

    }

    private void initUI() {
        formVB.setUuid(MainApp.formVA.getUid());
        formVB.setFacilityCode(MainApp.workLocation.getWlFacilityCode());
        formVB.setWlArea(MainApp.workLocation.getWlArea());

        if (workLocation.getWlLocationType().equals("1")) {
            bi.fldGrpCVvb04c.setVisibility(View.VISIBLE);
        } else formVB.setVillageCode(MainApp.workLocation.getWlVillageCode());

        MainApp.setMaxYearByCurrent(bi.vb04by);
        MainApp.setMaxMonthByCurrent(bi.vb04bm);
        MainApp.setMaxDayByCurrent(bi.vb04bd);
    }

    private void populateVillageSpinner() {

        Collection<Villages> villages = db.getAllVillagesByUC(MainApp.user.getUccode());

        villageNames = new ArrayList<>();
        villageCodes = new ArrayList<>();
        villageNames.add("...");
        villageCodes.add("...");

        for (Villages vg : villages) {
            villageNames.add(vg.getVillageName());
            villageCodes.add(vg.getVillageCode());
        }

        if (MainApp.user.getUserName().contains("test") || MainApp.user.getUserName().contains("dmu") || MainApp.user.getUserName().contains("user")) {
            villageNames.add("Test Village 1");
            villageNames.add("Test Village 2");
            villageNames.add("Test Village 3");

            villageCodes.add("001");
            villageCodes.add("002");
            villageCodes.add("003");
        }
        // Apply the adapter to the spinner
        bi.villageName.setAdapter(new ArrayAdapter<>(MemberInfoActivity.this, R.layout.custom_spinner, villageNames));

        bi.villageName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    formVB.setVillageCode(villageCodes.get(bi.villageName.getSelectedItemPosition()));
                    if (villageCodes.get(bi.villageName.getSelectedItemPosition()).equals("99")) {
                        bi.fldGrpCVvb06a.setVisibility(View.VISIBLE);
                    } else {
                        Clear.clearAllFields(bi.fldGrpCVvb06a);
                        bi.fldGrpCVvb06a.setVisibility(View.GONE);
                    }
                    //workLocation.setWlVillageName(villageNames.get(bi.villageName.getSelectedItemPosition()));

//                    MainApp.selectedVillageCode = (villageCodes.get(bi.wlVillageName.getSelectedItemPosition()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
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
        MainApp.lockScreen(this);

    }

    private void setupListeners() {

        bi.vb04by.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bi.vb04by.getText().toString().isEmpty() || bi.vb04bm.getText().toString().isEmpty() || bi.vb04bd.getText().toString().isEmpty())
                    return;
                bi.vb04bm.setMaxvalue(Integer.parseInt(bi.vb04by.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR) ?
                        Calendar.getInstance().get(Calendar.MONTH) + 1 : 12f);
                bi.vb04bd.setMaxvalue(Integer.parseInt(bi.vb04by.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR)
                        && Integer.parseInt(bi.vb04bm.getText().toString()) == Calendar.getInstance().get(Calendar.MONTH) + 1 ?
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH) : 31f);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bi.vb04bm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (bi.vb04by.getText().toString().isEmpty() || bi.vb04bm.getText().toString().isEmpty())
                    return;

                bi.vb04bm.setMaxvalue(Integer.parseInt(bi.vb04by.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR) ?
                        Calendar.getInstance().get(Calendar.MONTH) + 1 : 12f);
                bi.vb04bd.setMaxvalue(Integer.parseInt(bi.vb04by.getText().toString()) == Calendar.getInstance().get(Calendar.YEAR)
                        && Integer.parseInt(bi.vb04bm.getText().toString()) == Calendar.getInstance().get(Calendar.MONTH) + 1 ?
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH) : 31f);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

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
            if (formVB.getVb03().equals("1")) {
                startActivity(new Intent(this, SectionVB_womanActivity.class).putExtra("btn", false));
            } else {
                startActivity(new Intent(this, SectionVBActivity.class).putExtra("btn", false));
            }
        } else {
            Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
        }
    }


    public void btnEnd(View view) {
        finish();
        MainApp.memberCount--;
        startActivity(new Intent(this, MainActivity.class));
    }

    public void checkMember(View view) {

            List<VaccinesData> vaccinesDataArrayList = new ArrayList<>();
            try {
                vaccinesDataArrayList = db.getSyncedVaccinatedChildBYCardNo(bi.vb02.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (vaccinesDataArrayList.size() > 0) {
                Snackbar.make(bi.toolbar, R.string.alreadyExistMember, Snackbar.LENGTH_LONG).show();
                bi.fldGrpInfo.setVisibility(View.GONE);
                bi.villageName.setSelection(0);
                bi.vb04by.setText("");
                bi.vb04bm.setText("");
                bi.vb04bd.setText("");
                bi.ageY.setText("");
                bi.vb05m.setText("");
                bi.vb05d.setText("");
                bi.vb05a.clearCheck();
                bi.vb05ba.setChecked(false);
                bi.vb05bb.setChecked(false);
                bi.vb05bc.setChecked(false);
                bi.vb06.setText("");
                bi.vb06a.setText("");
                bi.vb07.setText("");
                bi.vb09.clearCheck();
            } else {
                bi.fldGrpInfo.setVisibility(View.VISIBLE);
            }
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
            startActivity(new Intent(this, VaccinatedChildListActivity.class));
        } else startActivity(new Intent(this, VaccinatedWomenListActivity.class));
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
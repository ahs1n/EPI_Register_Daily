package edu.aku.hassannaqvi.epi_register_daily.ui.sections;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivitySectionVbBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVB;
import edu.aku.hassannaqvi.epi_register_daily.ui.TakePhoto;
import edu.aku.hassannaqvi.epi_register_daily.ui.lists.RegisteredMembersListActivity;

public class SectionVBActivity extends AppCompatActivity {
    private static final String TAG = "SectionVBActivity";
    ActivitySectionVbBinding bi;
    private DatabaseHelper db;
    boolean b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeUrdu);
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_vb);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        setGPS();
        setupListeners();

        b = getIntent().getBooleanExtra("b", true);

        if (b) formVB = new FormVB();
        bi.setForm(formVB);
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
        if (b) if (!insertNewRecord()) return;
        if (updateDB()) {
            finish();
            Toast.makeText(this, "Form saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, SectionVBActivity.class));
        } else {
            Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
        }
    }


    public void btnEnd(View view) {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }


    private boolean formValidation() {

        if (!Validator.emptyCheckingContainer(this, bi.GrpName)) {
            return false;
        }
        if (formVB.getVb03().equals("2") && formVB.getFrontfilename().equals("")) {
            return Validator.emptyCustomTextBox(this, bi.frontFileName, "Please take front photo of Vaccination Card.");
        }

        // Check back photo taken
        if (formVB.getVb03().equals("2") && formVB.getBackfilename().equals("")) {
            return Validator.emptyCustomTextBox(this, bi.backFileName, "Please take back photo of Vaccination Card.");

        }

        if (bi.vb08ca98.isChecked() && formVB.getVb08ca().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08ca, bi.vb08caa);

        if (bi.vb08cb98.isChecked() && formVB.getVb08cb().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08cb, bi.vb08cba);

        if (bi.vb08cc98.isChecked() && formVB.getVb08cc().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08cc, bi.vb08cca);

        if (bi.vb08cd98.isChecked() && formVB.getVb08cd().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08cd, bi.vb08cda);

        if (bi.vb08ce98.isChecked() && formVB.getVb08ce().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08ce, bi.vb08cea);

        if (bi.vb08cf98.isChecked() && formVB.getVb08cf().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08cf, bi.vb08cfa);

        if (bi.vb08cg98.isChecked() && formVB.getVb08cg().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08cg, bi.vb08cga);

        if (bi.vb08ch98.isChecked() && formVB.getVb08ch().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08ch, bi.vb08cha);

        if (bi.vb08ci98.isChecked() && formVB.getVb08ci().equals(""))
            return Validator.emptyRadioButton(this, bi.vb08ci, bi.vb08cia);


        return true;
    }

    @Override
    public void onBackPressed() {
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, RegisteredMembersListActivity.class));
    }


    public void takePhoto(View view) {

        Intent intent = new Intent(this, TakePhoto.class);

        intent.putExtra("picID", formVB.getVb02() + "_" + MainApp.formVB.getVb02() + "_");
        intent.putExtra("Name", formVB.getVb04a());

        if (view.getId() == R.id.frontPhoto) {
            intent.putExtra("picView", "front".toUpperCase());
            startActivityForResult(intent, 1); // Activity is started with requestCode 1 = Front
        } else if (view.getId() == R.id.backPhoto) {
            intent.putExtra("picView", "back".toUpperCase());
            startActivityForResult(intent, 2); // Activity is started with requestCode 2 = Back
        } else {
            intent.putExtra("picView", "child".toUpperCase());
            startActivityForResult(intent, 3); // Activity is started with requestCode 3 = Child
        }
    }


    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, requestCode + "_" + resultCode, Toast.LENGTH_SHORT).show();

            String fileName = data.getStringExtra("FileName");

            // Check if the requestCode 1 = Front : 2 = Back -- resultCode 1 = Success : 2 = Failure
            // Results received with requestCode 1 = Front

            if (requestCode == 1 && resultCode == RESULT_OK) {
                Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();

                bi.frontFileName.setText(fileName);
                bi.frontPhoto.setEnabled(false);

            } else if (requestCode == 1 && resultCode != RESULT_CANCELED) {
                Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();

                //TODO: Implement functionality below when photo was not taken
                // ...
                //     bi.frontFileName.setText("Photo not taken.");

            }

            // Results received with requestCode 2 = Back
            if (requestCode == 2 && resultCode == RESULT_OK) {
                Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
                bi.backFileName.setText(fileName);
                bi.backPhoto.setEnabled(false);
            } else if (requestCode == 2 && resultCode != RESULT_CANCELED) {

                Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();

                //TODO: Implement functionality below when photo was not taken
                // ...
                //      bi.backFileName.setText("Photo not taken.");

            }

            // Results received with requestCode 2 = child
            if (requestCode == 3 && resultCode == RESULT_OK) {
                Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
                bi.childFileName.setText(fileName);
                bi.childPhoto.setEnabled(false);
            } else if (requestCode == 3 && resultCode != RESULT_CANCELED) {

                Toast.makeText(this, "Photo Cancelled", Toast.LENGTH_SHORT).show();

                //TODO: Implement functionality below when photo was not taken
                // ...
                //      bi.backFileName.setText("Photo not taken.");

            }
        }
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

            formVB.setGpsLat(lat);
            formVB.setGpsLng(lang);
            formVB.setGpsAcc(acc);
            formVB.setGpsDT(date); // Timestamp is converted to date above

//            Toast.makeText(this, "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }
}
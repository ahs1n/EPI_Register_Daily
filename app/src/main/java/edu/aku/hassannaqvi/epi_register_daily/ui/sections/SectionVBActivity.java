package edu.aku.hassannaqvi.epi_register_daily.ui.sections;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccines;

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
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivitySectionVbBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.Vaccines;
import edu.aku.hassannaqvi.epi_register_daily.ui.TakePhoto;

public class SectionVBActivity extends AppCompatActivity {
    private static final String TAG = "SectionVBActivity";
    ActivitySectionVbBinding bi;
    private DatabaseHelper db;
    boolean b, group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeUrdu);
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_vb);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        setGPS();

        formVB.setVb01(String.valueOf(MainApp.memberCount + 1));

        MainApp.formVB.setUuid(MainApp.formVA.getUid());

        group = getIntent().getBooleanExtra("group", true);
        if (formVB.getVb03().equals("2")) {
//            formVB.setVb03("2");
            bi.fldGrpCVvb08c.setVisibility(View.VISIBLE);
            bi.fldGrpCVtakePhoto.setVisibility(View.VISIBLE);
        } else {
            bi.fldGrpCVvb08w.setVisibility(View.VISIBLE);
        }

        bi.pName.setText(formVB.getVb04a());
        bi.hName.setText(formVB.getVb04());
        bi.cardNo.setText(formVB.getVb02());
//        bi.vacStatus.setText(formVB.getVb08w());
        if (formVB.getVb03().equals("1")) {
            if (formVB.getVb08w().equals("1")) bi.vacStatus1.setText("TT1");
            if (formVB.getVb08w().equals("2")) bi.vacStatus2.setText("TT2");
            if (formVB.getVb08w().equals("3")) bi.vacStatus3.setText("TT3");
            if (formVB.getVb08w().equals("4")) bi.vacStatus4.setText("TT4");
            if (formVB.getVb08w().equals("5")) bi.vacStatus5.setText("TT5");
        }

        bi.setForm(formVB);
    }

/*    @Override
    protected void onResume() {
        super.onResume();

        if (MainApp.formVA.getUid().equals("")){
            try {
                MainApp.formVA = db.getFormByuid(MainApp.formVA.getId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/


    //TODO: Won't insert if Opening Editable
    /*private boolean insertNewRecord() {
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
    }*/

    private boolean insertVaccineRecord(String vaccCode, String antigen, String vaccDate) {
        //   if (!vaccines.getUid().equals("") || MainApp.superuser) return true;
        //    vaccines.populateMeta();

        vaccines.updateAntigen(vaccCode, antigen, vaccDate);
        long rowId = 0;
        try {
            rowId = db.addVaccine(vaccines);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.db_excp_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        vaccines.setId(String.valueOf(rowId));
        if (rowId > 0) {
            vaccines.setUid(vaccines.getDeviceId() + vaccines.getId());
            db.updatesVaccineColumn(TableContracts.VaccinesTable.COLUMN_UID, vaccines.getUid());
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
            updcount = db.updatesFormVBColumn(FormsVBTable.COLUMN_VAC, formVB.vACtoString());
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
//        if (b) if (!insertNewRecord()) return;
        vaccines = new Vaccines();
        vaccines.populateMeta();
        String caAntigen = null;

        // BCG
        if (bi.vb08ca98.isChecked()) {
            caAntigen = bi.vb08caa.isChecked() ? "1" : "-1";
            insertVaccineRecord("ca", caAntigen, bi.vb08cadt.getText().toString());
        }

        // OPV
        if (bi.vb08cb98.isChecked()) {
            caAntigen = bi.vb08cba.isChecked() ? "1"
                    : bi.vb08cbb.isChecked() ? "2"
                    : bi.vb08cbc.isChecked() ? "3"
                    : bi.vb08cbd.isChecked() ? "4"
                    : "-1";
            insertVaccineRecord("cb", caAntigen, bi.vb08cbdt.getText().toString());
        }

        // Hep B
        if (bi.vb08cc98.isChecked()) {
            caAntigen = bi.vb08cca.isChecked() ? "1"
                    : "-1";
            insertVaccineRecord("cc", caAntigen, bi.vb08ccdt.getText().toString());
        }

        // Penta
        if (bi.vb08cd98.isChecked()) {
            caAntigen = bi.vb08cda.isChecked() ? "1"
                    : bi.vb08cdb.isChecked() ? "2"
                    : bi.vb08cdc.isChecked() ? "3"
                    : "-1";
            insertVaccineRecord("cd", caAntigen, bi.vb08cddt.getText().toString());
        }

        // PCV
        if (bi.vb08ce98.isChecked()) {
            caAntigen = bi.vb08cea.isChecked() ? "1"
                    : bi.vb08ceb.isChecked() ? "2"
                    : bi.vb08cec.isChecked() ? "3"
                    : "-1";
            insertVaccineRecord("ce", caAntigen, bi.vb08cedt.getText().toString());
        }

        // Rota
        if (bi.vb08cf98.isChecked()) {
            caAntigen = bi.vb08cfa.isChecked() ? "1"
                    : bi.vb08cfb.isChecked() ? "2"
                    : "-1";
            insertVaccineRecord("cf", caAntigen, bi.vb08cfdt.getText().toString());
        }

        // IPV
        if (bi.vb08cg98.isChecked()) {
            caAntigen = bi.vb08cga.isChecked() ? "1"
                    : bi.vb08cgb.isChecked() ? "2"
                    : "-1";
            insertVaccineRecord("cg", caAntigen, bi.vb08cgdt.getText().toString());
        }

        // Measles
        if (bi.vb08ch98.isChecked()) {
            caAntigen = bi.vb08cha.isChecked() ? "1"
                    : bi.vb08chb.isChecked() ? "2"
                    : "-1";
            insertVaccineRecord("ch", caAntigen, bi.vb08chdt.getText().toString());
        }

        // Typhoid
        if (bi.vb08ci98.isChecked()) {
            caAntigen = bi.vb08cia.isChecked() ? "1"
                    : "-1";
            insertVaccineRecord("ci", caAntigen, bi.vb08cidt.getText().toString());
        }

        // TT
//        if (bi.vb03a.isChecked()) {
        caAntigen = bi.vb08waa.isChecked() ? "1"
                : bi.vb08wab.isChecked() ? "2"
                : bi.vb08wac.isChecked() ? "3"
                : bi.vb08wad.isChecked() ? "4"
                : bi.vb08wae.isChecked() ? "5"
                : "-1";
        insertVaccineRecord("TT", caAntigen, bi.vb08wdt.getText().toString());
//        }


        if (updateDB()) {
            finish();
            Toast.makeText(this, "Form saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
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

        if (bi.vb08ca98.isChecked() && formVB.getVb08ca().equals("") || bi.vb08ca98.isChecked() && formVB.getVb08cadt().equals(""))
//            return Validator.emptyRadioButton(this, bi.vb08ca, bi.vb08caa);
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08ca, false);

        if (bi.vb08cb98.isChecked() && formVB.getVb08cb().equals("") || bi.vb08cb98.isChecked() && formVB.getVb08cbdt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08cb, false);

        if (bi.vb08cc98.isChecked() && formVB.getVb08cc().equals("") || bi.vb08cc98.isChecked() && formVB.getVb08ccdt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08cc, false);

        if (bi.vb08cd98.isChecked() && formVB.getVb08cd().equals("") || bi.vb08cd98.isChecked() && formVB.getVb08cddt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08cd, false);

        if (bi.vb08ce98.isChecked() && formVB.getVb08ce().equals("") || bi.vb08ce98.isChecked() && formVB.getVb08cedt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08ce, false);

        if (bi.vb08cf98.isChecked() && formVB.getVb08cf().equals("") || bi.vb08cf98.isChecked() && formVB.getVb08cfdt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08cf, false);

        if (bi.vb08cg98.isChecked() && formVB.getVb08cg().equals("") || bi.vb08cg98.isChecked() && formVB.getVb08cgdt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08cg, false);

        if (bi.vb08ch98.isChecked() && formVB.getVb08ch().equals("") || bi.vb08ch98.isChecked() && formVB.getVb08chdt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08ch, false);

        if (bi.vb08ci98.isChecked() && formVB.getVb08ci().equals("") || bi.vb08ci98.isChecked() && formVB.getVb08cidt().equals(""))
            return Validator.emptyCheckingContainer(this, bi.fldGrpVB08ci, false);


        return true;
    }

    @Override
    public void onBackPressed() {
         Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
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
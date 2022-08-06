package edu.aku.hassannaqvi.epi_register_daily.ui.sections;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccineCount;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccines;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;

import java.util.ArrayList;

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
    boolean btn, group, b;

    public static char getChar(int i) {
        return i < 0 || i > 25 ? '?' : (char) ('a' + i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeUrdu);
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_vb);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        setGPS();
        setupListeners();

        formVB.setUuid(MainApp.formVA.getUid());

        btn = getIntent().getBooleanExtra("btn", true);
        if (btn) bi.btnEnd.setVisibility(View.VISIBLE);

        group = getIntent().getBooleanExtra("group", true);
        if (formVB.getVb03().equals("2")) {
            bi.fldGrpCVvb08c.setVisibility(View.VISIBLE);
            bi.fldGrpCVtakePhoto.setVisibility(View.VISIBLE);
        } else {
            bi.wraVACINFO.setVisibility(View.VISIBLE);
            bi.fldGrpCVvb08w.setVisibility(View.VISIBLE);
        }

        bi.pName.setText(formVB.getVb04a());
        bi.hName.setText(formVB.getVb04());
        bi.cardNo.setText(formVB.getVb02());
        if (formVB.getVb03().equals("1")) {
            bi.vacStatus.setText("TT" + formVB.getVb08w());
        }
        bi.vacDate.setText(formVB.getVb08wdt());

        vaccinesList = new ArrayList<>();
        vaccineCount = 0;
        ArrayList antigenName = new ArrayList<String>();

        Log.d(TAG, "onCreate(vaccineList): " + vaccinesList.size());
        try {
            vaccinesList = db.getVaccinatedMembersBYUID();
            for (Vaccines vaccines : vaccinesList) {
                antigenName.add(vaccines.getVb08CCode() + vaccines.getVb08CAntigen() + vaccines.getVb08WCode() + vaccines.getVb08WAntigen());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JSONException(FormVB): " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        int firstTrue = -1;
        String baseId = "";
        ArrayList<Boolean> results = new ArrayList<Boolean>();

        for (int i = 0; i < antigenName.size(); i++) {
            String antigen = (String) antigenName.get(i);

            //BCG
            baseId = "vb08ca";
            results.clear();
            showHideDoneCheck(antigen.equals("ca1"), bi.vb08caa, bi.vb08caatick);

            // OPV
            baseId = "vb08cb";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("cb1"), bi.vb08cba, bi.vb08cbatick));
            results.add(showHideDoneCheck(antigen.equals("cb2"), bi.vb08cbb, bi.vb08cbbtick));
            results.add(showHideDoneCheck(antigen.equals("cb3"), bi.vb08cbc, bi.vb08cbctick));
            results.add(showHideDoneCheck(antigen.equals("cb4"), bi.vb08cbd, bi.vb08cbdtick));
            verifyCrossTicks(results, baseId);


            //Hep B
            baseId = "vb08cc";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("cc1"), bi.vb08cca, bi.vb08ccatick));
            verifyCrossTicks(results, baseId);

            // Penta
            baseId = "vb08cd";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("cd1"), bi.vb08cda, bi.vb08cdatick));
            results.add(showHideDoneCheck(antigen.equals("cd2"), bi.vb08cdb, bi.vb08cdbtick));
            results.add(showHideDoneCheck(antigen.equals("cd3"), bi.vb08cdc, bi.vb08cdctick));
            verifyCrossTicks(results, baseId);

            // PCV
            baseId = "vb08ce";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("ce1"), bi.vb08cea, bi.vb08ceatick));
            results.add(showHideDoneCheck(antigen.equals("ce2"), bi.vb08ceb, bi.vb08cebtick));
            results.add(showHideDoneCheck(antigen.equals("ce3"), bi.vb08cec, bi.vb08cectick));
            verifyCrossTicks(results, baseId);

            // Rota
            baseId = "vb08cf";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("cf1"), bi.vb08cfa, bi.vb08cfatick));
            results.add(showHideDoneCheck(antigen.equals("cf2"), bi.vb08cfb, bi.vb08cfbtick));
            verifyCrossTicks(results, baseId);

            // IPV
            baseId = "vb08cg";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("cg1"), bi.vb08cga, bi.vb08cgatick));
            results.add(showHideDoneCheck(antigen.equals("cg2"), bi.vb08cgb, bi.vb08cgbtick));
            verifyCrossTicks(results, baseId);

            // Measles
            baseId = "vb08ch";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("ch1"), bi.vb08cha, bi.vb08chatick));
            results.add(showHideDoneCheck(antigen.equals("ch2"), bi.vb08chb, bi.vb08chbtick));
            verifyCrossTicks(results, baseId);

            // Typhoid
            baseId = "vb08ci";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("ci1"), bi.vb08cia, bi.vb08ciatick));
            verifyCrossTicks(results, baseId);

            // TT
            baseId = "vb08wa";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("TT1"), bi.vb08waa, bi.vb08waatick));
            results.add(showHideDoneCheck(antigen.equals("TT2"), bi.vb08wab, bi.vb08wabtick));
            results.add(showHideDoneCheck(antigen.equals("TT3"), bi.vb08wac, bi.vb08wactick));
            results.add(showHideDoneCheck(antigen.equals("TT4"), bi.vb08wad, bi.vb08wadtick));
            results.add(showHideDoneCheck(antigen.equals("TT5"), bi.vb08wae, bi.vb08waetick));
            verifyCrossTicks(results, baseId);
        }

        bi.setForm(formVB);
    }

    private void setupListeners() {
        bi.vb08w.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08wdt));
    }

    private View getViewDynamically(String viewId) {
        return findViewById(getResources()
                .getIdentifier(viewId, "id", getPackageName()));
    }

    private void markAsCrossed(String baseId, int index) {
        String letter = String.valueOf(getChar(index));
        if (letter.equals("?"))
            return;

        RadioButton radioButton = (RadioButton) getViewDynamically(baseId + letter);
        ImageView imgCross = (ImageView) getViewDynamically(baseId + letter + "tick");
        if (radioButton != null && imgCross != null) {
            if (imgCross.getVisibility() == View.GONE) {
                radioButton.setVisibility(View.GONE);
                imgCross.setVisibility(View.VISIBLE);
                imgCross.setBackground(getResources().getDrawable(R.drawable.ic_baseline_close_24));
            }
        }
    }

    private void verifyCrossTicks(ArrayList<Boolean> results, String baseId) {
        int firstTrue = results.indexOf(true);
        if (firstTrue > 0) {
            for (int j = 0; j < firstTrue; j++) {
                markAsCrossed(baseId, j);
            }
        }
    }

    private boolean showHideDoneCheck(
            boolean condition,
            RadioButton radioButton,
            ImageView imgDone
    ) {
        if (condition) {
            radioButton.setVisibility(View.GONE);
            imgDone.setVisibility(View.VISIBLE);
            return true;
        }// else radioButton.setVisibility(View.VISIBLE);
        return false;
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
        vaccines.setUuid(formVB.getUid());
        vaccines.setFrontfilename(formVB.getFrontfilename());
        vaccines.setBackfilename(formVB.getBackfilename());
        vaccines.setChildfilename(formVB.getChildfilename());
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
        String waAntigen = null;

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
        if (!formVB.getVb08w().equals("")) {
            waAntigen = bi.vb08waa.isChecked() ? "1"
                    : bi.vb08wab.isChecked() ? "2"
                    : bi.vb08wac.isChecked() ? "3"
                    : bi.vb08wad.isChecked() ? "4"
                    : bi.vb08wae.isChecked() ? "5"
                    : "-1";
            insertVaccineRecord("TT", waAntigen, bi.vb08wdt.getText().toString());
        }


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
//        MainApp.memberCount --;
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
//        MainApp.memberCount --;
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
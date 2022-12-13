package edu.aku.hassannaqvi.epi_register_daily.ui.sections;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.flag;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccineCount;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccines;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesData;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesDataList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

import edu.aku.hassannaqvi.epi_register_daily.MainActivity;
import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivitySectionVbBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.VaccinesData;
import edu.aku.hassannaqvi.epi_register_daily.ui.TakePhoto;

public class SectionVBActivity extends AppCompatActivity {
    private static final String TAG = "SectionVBActivity";
    ActivitySectionVbBinding bi;
    private DatabaseHelper db;
    boolean btn, group, b;

    public static char getChar(int i) {
        return i < 0 || i > 25 ? '?' : (char) ('a' + i);
    }

    private int currentSelectedRadioButtonId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeUrdu);
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_vb);
        setSupportActionBar(bi.toolbar);
        db = MainApp.appInfo.dbHelper;
        setupListeners();

        formVB.setUuid(MainApp.formVA.getUid());

        btn = getIntent().getBooleanExtra("btn", true);
        if (btn) bi.btnEnd.setVisibility(View.VISIBLE);

        group = getIntent().getBooleanExtra("group", true);
        if (formVB.getVb03().equals("2") || vaccinesData.getVBO3().equals("2")) {
            bi.fldGrpCVvb08c.setVisibility(View.VISIBLE);
            bi.fldGrpCVtakePhoto.setVisibility(View.VISIBLE);
        } else {
            //bi.wraVACINFO.setVisibility(View.VISIBLE);
            bi.fldGrpCVvb08w.setVisibility(View.VISIBLE);
        }

        if (MainApp.flag) {
            bi.pName.setText(formVB.getVb04());
            bi.hName.setText(formVB.getVb04());
            bi.cardNo.setText(formVB.getVb02());
            if (formVB.getVb03().equals("1")) {
                bi.vacStatus.setText("TT" + formVB.getVb08w());
            }
            bi.vacDate.setText(formVB.getVb08wdt());
        } else {
            bi.pName.setText(vaccinesData.getVB04A());
            bi.hName.setText(vaccinesData.getVB04());
            bi.cardNo.setText(vaccinesData.getVBO2());

        }

        bi.vb08cb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                currentSelectedRadioButtonId = checkedId;
            }
        });
        bi.vb08cbdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (currentSelectedRadioButtonId != -1) {
                    String baseId = "vb08cb";
                    String userSelectedDate = editable.toString();

                    int doseNumber = -1;
                    for (int i=0; i<bi.vb08cb.getChildCount(); i++) {
                        View currentView = bi.vb08cb.getChildAt(i);
                        if (currentView instanceof RadioButton) {
                            doseNumber++;
                        }

                        if (currentSelectedRadioButtonId == currentView.getId()) {
                            View possibleTextView = bi.vb08cbll.getChildAt(i);
                            if (possibleTextView != null) {
                                TextView txtVaccineDate = (TextView) possibleTextView;
                                if (txtVaccineDate != null) {
                                    // This is the next TextView
                                    String prevDateStr = userSelectedDate;

                                    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                                    DateTime prevDate = fmt.parseDateTime(prevDateStr);

                                    int days = getDaysOfVaccineType(baseId, doseNumber);
                                    DateTime nextDate = prevDate.plusDays(days);
                                    txtVaccineDate.setText(nextDate.toString("yyyy-MM-dd"));
                                    txtVaccineDate.setVisibility(View.VISIBLE);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        });

        //vaccinesList = new ArrayList<>();
        MainApp.vaccinesDataList = new ArrayList<>();

        vaccineCount = 0;
        ArrayList antigenName = new ArrayList<String>();

        String baseId = "";
        ArrayList<Boolean> results = new ArrayList<Boolean>();

        Log.d(TAG, "onCreate(vaccineList): " + vaccinesDataList.size());
        try {
            //vaccinesList = db.getVaccinatedMembersBYUID();
            vaccinesDataList.clear();
            vaccinesDataList = db.getSyncedVaccinatedMembersBYUID(vaccinesData.getUID());

            for (VaccinesData vaccines : vaccinesDataList) {

                if (vaccinesData.getVBO3().equals("2")) {

                    //BCG
                    baseId = "vb08ca";
                    results.clear();
                    showHideDoneCheckWithText(vaccines.getBcg(), baseId, "a");

                    // OPV
                    baseId = "vb08cb";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getOpv0(), baseId, "a"));
                    results.add(showHideDoneCheckWithText(vaccines.getOpv1(), baseId, "b"));
                    results.add(showHideDoneCheckWithText(vaccines.getOpv2(), baseId, "c"));
                    results.add(showHideDoneCheckWithText(vaccines.getOpv3(), baseId, "d"));
                    verifyCrossTicks(results, baseId);
                    calculateNextVaccineDate(results, baseId);


                    //Hep B
                    baseId = "vb08cc";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getHepB(), baseId, "a"));
                    verifyCrossTicks(results, baseId);

                    // Penta
                    baseId = "vb08cd";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getPenta1(), baseId, "a"));
                    results.add(showHideDoneCheckWithText(vaccines.getPenta2(), baseId, "b"));
                    results.add(showHideDoneCheckWithText(vaccines.getPenta3(), baseId, "c"));
                    verifyCrossTicks(results, baseId);

                    // PCV
                    baseId = "vb08ce";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getPcv1(), baseId, "a"));
                    results.add(showHideDoneCheckWithText(vaccines.getPcv2(), baseId, "b"));
                    results.add(showHideDoneCheckWithText(vaccines.getPcv3(), baseId, "c"));
                    verifyCrossTicks(results, baseId);

                    // Rota
                    baseId = "vb08cf";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getRota1(), baseId, "a"));
                    results.add(showHideDoneCheckWithText(vaccines.getRota2(), baseId, "b"));
                    verifyCrossTicks(results, baseId);

                    // IPV
                    baseId = "vb08cg";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getIpv1(), baseId, "a"));
                    results.add(showHideDoneCheckWithText(vaccines.getIpv2(), baseId, "b"));
                    verifyCrossTicks(results, baseId);

                    // Measles
                    baseId = "vb08ch";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getMeasles1(), baseId, "a"));
                    results.add(showHideDoneCheckWithText(vaccines.getMeasles2(), baseId, "b"));
                    verifyCrossTicks(results, baseId);

                    // Typhoid
                    baseId = "vb08ci";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getTyphoid(), baseId, "a"));
                    verifyCrossTicks(results, baseId);
                } else {

                    // TT
                    baseId = "vb08wa";
                    results.clear();
                    results.add(showHideDoneCheckWithText(vaccines.getTt1(), baseId, "a"));
                    results.add(showHideDoneCheckWithText(vaccines.getTt2(), baseId, "b"));
                    results.add(showHideDoneCheckWithText(vaccines.getTt3(), baseId, "c"));
                    results.add(showHideDoneCheckWithText(vaccines.getTt4(), baseId, "d"));
                    results.add(showHideDoneCheckWithText(vaccines.getTt5(), baseId, "e"));
                    verifyCrossTicks(results, baseId);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JSONException(FormVB): " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        int firstTrue = -1;

        /*
        for (int i = 0; i < antigenName.size(); i++) {
            String antigen = (String) antigenName.get(i);

            //BCG
            baseId = "vb08ca";
            results.clear();
            showHideDoneCheck(!antigen.equals("BCG1"), bi.vb08caa, bi.vb08caatick);

            // OPV
            baseId = "vb08cb";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("OPV1"), bi.vb08cba, bi.vb08cbatick));
            results.add(showHideDoneCheck(antigen.equals("OPV2"), bi.vb08cbb, bi.vb08cbbtick));
            results.add(showHideDoneCheck(antigen.equals("OPV3"), bi.vb08cbc, bi.vb08cbctick));
            results.add(showHideDoneCheck(antigen.equals("OPV4"), bi.vb08cbd, bi.vb08cbdtick));
            verifyCrossTicks(results, baseId);


            //Hep B
            baseId = "vb08cc";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("HepB1"), bi.vb08cca, bi.vb08ccatick));
            verifyCrossTicks(results, baseId);

            // Penta
            baseId = "vb08cd";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("Penta1"), bi.vb08cda, bi.vb08cdatick));
            results.add(showHideDoneCheck(antigen.equals("Penta2"), bi.vb08cdb, bi.vb08cdbtick));
            results.add(showHideDoneCheck(antigen.equals("Penta3"), bi.vb08cdc, bi.vb08cdctick));
            verifyCrossTicks(results, baseId);

            // PCV
            baseId = "vb08ce";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("PCV1"), bi.vb08cea, bi.vb08ceatick));
            results.add(showHideDoneCheck(antigen.equals("PCV2"), bi.vb08ceb, bi.vb08cebtick));
            results.add(showHideDoneCheck(antigen.equals("PCV3"), bi.vb08cec, bi.vb08cectick));
            verifyCrossTicks(results, baseId);

            // Rota
            baseId = "vb08cf";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("Rota1"), bi.vb08cfa, bi.vb08cfatick));
            results.add(showHideDoneCheck(antigen.equals("Rota2"), bi.vb08cfb, bi.vb08cfbtick));
            verifyCrossTicks(results, baseId);

            // IPV
            baseId = "vb08cg";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("IPV1"), bi.vb08cga, bi.vb08cgatick));
            results.add(showHideDoneCheck(antigen.equals("IPV2"), bi.vb08cgb, bi.vb08cgbtick));
            verifyCrossTicks(results, baseId);

            // Measles
            baseId = "vb08ch";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("Measles1"), bi.vb08cha, bi.vb08chatick));
            results.add(showHideDoneCheck(antigen.equals("Measles2"), bi.vb08chb, bi.vb08chbtick));
            verifyCrossTicks(results, baseId);

            // Typhoid
            baseId = "vb08ci";
            results.clear();
            results.add(showHideDoneCheck(antigen.equals("Typhoid1"), bi.vb08cia, bi.vb08ciatick));
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
        }*/

        if (vaccinesDataList.size() == 19) {
            bi.vb08ca98.setEnabled(false);
            bi.vb08cb98.setEnabled(false);
            bi.vb08cc98.setEnabled(false);
            bi.vb08cd98.setEnabled(false);
            bi.vb08ce98.setEnabled(false);
            bi.vb08cf98.setEnabled(false);
            bi.vb08cg98.setEnabled(false);
            bi.vb08ch98.setEnabled(false);
            bi.vb08ci98.setEnabled(false);
            bi.btnContinue.setVisibility(View.GONE);
        } else {
            formVB.setFrontfilename("");
            formVB.setBackfilename("");
            formVB.setChildfilename("");
        }

        bi.setForm(formVB);
    }

    private void setupListeners() {
        bi.vb08w.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08wdt));

        bi.vb08ca.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08cadt));
        bi.vb08cb.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08cbdt));
        bi.vb08cc.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08ccdt));
        bi.vb08cd.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08cddt));
        bi.vb08ce.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08cedt));
        bi.vb08cf.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08cfdt));
        bi.vb08cg.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08cgdt));
        bi.vb08ch.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08chdt));
        bi.vb08ci.setOnCheckedChangeListener((radioGroup, i) -> Clear.clearAllFields(bi.vb08cidt));

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
        int firstTrue = results.lastIndexOf(true);
        if (firstTrue > 0) {
            for (int j = 0; j < firstTrue; j++) {
                markAsCrossed(baseId, j);
            }
        }
    }

    private void calculateNextVaccineDate(ArrayList<Boolean> results, String baseId) {
        int firstTrue = results.lastIndexOf(true);
        if (firstTrue > 0) {
            String prevLetter = String.valueOf(getChar(firstTrue));
            String letter = String.valueOf(getChar(firstTrue + 1));
            if (letter.equals("?") || prevLetter.equals("?"))
                return;

            String vaccineType = baseId;
            int doseNumber = firstTrue;

            // baseId = opv
            // letter = dose number

            RadioButton radioButton = (RadioButton) getViewDynamically(baseId + letter);
            TextView txtVaccineDatePrevious = (TextView) getViewDynamically(baseId + prevLetter + "txt");
            TextView txtVaccineDate = (TextView) getViewDynamically(baseId + letter + "txt");

            if (radioButton != null && txtVaccineDate != null && txtVaccineDatePrevious != null) {
                String prevDateStr = txtVaccineDatePrevious.getText().toString();

                DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                DateTime prevDate = fmt.parseDateTime(prevDateStr);

                int days = getDaysOfVaccineType(vaccineType, doseNumber);
                DateTime nextDate = prevDate.plusDays(days);
                txtVaccineDate.setText(nextDate.toString("yyyy-MM-dd"));
                txtVaccineDate.setVisibility(View.VISIBLE);
            }
        }
    }

    private int getDaysOfVaccineType(String vaccineType, int currentDose) {
        int days = 0;
        switch (vaccineType) {
            case "vb08cb":      // OPV
                if (currentDose == 0)
                    days = 42;
                else if (currentDose == 1)
                    days = 28;
                else days = 30;
                break;
        }

        return days;
    }

    private boolean showHideDoneCheckWithText(
            String conditionText,
            String baseId,
            String letter
    ) {

        RadioButton radioButton = (RadioButton) getViewDynamically(baseId + letter);
        ImageView imgDone = (ImageView) getViewDynamically(baseId + letter + "tick");
        TextView txtVaccine = (TextView) getViewDynamically(baseId + letter + "txt");

        if (!conditionText.equals("")) {
            if (txtVaccine != null) {
                txtVaccine.setVisibility(View.VISIBLE);
                txtVaccine.setText(conditionText);
            }

            if (radioButton != null)
                radioButton.setVisibility(View.GONE);

            if (imgDone != null)
                imgDone.setVisibility(View.VISIBLE);

            return true;
        }// else radioButton.setVisibility(View.VISIBLE);
        return false;
    }


    private boolean insertVaccineRecord(String vaccCode, String antigen, String vaccDate) {

        setGPS();


        if (flag) {

            vaccines.setFrontfilename(formVB.getFrontfilename());
            vaccines.setBackfilename(formVB.getBackfilename());
            vaccines.setChildfilename(formVB.getChildfilename());
            vaccines.setGpsLat(formVB.getGpsLat());
            vaccines.setGpsLng(formVB.getGpsLng());
            vaccines.setGpsAcc(formVB.getGpsAcc());
            vaccines.setGpsDT(formVB.getGpsDT());
        } else {
            vaccines.setFrontfilename(bi.frontFileName.getText().toString());
            vaccines.setBackfilename(bi.backFileName.getText().toString());
            vaccines.setChildfilename(bi.childFileName.getText().toString());
            vaccines.setGpsLat(vaccines.getGpsLat());
            vaccines.setGpsLng(vaccines.getGpsLng());
            vaccines.setGpsAcc(vaccines.getGpsAcc());
            vaccines.setGpsDT(vaccines.getGpsDT());
        }

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

        setGPS();

        int updcount = 0;
        try {
            updcount = db.updatesFormVBColumn(FormsVBTable.COLUMN_VAC, formVB.vACtoString())
                    + db.updatesFormVBColumn(FormsVBTable.COLUMN_GPSLAT, formVB.getGpsLat())
                    + db.updatesFormVBColumn(FormsVBTable.COLUMN_GPSLNG, formVB.getGpsLng())
                    + db.updatesFormVBColumn(FormsVBTable.COLUMN_GPSACC, formVB.getGpsAcc())
                    + db.updatesFormVBColumn(FormsVBTable.COLUMN_GPSDATE, formVB.getGpsDT());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, R.string.upd_db + e.getMessage());
            Toast.makeText(this, R.string.upd_db + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (updcount == 5) {
            return true;
        } else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean updateDBVaccineFUP() {
        if (MainApp.superuser) return true;

        setGPS();

        int updcount = 0;
        updcount = db.updatesVaccineColumn(TableContracts.VaccinesTable.COLUMN_GPSLAT, vaccines.getGpsLat())
                + db.updatesVaccineColumn(TableContracts.VaccinesTable.COLUMN_GPSLNG, vaccines.getGpsLng())
                + db.updatesVaccineColumn(TableContracts.VaccinesTable.COLUMN_GPSACC, vaccines.getGpsAcc())
                + db.updatesVaccineColumn(TableContracts.VaccinesTable.COLUMN_GPSDATE, vaccines.getGpsDT());
        if (updcount == 4) {
            return true;
        } else {
            Toast.makeText(this, R.string.upd_db_error, Toast.LENGTH_SHORT).show();
            return false;
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

            if (flag) {
                formVB.setGpsLat(lat);
                formVB.setGpsLng(lang);
                formVB.setGpsAcc(acc);
                formVB.setGpsDT(date); // Timestamp is converted to date above
            } else {
                vaccines.setGpsLat(lat);
                vaccines.setGpsLng(lang);
                vaccines.setGpsAcc(acc);
                vaccines.setGpsDT(date); // Timestamp is converted to date above
            }

            Toast.makeText(this, "Points set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setPoints: " + e.getMessage());
        }

    }


    public void btnContinue(View view) {
        //vaccines = new Vaccines();
        if (!formValidation()) return;
//        if (b) if (!insertNewRecord()) return;

        if (flag) {
            vaccines.populateMeta();
        } else {
            vaccines.populateMetaFollowUp();
        }

        String caAntigen = null;
        String waAntigen = null;

        // BCG
        if (bi.vb08ca98.isChecked()) {
            caAntigen = bi.vb08caa.isChecked() ? "1" : "-1";
            insertVaccineRecord("BCG", caAntigen, bi.vb08cadt.getText().toString());
        }

        // OPV
        if (bi.vb08cb98.isChecked()) {
            caAntigen = bi.vb08cba.isChecked() ? "1"
                    : bi.vb08cbb.isChecked() ? "2"
                    : bi.vb08cbc.isChecked() ? "3"
                    : bi.vb08cbd.isChecked() ? "4"
                    : "-1";
            insertVaccineRecord("OPV", caAntigen, bi.vb08cbdt.getText().toString());
        }

        // Hep B
        if (bi.vb08cc98.isChecked()) {
            caAntigen = bi.vb08cca.isChecked() ? "1"
                    : "-1";
            insertVaccineRecord("HepB", caAntigen, bi.vb08ccdt.getText().toString());
        }

        // Penta
        if (bi.vb08cd98.isChecked()) {
            caAntigen = bi.vb08cda.isChecked() ? "1"
                    : bi.vb08cdb.isChecked() ? "2"
                    : bi.vb08cdc.isChecked() ? "3"
                    : "-1";
            insertVaccineRecord("Penta", caAntigen, bi.vb08cddt.getText().toString());
        }

        // PCV
        if (bi.vb08ce98.isChecked()) {
            caAntigen = bi.vb08cea.isChecked() ? "1"
                    : bi.vb08ceb.isChecked() ? "2"
                    : bi.vb08cec.isChecked() ? "3"
                    : "-1";
            insertVaccineRecord("PCV", caAntigen, bi.vb08cedt.getText().toString());
        }

        // Rota
        if (bi.vb08cf98.isChecked()) {
            caAntigen = bi.vb08cfa.isChecked() ? "1"
                    : bi.vb08cfb.isChecked() ? "2"
                    : "-1";
            insertVaccineRecord("Rota", caAntigen, bi.vb08cfdt.getText().toString());
        }

        // IPV
        if (bi.vb08cg98.isChecked()) {
            caAntigen = bi.vb08cga.isChecked() ? "1"
                    : bi.vb08cgb.isChecked() ? "2"
                    : "-1";
            insertVaccineRecord("IPV", caAntigen, bi.vb08cgdt.getText().toString());
        }

        // Measles
        if (bi.vb08ch98.isChecked()) {
            caAntigen = bi.vb08cha.isChecked() ? "1"
                    : bi.vb08chb.isChecked() ? "2"
                    : "-1";
            insertVaccineRecord("Measles", caAntigen, bi.vb08chdt.getText().toString());
        }

        // Typhoid
        if (bi.vb08ci98.isChecked()) {
            caAntigen = bi.vb08cia.isChecked() ? "1"
                    : "-1";
            insertVaccineRecord("Typhoid", caAntigen, bi.vb08cidt.getText().toString());
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

        if (flag) {
            if (updateDB()) {
                MainApp.flag = false;
                finish();
                Toast.makeText(this, "Form saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
            }
        } else {
            if (updateDBVaccineFUP()) {
                finish();
                Toast.makeText(this, "Form saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(this, R.string.fail_db_upd, Toast.LENGTH_SHORT).show();
            }

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
        if (flag) {
            if (formVB.getVb03().equals("2") && formVB.getFrontfilename().equals("")) {
                return Validator.emptyCustomTextBox(this, bi.frontFileName, "Please take front photo of Vaccination Card.");
            }

            // Check back photo taken
            if (formVB.getVb03().equals("2") && formVB.getBackfilename().equals("")) {
                return Validator.emptyCustomTextBox(this, bi.backFileName, "Please take back photo of Vaccination Card.");

            }

            // Check back photo taken
            if (formVB.getVb03().equals("2") && formVB.getChildfilename().equals("")) {
                return Validator.emptyCustomTextBox(this, bi.childFileName, "Please take photo of Child.");

            }
        } else {

        }
        if (vaccinesData.getVBO3().equals("2") && bi.frontFileName.getText().toString().equals("")) {
            return Validator.emptyCustomTextBox(this, bi.frontFileName, "Please take front photo of Vaccination Card.");
        }

        // Check back photo taken
        if (vaccinesData.getVBO3().equals("2") && bi.backFileName.getText().toString().equals("")) {
            return Validator.emptyCustomTextBox(this, bi.backFileName, "Please take back photo of Vaccination Card.");

        }

        // Check back photo taken
        if (vaccinesData.getVBO3().equals("2") && bi.childFileName.getText().toString().equals("")) {
            return Validator.emptyCustomTextBox(this, bi.childFileName, "Please take photo of Child.");

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

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
    }


    public void takePhoto(View view) {

        Intent intent = new Intent(this, TakePhoto.class);

        if (flag) {
            intent.putExtra("picID", formVB.getVb02() + "_" + MainApp.formVB.getVb02() + "_");
            intent.putExtra("Name", formVB.getVb04a());
        } else {
            intent.putExtra("picID", vaccinesData.getVBO2() + "_" + vaccinesData.getVBO2() + "_");
            intent.putExtra("Name", vaccinesData.getVB04A());
        }

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
                //Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();

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
                //Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
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
                //Toast.makeText(this, "Photo Taken", Toast.LENGTH_SHORT).show();
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
}
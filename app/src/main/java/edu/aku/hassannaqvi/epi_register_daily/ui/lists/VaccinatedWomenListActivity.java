package edu.aku.hassannaqvi.epi_register_daily.ui.lists;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVA;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesData;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesDataList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONException;

import edu.aku.hassannaqvi.epi_register_daily.MainActivity;
import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.adapters.VaccinatedMembersFollowupsAdapter;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityVaccinatedListWomenBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVA;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.MemberInfoActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVBActivity;


public class VaccinatedWomenListActivity extends AppCompatActivity {


    private static final String TAG = "VaccinationActivity";
    ActivityVaccinatedListWomenBinding bi;
    DatabaseHelper db;
    ActivityResultLauncher<Intent> MemberInfoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && !MainApp.superuser) {
                        // There are no request codes
                        //Intent data = result.getData();
                        Intent data = result.getData();

                        vaccinesDataList.add(vaccinesData);

                    }

                    if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Toast.makeText(VaccinatedWomenListActivity.this, "No member added.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    private VaccinatedMembersFollowupsAdapter vaccinatedMembersAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_mwra);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_vaccinated_list_women);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        vaccinesDataList = db.getAllFollowupWomen();

        initSearchFilter();

        vaccinatedMembersAdapter = new VaccinatedMembersFollowupsAdapter(this, vaccinesDataList, member -> {
            try {
                vaccinesData = db.getFollowupSelectedMembers(member.getUID());
                Toast.makeText(VaccinatedWomenListActivity.this,
                        "Selected Member\n Line No: "
                                + member.getVBO2() + "\nName: "
                                + member.getVB04A(),
                        Toast.LENGTH_LONG).show();
                VaccinatedWomenListActivity.this.startActivity(new Intent(VaccinatedWomenListActivity.this, SectionVBActivity.class).putExtra("group", false));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        bi.rvMember.setAdapter(vaccinatedMembersAdapter);

        vaccinatedMembersAdapter.notifyDataSetChanged();
        bi.rvMember.setLayoutManager(new LinearLayoutManager(this));

        bi.fab.setOnClickListener(view -> {
            addMoreMember();
        });

        bi.searchBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(bi.searchByName.isChecked()) {
                    bi.memberId.setHint("Card Number");
                }else{
                    bi.memberId.setHint("Name");
                }
            }
        });
    }

/*    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Activity Resumed!", Toast.LENGTH_SHORT).show();

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
        Toast.makeText(this, "Activity Resumed!", Toast.LENGTH_SHORT).show();
        //MainApp.formVB.setUuid(MainApp.formVA.getUid());
        formVA = new FormVA();
        if (MainApp.formVA.getUid().equals("")) {
            try {
                MainApp.formVA = db.getFormByuid(MainApp.formVA.getUid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void addMoreMember() {
        Intent intent = new Intent(this, MemberInfoActivity.class).putExtra("group", false);
        finish();
        MemberInfoLauncher.launch(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterForms(View view) {

        if (bi.searchByName.isChecked()) {
            Toast.makeText(this, "Searched", Toast.LENGTH_SHORT).show();

            vaccinesDataList = db.getFollowupWomensByName(bi.memberId.getText().toString());
            vaccinatedMembersAdapter = new VaccinatedMembersFollowupsAdapter(this, vaccinesDataList, member -> {

                try {
                    vaccinesData = db.getFollowupSelectedMembers(member.getUID());
                    Toast.makeText(VaccinatedWomenListActivity.this,
                            "Selected Member\n Line No: "
                                    + member.getVBO2() + "\nName: "
                                    + member.getVB04A(),
                            Toast.LENGTH_LONG).show();
                    VaccinatedWomenListActivity.this.startActivity(new Intent(VaccinatedWomenListActivity.this, SectionVBActivity.class).putExtra("group", false));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            vaccinatedMembersAdapter.notifyDataSetChanged();
            bi.rvMember.setAdapter(vaccinatedMembersAdapter);
        } else {
            Toast.makeText(this, "Searched", Toast.LENGTH_SHORT).show();

            vaccinesDataList= db.getFollowupWomenByCardNo(bi.memberId.getText().toString());
            vaccinatedMembersAdapter = new VaccinatedMembersFollowupsAdapter(this, vaccinesDataList, member -> {

                try {
                    vaccinesData = db.getFollowupSelectedMembers(member.getUID());
                    Toast.makeText(VaccinatedWomenListActivity.this,
                            "Selected Member\n Line No: "
                                    + member.getVBO2() + "\nName: "
                                    + member.getVB04A(),
                            Toast.LENGTH_LONG).show();
                    VaccinatedWomenListActivity.this.startActivity(new Intent(VaccinatedWomenListActivity.this, SectionVBActivity.class).putExtra("group", false));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            vaccinatedMembersAdapter.notifyDataSetChanged();
            bi.rvMember.setAdapter(vaccinatedMembersAdapter);
        }

    }

    public void btnEnd(View view) {

        finish();
    }

    @Override
    public void onBackPressed() {
        // Toast.makeText(getApplicationContext(), "Back Press Not Allowed", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    // Search Filter
    private void initSearchFilter() {
        bi.memberId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                vaccinatedMembersAdapter.filter(s.toString());
            }
        });

        bi.memberId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                vaccinatedMembersAdapter.filter(v.getText().toString());
                return true;
            }
        });
    }
}
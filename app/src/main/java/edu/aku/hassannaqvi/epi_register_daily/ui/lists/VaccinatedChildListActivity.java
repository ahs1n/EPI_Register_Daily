
package edu.aku.hassannaqvi.epi_register_daily.ui.lists;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVA;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVBList;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesData;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesDataList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import edu.aku.hassannaqvi.epi_register_daily.adapters.VaccinatedMembersAdapter;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityVaccinatedListChildBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVA;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.MemberInfoActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVBActivity;


public class VaccinatedChildListActivity extends AppCompatActivity {


    private static final String TAG = "VaccinationActivity";
    ActivityVaccinatedListChildBinding bi;
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

                        vaccinesDataList.add(MainApp.vaccinesData);

                    }

                    if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Toast.makeText(VaccinatedChildListActivity.this, "No family member added.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    private VaccinatedMembersFollowupsAdapter vaccinatedMembersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_mwra);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_vaccinated_list_child);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        vaccinesDataList = db.getAllFollowupChilds();


        vaccinatedMembersAdapter = new VaccinatedMembersFollowupsAdapter(this, vaccinesDataList, member -> {
            try {
                vaccinesData = db.getFollowupSelectedMembers(member.getUID());
                Toast.makeText(VaccinatedChildListActivity.this,
                        "Selected Member\n Line No: "
                                + member.getVBO2() + "\nName: "
                                + member.getVB04A(),
                        Toast.LENGTH_LONG).show();
                VaccinatedChildListActivity.this.startActivity(new Intent(VaccinatedChildListActivity.this, SectionVBActivity.class).putExtra("b", false));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        bi.rvMember.setAdapter(vaccinatedMembersAdapter);

        vaccinatedMembersAdapter.notifyDataSetChanged();
        bi.rvMember.setLayoutManager(new LinearLayoutManager(this));

        bi.fab.setOnClickListener(view -> {
//            MainApp.formVB = new FormVB();
            addMoreMember();
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
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
//        MainApp.formVB = new FormVB();
        Intent intent = new Intent(this, MemberInfoActivity.class);
        finish();
        MemberInfoLauncher.launch(intent);
    }

    public void filterForms(View view) {

        if (bi.searchByName.isChecked()) {
            Toast.makeText(this, "Searched", Toast.LENGTH_SHORT).show();

            vaccinesDataList = db.getFollowupChildsByName(bi.memberId.getText().toString());
            vaccinatedMembersAdapter = new VaccinatedMembersFollowupsAdapter(this, vaccinesDataList, member -> {

                try {
                    vaccinesData = db.getFollowupSelectedMembers(member.getUID());
                    Toast.makeText(VaccinatedChildListActivity.this,
                            "Selected Member\n Line No: "
                                    + member.getVBO2() + "\nName: "
                                    + member.getVB04A(),
                            Toast.LENGTH_LONG).show();
                    VaccinatedChildListActivity.this.startActivity(new Intent(VaccinatedChildListActivity.this, SectionVBActivity.class).putExtra("b", false));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            vaccinatedMembersAdapter.notifyDataSetChanged();
            bi.rvMember.setAdapter(vaccinatedMembersAdapter);
        } else {
            Toast.makeText(this, "Searched", Toast.LENGTH_SHORT).show();

            vaccinesDataList = db.getFollowupChildsByCardNo(bi.memberId.getText().toString());
            vaccinatedMembersAdapter = new VaccinatedMembersFollowupsAdapter(this, vaccinesDataList, member -> {

                try {
                    formVB = db.getSelectedMembers(member.getUID());
                    Toast.makeText(VaccinatedChildListActivity.this,
                            "Selected Member\n Line No: "
                                    + member.getVBO2() + "\nName: "
                                    + member.getVB04A(),
                            Toast.LENGTH_LONG).show();
                    VaccinatedChildListActivity.this.startActivity(new Intent(VaccinatedChildListActivity.this, SectionVBActivity.class).putExtra("b", false));
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
}

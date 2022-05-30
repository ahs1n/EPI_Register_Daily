package edu.aku.hassannaqvi.epi_register_daily.ui.lists;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVBList;

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

import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.adapters.VaccinatedMembersAdapter;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityVaccinatedListBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVB;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVBActivity;


public class RegisteredMembersListActivity extends AppCompatActivity {


    private static final String TAG = "VaccinationActivity";
    ActivityVaccinatedListBinding bi;
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

                        formVBList.add(MainApp.formVB);

                    }

                    if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Toast.makeText(RegisteredMembersListActivity.this, "No family member added.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    private VaccinatedMembersAdapter vaccinatedMembersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_mwra);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_vaccinated_list);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        formVBList = db.getAllMembers();
        //MainApp.formVBList = new ArrayList<>();

/*        Log.d(TAG, "onCreate: formVBList " + MainApp.formVBList.size());
        try {
            MainApp.formVBList = db.getAllMembers(MainApp.formVA.getUid());


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "JSONException(FamilyMembers): " + e.getMessage(), Toast.LENGTH_LONG).show();
        }*/
        MainApp.selectedChild = "";

        vaccinatedMembersAdapter = new VaccinatedMembersAdapter(this, formVBList);
        bi.rvMember.setAdapter(vaccinatedMembersAdapter);


        bi.rvMember.setLayoutManager(new LinearLayoutManager(this));

        bi.fab.setOnClickListener(view -> {
            MainApp.formVB = new FormVB();
            addFemale();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Activity Resumed!", Toast.LENGTH_SHORT).show();

    }

    public void addFemale() {
        addMoreMember();
    }

    private void addMoreMember() {
        MainApp.formVB = new FormVB();
        Intent intent = new Intent(this, SectionVBActivity.class);
        finish();
        MemberInfoLauncher.launch(intent);
    }

    public void filterForms(View view) {
        Toast.makeText(this, "Searched", Toast.LENGTH_SHORT).show();

        formVBList = db.getAllMembersByCardNo(bi.memberId.getText().toString());
        vaccinatedMembersAdapter = new VaccinatedMembersAdapter(this, formVBList);
        vaccinatedMembersAdapter.notifyDataSetChanged();
        bi.rvMember.setAdapter(vaccinatedMembersAdapter);

    }

    public void btnEnd(View view) {

        finish();
    }
}
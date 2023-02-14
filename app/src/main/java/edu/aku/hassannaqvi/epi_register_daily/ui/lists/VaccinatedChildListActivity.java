
package edu.aku.hassannaqvi.epi_register_daily.ui.lists;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.memberCount;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesData;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesDataList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import edu.aku.hassannaqvi.epi_register_daily.adapters.GenericRVAdapter;
import edu.aku.hassannaqvi.epi_register_daily.adapters.VaccinatedMembersFollowupsAdapter;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityVaccinatedListChildBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.VaccinesData;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.MemberInfoActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVBActivity;


public class VaccinatedChildListActivity extends AppCompatActivity {

    private final int VACC_CHILD_RV = 102;


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
    GenericRVAdapter.IRVOnItemClickListener iRVOnItemClickListener = (recyclerView, obj, index) -> {
        int recyclerViewTag = (int) recyclerView.getTag();
        if (recyclerViewTag == VACC_CHILD_RV) {
            VaccinesData childVacc = (VaccinesData) obj;
            try {
                vaccinesData = db.getFollowupSelectedMembers(childVacc.getUID(), childVacc.getVBO2());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, SectionVBActivity.class).putExtra("woman", false));
        }
    };

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_mwra);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_vaccinated_list_child);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        vaccinesDataList = db.getAllFollowupChilds();

        initVacChildRV();

//        initSearchFilter();

        /*vaccinatedMembersAdapter = new VaccinatedMembersFollowupsAdapter(this, vaccinesDataList, member -> {
            try {
                vaccinesData = db.getFollowupSelectedMembers(member.getUID());
                Toast.makeText(VaccinatedChildListActivity.this,
                        "Selected Member\n Line No: "
                                + member.getVBO2() + "\nName: "
                                + member.getVB04A(),
                        Toast.LENGTH_LONG).show();
                VaccinatedChildListActivity.this.startActivity(new Intent(VaccinatedChildListActivity.this, SectionVBActivity.class).putExtra("group", false));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        bi.rvMember.setAdapter(vaccinatedMembersAdapter);

        vaccinatedMembersAdapter.notifyDataSetChanged();*/
        bi.rvMember.setLayoutManager(new LinearLayoutManager(this));

        bi.fab.setOnClickListener(view -> {
//            MainApp.formVB = new FormVB();
            addMoreMember();
        });


        bi.searchBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (bi.searchByName.isChecked()) {
                    bi.memberId.setHint("Name");
                } else {
                    bi.memberId.setHint("Card No.");
                }
            }
        });

    }

    private void initVacChildRV() {
        GenericRVAdapter<VaccinesData> genericRVAdapter = new GenericRVAdapter<VaccinesData>(this,
                vaccinesDataList, bi.rvMember, iRVOnItemClickListener, false) {

            @Override
            protected View createView(Activity activity, ViewGroup viewGroup, int viewType) {
                return LayoutInflater.from(activity)
                        .inflate(R.layout.vaccinated_member_row, viewGroup, false);
            }

            @Override
            protected void bindView(VaccinesData item, GenericRVAdapter.ViewHolder viewHolder, int position, boolean isMultiSelect) {
                if (item != null) {
                    viewHolder.itemView.setTag(position);
                    TextView mName = (TextView) viewHolder.getView(R.id.mName);
                    mName.setText(item.getVB04A());
                    mName.setTag(position);
                    TextView ageY = (TextView) viewHolder.getView(R.id.ageY);
                    ageY.setText(String.format("%s DOB", item.getDob()));
                    TextView fName = (TextView) viewHolder.getView(R.id.fName);
                    fName.setText(item.getVB04());
                    TextView cardNo = (TextView) viewHolder.getView(R.id.cardNo);
                    cardNo.setText(item.getVBO2());
                    ImageView iv = (ImageView) viewHolder.getView(R.id.mainIcon);
                    iv.setImageResource(item.getVBO5A().equals("1") ?
                            R.drawable.malebabyicon : R.drawable.femalebabyicon);

                }
            }
        };


        bi.rvMember.setTag(VACC_CHILD_RV);
        bi.rvMember.setAdapter(genericRVAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainApp.lockScreen(this);
        Toast.makeText(this, "Activity Resumed!", Toast.LENGTH_SHORT).show();
        //MainApp.formVB.setUuid(MainApp.formVA.getUid());
//        formVA = new FormVA();
        if (MainApp.formVA.getUid().equals("") || MainApp.formVA.getUid() == null) {
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

    /*@SuppressLint("NotifyDataSetChanged")
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
                    VaccinatedChildListActivity.this.startActivity(new Intent(VaccinatedChildListActivity.this, SectionVBActivity.class).putExtra("group", false));
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
                    vaccinesData = db.getFollowupSelectedMembers(member.getUID());
                    Toast.makeText(VaccinatedChildListActivity.this,
                            "Selected Member\n Line No: "
                                    + member.getVBO2() + "\nName: "
                                    + member.getVB04A(),
                            Toast.LENGTH_LONG).show();
                    VaccinatedChildListActivity.this.startActivity(new Intent(VaccinatedChildListActivity.this, SectionVBActivity.class).putExtra("group", false));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
            vaccinatedMembersAdapter.notifyDataSetChanged();
            bi.rvMember.setAdapter(vaccinatedMembersAdapter);
        }

    }*/

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

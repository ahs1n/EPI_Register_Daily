package edu.aku.hassannaqvi.epi_register_daily.ui.lists;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVA;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.vaccinesData;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.womenFollowUP;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.womenFollowUPList;

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
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityVaccinatedListWomenBinding;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVA;
import edu.aku.hassannaqvi.epi_register_daily.models.WomenFollowUP;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.MemberInfoActivity;
import edu.aku.hassannaqvi.epi_register_daily.ui.sections.SectionVBActivity;


public class VaccinatedWomenListActivity extends AppCompatActivity {

    private final int VACC_WOMEN_RV = 101;
    private GenericRVAdapter<WomenFollowUP> genericRVAdapter;


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

                        womenFollowUPList.add(womenFollowUP);

                    }

                    if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Toast.makeText(VaccinatedWomenListActivity.this, "No member added.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    //    private VaccinatedMembersFollowupsAdapter vaccinatedMembersAdapter;
    GenericRVAdapter.IRVOnItemClickListener iRVOnItemClickListener = (recyclerView, obj, index) -> {
        int recyclerViewTag = (int) recyclerView.getTag();
        if (recyclerViewTag == VACC_WOMEN_RV) {
            WomenFollowUP womenFollowUP = (WomenFollowUP) obj;
            try {
                MainApp.womenFollowUP = db.getFollowupSelectedWoman(womenFollowUP.getUID(), womenFollowUP.getVBO2());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(this, SectionVBActivity.class).putExtra("woman", true));
        }
    };

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_mwra);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_vaccinated_list_women);
        bi.setCallback(this);
        db = MainApp.appInfo.dbHelper;
        womenFollowUPList = db.getAllFollowupWomen();

        initVacWomenRV();
        initSearchFilter();

        bi.rvMember.setLayoutManager(new LinearLayoutManager(this));

        bi.fab.setOnClickListener(view -> {
            addMoreMember();
        });

        /*bi.searchBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (bi.searchByName.isChecked()) {
                    bi.memberId.setHint("Card Number");
                } else {
                    bi.memberId.setHint("Name");
                }
            }
        });*/
    }

    private void initVacWomenRV() {
        genericRVAdapter = new GenericRVAdapter<WomenFollowUP>(this,
                womenFollowUPList, bi.rvMember, iRVOnItemClickListener, false) {
            @Override
            protected View createView(Activity activity, ViewGroup viewGroup, int viewType) {
                return LayoutInflater.from(activity)
                        .inflate(R.layout.vaccinated_member_row, viewGroup, false);
            }

            @Override
            protected void bindView(WomenFollowUP item, ViewHolder viewHolder, int position, boolean isMultiSelect) {
                if (item != null) {
                    viewHolder.itemView.setTag(position);
                    TextView mName = (TextView) viewHolder.getView(R.id.mName);
                    mName.setText(item.getVB04A());
                    mName.setTag(position);
                    TextView ageY = (TextView) viewHolder.getView(R.id.ageY);
                    ageY.setText(String.format("%s Y", item.getAge()));
                    TextView fName = (TextView) viewHolder.getView(R.id.fName);
                    fName.setText(item.getVB04());
                    TextView cardNo = (TextView) viewHolder.getView(R.id.cardNo);
                    cardNo.setText(item.getVBO2());
                    ImageView iv = (ImageView) viewHolder.getView(R.id.mainIcon);
                    iv.setImageResource(R.drawable.mwraicon);
                }
            }
        };
        bi.rvMember.setTag(VACC_WOMEN_RV);
        bi.rvMember.setAdapter(genericRVAdapter);
    }


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
                genericRVAdapter.filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bi.memberId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return true;
            }
        });
    }
}
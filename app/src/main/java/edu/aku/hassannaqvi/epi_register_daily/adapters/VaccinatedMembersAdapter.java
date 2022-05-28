package edu.aku.hassannaqvi.epi_register_daily.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.aku.hassannaqvi.epi_register_daily.R;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVB;


public class VaccinatedMembersAdapter extends RecyclerView.Adapter<VaccinatedMembersAdapter.ViewHolder> {
    private static final String TAG = "VaccinatedMembersAdapter";
    private final Context mContext;
    private final List<FormVB> member;
    private final int completeCount;
    private final boolean motherPresent = false;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param members List<FemaleMembersModel> containing the data to populate views to be used by RecyclerView.
     */
    public VaccinatedMembersAdapter(Context mContext, List<FormVB> members) {
        this.member = members;
        this.mContext = mContext;
        completeCount = 0;

    }


    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        FormVB members = this.member.get(position);        // Get element from your dataset at this position and replace the contents of the view
        // with that element

        TextView fName = viewHolder.fName;
        TextView fAgeY = viewHolder.mAgeY;
        TextView fatherName = viewHolder.fatherName;
        ImageView fmRow = viewHolder.fmRow;
        ImageView mainIcon = viewHolder.mainIcon;
        View cloaked = viewHolder.cloak;
        View indexedBar = viewHolder.indexedBar;

        fName.setText(members.getVb04a());
        if (members.getVb03().equals("1")) {
            fAgeY.setText(members.getVb05y() + " Y ");
        } else fAgeY.setText(members.getVb05y() + " Y " + members.getVb05m() + " M ");
        fatherName.setText(members.getVb04());


        mainIcon.setImageResource(members.getVb03().equals("2") ? (members.getVb05a().equals("1") ? R.drawable.malebabyicon : R.drawable.femalebabyicon) : R.drawable.mwraicon);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.vaccinated_member_row, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return member.size();
    }

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView fName;
        private final TextView mAgeY;
        private final TextView fatherName;
        //private final TextView addSec;
        //private final LinearLayout subItem;
        private final ImageView fmRow;
        private final ImageView mainIcon;
        private final View cloak;
        private final View indexedBar;


        public ViewHolder(View v) {
            super(v);
            fName = v.findViewById(R.id.chh02);
            mAgeY = v.findViewById(R.id.ageY);
            fatherName = v.findViewById(R.id.vb04);
            fmRow = v.findViewById(R.id.cfmRow);
            mainIcon = v.findViewById(R.id.mainIcon);
            cloak = v.findViewById(R.id.cloaked);
            indexedBar = v.findViewById(R.id.indexedBar);

        }

        public TextView getTextView() {
            return fName;
        }
    }


}

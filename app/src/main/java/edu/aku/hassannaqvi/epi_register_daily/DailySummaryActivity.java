package edu.aku.hassannaqvi.epi_register_daily;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.Collections;
import java.util.List;

import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.database.DatabaseHelper;
import edu.aku.hassannaqvi.epi_register_daily.databinding.ActivityDailySummaryBinding;

public class DailySummaryActivity extends AppCompatActivity {

    ActivityDailySummaryBinding bi;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_daily_summary);

        db = MainApp.appInfo.dbHelper;

        initSummaryUI();
    }

    // Initialize summary UI
    private void initSummaryUI() {
        // BCG
        List<String> bcg = db.getTodaysVaccinesByAntigen("BCG");
        bi.bcgAnt.setText(String.valueOf(bcg.size()));

        // OPV
        List<String> opv = db.getTodaysVaccinesByAntigen("OPV");
        bi.opvAnt0.setText(String.valueOf(Collections.frequency(opv, "OPV1")));
        bi.opvAnt1.setText(String.valueOf(Collections.frequency(opv, "OPV2")));
        bi.opvAnt2.setText(String.valueOf(Collections.frequency(opv, "OPV3")));
        bi.opvAnt3.setText(String.valueOf(Collections.frequency(opv, "OPV4")));

        // HepB
        List<String> hepB = db.getTodaysVaccinesByAntigen("HepB");
        bi.hepBAnt.setText(String.valueOf(hepB.size()));

        // Penta
        List<String> penta = db.getTodaysVaccinesByAntigen("Penta");
        bi.pentaAnt1.setText(String.valueOf(Collections.frequency(penta, "Penta1")));
        bi.pentaAnt2.setText(String.valueOf(Collections.frequency(penta, "Penta2")));
        bi.pentaAnt3.setText(String.valueOf(Collections.frequency(penta, "Penta3")));

        // PCV
        List<String> pcv = db.getTodaysVaccinesByAntigen("PCV");
        bi.pcvAnt1.setText(String.valueOf(Collections.frequency(pcv, "PCV1")));
        bi.pcvAnt2.setText(String.valueOf(Collections.frequency(pcv, "PCV2")));
        bi.pcvAnt3.setText(String.valueOf(Collections.frequency(pcv, "PCV3")));

        // Rota
        List<String> rota = db.getTodaysVaccinesByAntigen("Rota");
        bi.rotaAnt1.setText(String.valueOf(Collections.frequency(rota, "Rota1")));
        bi.rotaAnt2.setText(String.valueOf(Collections.frequency(rota, "Rota2")));

        // IPV
        List<String> ipv = db.getTodaysVaccinesByAntigen("IPV");
        bi.ipvAnt1.setText(String.valueOf(Collections.frequency(ipv, "IPV1")));
        bi.ipvAnt2.setText(String.valueOf(Collections.frequency(ipv, "IPV2")));

        // Measles
        List<String> measles = db.getTodaysVaccinesByAntigen("Measles");
        bi.measlesAnt1.setText(String.valueOf(Collections.frequency(measles, "Measles1")));
        bi.measlesAnt2.setText(String.valueOf(Collections.frequency(measles, "Measles2")));

        // Typhoid
        List<String> typhoid = db.getTodaysVaccinesByAntigen("Typhoid");
        bi.typhoidAnt.setText(String.valueOf(typhoid.size()));

    }


}
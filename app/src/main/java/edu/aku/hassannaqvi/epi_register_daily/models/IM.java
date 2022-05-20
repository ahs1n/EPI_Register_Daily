package edu.aku.hassannaqvi.epi_register_daily.models;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.PROJECT_NAME;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp._EMPTY_;

import android.database.Cursor;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.epi_register_daily.BR;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;


public class IM extends BaseObservable implements Observable {

    private final String TAG = "FormVB";
    private final transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    // APP VARIABLES
    private String projectName = PROJECT_NAME;
    // APP VARIABLES
    private String id = _EMPTY_;
    private String uid = _EMPTY_;
    private String userName = _EMPTY_;
    private String sysDate = _EMPTY_;
    private String sno = _EMPTY_;
    private String deviceId = _EMPTY_;
    private String deviceTag = _EMPTY_;
    private String appver = _EMPTY_;
    private String endTime = _EMPTY_;
    private String iStatus = _EMPTY_;
    private String iStatus96x = _EMPTY_;
    private String synced = _EMPTY_;
    private String syncDate = _EMPTY_;
    private String entryType = _EMPTY_;

    // FIELD VARIABLES
    private String vb08c = _EMPTY_;
    private String vb08ca = _EMPTY_;
    private String vb08cb = _EMPTY_;
    private String vb08cc = _EMPTY_;
    private String vb08cd = _EMPTY_;
    private String vb08ce = _EMPTY_;
    private String vb08cf = _EMPTY_;
    private String vb08cg = _EMPTY_;
    private String vb08ch = _EMPTY_;
    private String vb08ci = _EMPTY_;
    private String vb08cj = _EMPTY_;
    private String vb08ck = _EMPTY_;
    private String vb08cl = _EMPTY_;
    private String vb08cm = _EMPTY_;
    private String vb08cn = _EMPTY_;
    private String vb08co = _EMPTY_;
    private String vb08w = _EMPTY_;
    private String vb08wa = _EMPTY_;
    private String vb08wb = _EMPTY_;
    private String vb08wc = _EMPTY_;
    private String vb08wd = _EMPTY_;
    private String vb08we = _EMPTY_;


    public IM() {

/*        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setUserName(MainApp.user.getUserName());
        setDeviceId(MainApp.deviceid);
        setAppver(MainApp.appInfo.getAppVersion());
        setAppver(MainApp.appInfo.getAppVersion());*/

    }


    public void populateMeta() {

        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setUserName(MainApp.user.getUserName());
        setDeviceId(MainApp.deviceid);
        //   setUuid(MainApp.form.getUid());  // not applicable in Form table
        setAppver(MainApp.appInfo.getAppVersion());
        setProjectName(PROJECT_NAME);
        // setEntryType(String.valueOf(MainApp.entryType));
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Bindable
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
        notifyPropertyChanged(BR.sno);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceTag() {
        return deviceTag;
    }

    public void setDeviceTag(String deviceTag) {
        this.deviceTag = deviceTag;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getiStatus() {
        return iStatus;
    }

    public void setiStatus(String iStatus) {
        this.iStatus = iStatus;
    }

    public String getiStatus96x() {
        return iStatus96x;
    }

    public void setiStatus96x(String iStatus96x) {
        this.iStatus96x = iStatus96x;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

    @Bindable
    public String getVb08c() {
        return vb08c;
    }

    public void setVb08c(String vb08c) {
        this.vb08c = vb08c;
        notifyPropertyChanged(BR.vb08c);
    }

    @Bindable
    public String getVb08ca() {
        return vb08ca;
    }

    public void setVb08ca(String vb08ca) {
        this.vb08ca = vb08ca;
        notifyPropertyChanged(BR.vb08ca);
    }

    @Bindable
    public String getVb08cb() {
        return vb08cb;
    }

    public void setVb08cb(String vb08cb) {
        this.vb08cb = vb08cb;
        notifyPropertyChanged(BR.vb08cb);
    }

    @Bindable
    public String getVb08cc() {
        return vb08cc;
    }

    public void setVb08cc(String vb08cc) {
        this.vb08cc = vb08cc;
        notifyPropertyChanged(BR.vb08cc);
    }

    @Bindable
    public String getVb08cd() {
        return vb08cd;
    }

    public void setVb08cd(String vb08cd) {
        this.vb08cd = vb08cd;
        notifyPropertyChanged(BR.vb08cd);
    }

    @Bindable
    public String getVb08ce() {
        return vb08ce;
    }

    public void setVb08ce(String vb08ce) {
        this.vb08ce = vb08ce;
        notifyPropertyChanged(BR.vb08ce);
    }

    @Bindable
    public String getVb08cf() {
        return vb08cf;
    }

    public void setVb08cf(String vb08cf) {
        this.vb08cf = vb08cf;
        notifyPropertyChanged(BR.vb08cf);
    }

    @Bindable
    public String getVb08cg() {
        return vb08cg;
    }

    public void setVb08cg(String vb08cg) {
        this.vb08cg = vb08cg;
        notifyPropertyChanged(BR.vb08cg);
    }

    @Bindable
    public String getVb08ch() {
        return vb08ch;
    }

    public void setVb08ch(String vb08ch) {
        this.vb08ch = vb08ch;
        notifyPropertyChanged(BR.vb08ch);
    }

    @Bindable
    public String getVb08ci() {
        return vb08ci;
    }

    public void setVb08ci(String vb08ci) {
        this.vb08ci = vb08ci;
        notifyPropertyChanged(BR.vb08ci);
    }

    @Bindable
    public String getVb08cj() {
        return vb08cj;
    }

    public void setVb08cj(String vb08cj) {
        this.vb08cj = vb08cj;
        notifyPropertyChanged(BR.vb08cj);
    }

    @Bindable
    public String getVb08ck() {
        return vb08ck;
    }

    public void setVb08ck(String vb08ck) {
        this.vb08ck = vb08ck;
        notifyPropertyChanged(BR.vb08ck);
    }

    @Bindable
    public String getVb08cl() {
        return vb08cl;
    }

    public void setVb08cl(String vb08cl) {
        this.vb08cl = vb08cl;
        notifyPropertyChanged(BR.vb08cl);
    }

    @Bindable
    public String getVb08cm() {
        return vb08cm;
    }

    public void setVb08cm(String vb08cm) {
        this.vb08cm = vb08cm;
        notifyPropertyChanged(BR.vb08cm);
    }

    @Bindable
    public String getVb08cn() {
        return vb08cn;
    }

    public void setVb08cn(String vb08cn) {
        this.vb08cn = vb08cn;
        notifyPropertyChanged(BR.vb08cn);
    }

    @Bindable
    public String getVb08co() {
        return vb08co;
    }

    public void setVb08co(String vb08co) {
        this.vb08co = vb08co;
        notifyPropertyChanged(BR.vb08co);
    }

    @Bindable
    public String getVb08w() {
        return vb08w;
    }

    public void setVb08w(String vb08w) {
        this.vb08w = vb08w;
        notifyPropertyChanged(BR.vb08w);
    }

    @Bindable
    public String getVb08wa() {
        return vb08wa;
    }

    public void setVb08wa(String vb08wa) {
        if (this.vb08wa.equals(vb08wa)) return; // for all checkboxes
        this.vb08wa = vb08wa;
        notifyPropertyChanged(BR.vb08wa);
    }

    @Bindable
    public String getVb08wb() {
        return vb08wb;
    }

    public void setVb08wb(String vb08wb) {
        if (this.vb08wb.equals(vb08wb)) return; // for all checkboxes
        this.vb08wb = vb08wb;
        notifyPropertyChanged(BR.vb08wb);
    }

    @Bindable
    public String getVb08wc() {
        return vb08wc;
    }

    public void setVb08wc(String vb08wc) {
        if (this.vb08wc.equals(vb08wc)) return; // for all checkboxes
        this.vb08wc = vb08wc;
        notifyPropertyChanged(BR.vb08wc);
    }

    @Bindable
    public String getVb08wd() {
        return vb08wd;
    }

    public void setVb08wd(String vb08wd) {
        if (this.vb08wd.equals(vb08wd)) return; // for all checkboxes
        this.vb08wd = vb08wd;
        notifyPropertyChanged(BR.vb08wd);
    }

    @Bindable
    public String getVb08we() {
        return vb08we;
    }

    public void setVb08we(String vb08we) {
        if (this.vb08we.equals(vb08we)) return; // for all checkboxes
        this.vb08we = vb08we;
        notifyPropertyChanged(BR.vb08we);
    }


    public IM Hydrate(Cursor cursor) throws JSONException {
        this.id = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_UID));
        this.projectName = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_PROJECT_NAME));
        this.sno = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SNO));
        this.userName = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_USERNAME));
        this.sysDate = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SYSDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_DEVICEID));
        this.deviceTag = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_DEVICETAGID));
        //   this.entryType = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_ENTRY_TYPE));
        this.appver = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_APPVERSION));
        this.iStatus = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_ISTATUS));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SYNCED));
        this.syncDate = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SYNC_DATE));

        vBHydrate(cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_VB)));
        return this;
    }

    public void vBHydrate(String string) throws JSONException {
        Log.d(TAG, "vBHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.vb08ca = json.getString("vb08ca");
            this.vb08cb = json.getString("vb08cb");
            this.vb08cc = json.getString("vb08cc");
            this.vb08cd = json.getString("vb08cd");
            this.vb08ce = json.getString("vb08ce");
            this.vb08cf = json.getString("vb08cf");
            this.vb08cg = json.getString("vb08cg");
            this.vb08ch = json.getString("vb08ch");
            this.vb08ci = json.getString("vb08ci");
            this.vb08cj = json.getString("vb08cj");
            this.vb08ck = json.getString("vb08ck");
            this.vb08cl = json.getString("vb08cl");
            this.vb08cm = json.getString("vb08cm");
            this.vb08cn = json.getString("vb08cn");
            this.vb08co = json.getString("vb08co");
            this.vb08wa = json.getString("vb08wa");
            this.vb08wb = json.getString("vb08wb");
            this.vb08wc = json.getString("vb08wc");
            this.vb08wd = json.getString("vb08wd");
            this.vb08we = json.getString("vb08we");

        }
    }


    public String vBtoString() throws JSONException {
        Log.d(TAG, "vBtoString: ");
        JSONObject json = new JSONObject();
        json.put("vb08ca", vb08ca)
                .put("vb08cb", vb08cb)
                .put("vb08cc", vb08cc)
                .put("vb08cd", vb08cd)
                .put("vb08ce", vb08ce)
                .put("vb08cf", vb08cf)
                .put("vb08cg", vb08cg)
                .put("vb08ch", vb08ch)
                .put("vb08ci", vb08ci)
                .put("vb08cj", vb08cj)
                .put("vb08ck", vb08ck)
                .put("vb08cl", vb08cl)
                .put("vb08cm", vb08cm)
                .put("vb08cn", vb08cn)
                .put("vb08co", vb08co)
                .put("vb08wa", vb08wa)
                .put("vb08wb", vb08wb)
                .put("vb08wc", vb08wc)
                .put("vb08wd", vb08wd)
                .put("vb08we", vb08we);
        return json.toString();
    }


    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(FormsVBTable.COLUMN_ID, this.id);
        json.put(FormsVBTable.COLUMN_UID, this.uid);
        json.put(FormsVBTable.COLUMN_PROJECT_NAME, this.projectName);
        json.put(FormsVBTable.COLUMN_SNO, this.sno);
        json.put(FormsVBTable.COLUMN_USERNAME, this.userName);
        json.put(FormsVBTable.COLUMN_SYSDATE, this.sysDate);
        json.put(FormsVBTable.COLUMN_DEVICEID, this.deviceId);
        json.put(FormsVBTable.COLUMN_DEVICETAGID, this.deviceTag);
        //    json.put(FormsVBTable.COLUMN_ENTRY_TYPE, this.entryType);
        json.put(FormsVBTable.COLUMN_ISTATUS, this.iStatus);
        json.put(FormsVBTable.COLUMN_SYNCED, this.synced);
        json.put(FormsVBTable.COLUMN_SYNC_DATE, this.syncDate);
        json.put(FormsVBTable.COLUMN_APPVERSION, this.appver);
        json.put(FormsVBTable.COLUMN_VB, new JSONObject(vBtoString()));
        return json;
    }
}
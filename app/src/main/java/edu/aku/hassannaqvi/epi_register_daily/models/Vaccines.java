package edu.aku.hassannaqvi.epi_register_daily.models;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.PROJECT_NAME;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp._EMPTY_;

import android.database.Cursor;

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
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.VaccinesTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;


public class Vaccines extends BaseObservable implements Observable {

    private final String TAG = "Vaccines";
    private final transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    // APP VARIABLES
    private String projectName = PROJECT_NAME;
    // APP VARIABLES
    private String id = _EMPTY_;
    private String uid = _EMPTY_;
    private String uuid = _EMPTY_;
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
    private String vb02 = _EMPTY_;
    private String vb04a = _EMPTY_;
    private String vb04 = _EMPTY_;
    private String vb08CCode = _EMPTY_;
    private String vb08CAntigen = _EMPTY_;
    private String vb08CDate = _EMPTY_;
    private String vb08WCode = _EMPTY_;
    private String vb08WAntigen = _EMPTY_;
    private String vb08WDate = _EMPTY_;


    public void populateMeta() {

        setSysDate(MainApp.formVB.getSysDate());
        setUserName(MainApp.formVB.getUserName());
        setDeviceId(MainApp.formVB.getDeviceId());
        setAppver(MainApp.formVB.getAppver());
        setVb02(MainApp.formVB.getVb02());
        setVb04a(MainApp.formVB.getVb04a());
        setVb04(MainApp.formVB.getVb04());

        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
//        setUserName(MainApp.user.getUserName());
//        setDeviceId(MainApp.deviceid);
//        setUuid(MainApp.formVA.getUid());  // not applicable in Form table
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Bindable
    public String getVb02() {
        return vb02;
    }

    public void setVb02(String vb02) {
        this.vb02 = vb02;
        notifyPropertyChanged(BR.vb02);
    }

    @Bindable
    public String getVb04a() {
        return vb04a;
    }

    public void setVb04a(String vb04a) {
        this.vb04a = vb04a;
        notifyPropertyChanged(BR.vb04a);
    }

    @Bindable
    public String getVb04() {
        return vb04;
    }

    public void setVb04(String vb04) {
        this.vb04 = vb04;
        notifyPropertyChanged(BR.vb04);
    }

    @Bindable
    public String getVb08CCode() {
        return vb08CCode;
    }

    public void setVb08CCode(String vb08CCode) {
        this.vb08CCode = vb08CCode;
        notifyPropertyChanged(BR.vb08CCode);
    }

    @Bindable
    public String getVb08CAntigen() {
        return vb08CAntigen;
    }

    public void setVb08CAntigen(String vb08CAntigen) {
        this.vb08CAntigen = vb08CAntigen;
        notifyPropertyChanged(BR.vb08CAntigen);
    }

    @Bindable
    public String getVb08CDate() {
        return vb08CDate;
    }

    public void setVb08CDate(String vb08CDate) {
        this.vb08CDate = vb08CDate;
        notifyPropertyChanged(BR.vb08CDate);
    }

    @Bindable
    public String getVb08WCode() {
        return vb08WCode;
    }

    public void setVb08WCode(String vb08WCode) {
        this.vb08WCode = vb08WCode;
        notifyPropertyChanged(BR.vb08WCode);
    }

    @Bindable
    public String getVb08WAntigen() {
        return vb08WAntigen;
    }

    public void setVb08WAntigen(String vb08WAntigen) {
        this.vb08WAntigen = vb08WAntigen;
        notifyPropertyChanged(BR.vb08WAntigen);
    }

    @Bindable
    public String getVb08WDate() {
        return vb08WDate;
    }

    public void setVb08WDate(String vb08WDate) {
        this.vb08WDate = vb08WDate;
        notifyPropertyChanged(BR.vb08WDate);
    }


    public Vaccines Hydrate(Cursor cursor) throws JSONException {
        this.id = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_UID));
        this.uuid = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_UUID));
        this.projectName = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_PROJECT_NAME));
        this.sno = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_SNO));
        this.userName = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_USERNAME));
        this.sysDate = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_SYSDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_DEVICEID));
        this.deviceTag = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_DEVICETAGID));
        this.appver = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_APPVERSION));
        this.iStatus = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_ISTATUS));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_SYNCED));
        this.syncDate = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_SYNC_DATE));

        this.vb02 = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB02));
        this.vb04a = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB04A));
        this.vb04 = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB04));
        this.vb08CCode = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB08C_CODE));
        this.vb08CAntigen = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB08C_ANTIGEN));
        this.vb08CDate = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB08C_DATE));
        this.vb08WCode = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB08W_CODE));
        this.vb08WAntigen = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB08W_ANTIGEN));
        this.vb08WDate = cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VB08W_DATE));

//        sVACHydrate(cursor.getString(cursor.getColumnIndexOrThrow(VaccinesTable.COLUMN_VACCINE)));
        return this;
    }


/*    public void sVACHydrate(String string) throws JSONException {
        Log.d(TAG, "vACHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.vb02 = json.getString("vb02");
            this.vb04a = json.getString("vb04a");

        }
    }


    public String sVACtoString() throws JSONException {
        Log.d(TAG, "vBtoString: ");
        JSONObject json = new JSONObject();
        json.put("vb02", vb02)
                .put("vb04a", vb04a);
        return json.toString();
    }*/


    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(VaccinesTable.COLUMN_ID, this.id);
        json.put(VaccinesTable.COLUMN_UID, this.uid);
        json.put(VaccinesTable.COLUMN_UUID, this.uuid);
        json.put(VaccinesTable.COLUMN_PROJECT_NAME, this.projectName);
        json.put(VaccinesTable.COLUMN_SNO, this.sno);
        json.put(VaccinesTable.COLUMN_USERNAME, this.userName);
        json.put(VaccinesTable.COLUMN_SYSDATE, this.sysDate);
        json.put(VaccinesTable.COLUMN_DEVICEID, this.deviceId);
        json.put(VaccinesTable.COLUMN_DEVICETAGID, this.deviceTag);
        json.put(VaccinesTable.COLUMN_ISTATUS, this.iStatus);
        json.put(VaccinesTable.COLUMN_SYNCED, this.synced);
        json.put(VaccinesTable.COLUMN_SYNC_DATE, this.syncDate);
        json.put(VaccinesTable.COLUMN_APPVERSION, this.appver);

        json.put(VaccinesTable.COLUMN_VB02, this.vb02);
        json.put(VaccinesTable.COLUMN_VB04A, this.vb04a);
        json.put(VaccinesTable.COLUMN_VB04, this.vb04);
        json.put(VaccinesTable.COLUMN_VB08C_CODE, this.vb08CCode);
        json.put(VaccinesTable.COLUMN_VB08C_ANTIGEN, this.vb08CAntigen);
        json.put(VaccinesTable.COLUMN_VB08C_DATE, this.vb08CDate);
        json.put(VaccinesTable.COLUMN_VB08W_CODE, this.vb08WCode);
        json.put(VaccinesTable.COLUMN_VB08W_ANTIGEN, this.vb08WAntigen);
        json.put(VaccinesTable.COLUMN_VB08W_DATE, this.vb08WDate);

//        json.put(VaccinesTable.COLUMN_VACCINE, new JSONObject(sVACtoString()));
        return json;
    }

    public void updateAntigen(String vaccCode, String antigen, String vaccDate) {
        setVb08CCode(vaccCode);
        setVb08CAntigen(antigen);
        setVb08CDate(vaccDate);
        setVb08WCode(vaccCode);
        setVb08WAntigen(antigen);
        setVb08WDate(vaccDate);

    }
}
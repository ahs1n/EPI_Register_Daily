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
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.AttendanceTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;


public class Attendance extends BaseObservable implements Observable {

    private final String TAG = "FormVA";
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
    private String gpsLat = _EMPTY_;
    private String gpsLng = _EMPTY_;
    private String gpsDT = _EMPTY_;
    private String gpsAcc = _EMPTY_;

    // FIELD VARIABLES
    private String attenddt = _EMPTY_;
    private String attendat = _EMPTY_;
    private String attendatx = _EMPTY_;
    private String facility = _EMPTY_;


    public Attendance() {

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
    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
        notifyPropertyChanged(BR.gpsLat);
    }

    @Bindable
    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
        notifyPropertyChanged(BR.gpsLng);
    }

    @Bindable
    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
        notifyPropertyChanged(BR.gpsDT);
    }

    @Bindable
    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
        notifyPropertyChanged(BR.gpsAcc);
    }


    @Bindable
    public String getAttenddt() {
        return attenddt;
    }

    public void setAttenddt(String attenddt) {
        this.attenddt = attenddt;
        notifyPropertyChanged(BR.attenddt);
    }

    @Bindable
    public String getAttendat() {
        return attendat;
    }

    public void setAttendat(String attendat) {
        this.attendat = attendat;
        setAttendatx(attendat.equals("2") ? this.attendatx : "");
        notifyPropertyChanged(BR.attendat);
    }

    @Bindable
    public String getAttendatx() {
        return attendatx;
    }

    public void setAttendatx(String attendatx) {
        this.attendatx = attendatx;
        notifyPropertyChanged(BR.attendatx);
    }

    @Bindable
    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
        notifyPropertyChanged(BR.facility);
    }


    public Attendance Hydrate(Cursor cursor) throws JSONException {
        this.id = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_UID));
        this.projectName = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_PROJECT_NAME));
        this.sno = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_SNO));
        this.userName = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_USERNAME));
        this.sysDate = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_SYSDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_DEVICEID));
        this.deviceTag = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_DEVICETAGID));
        //   this.entryType = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_ENTRY_TYPE));
        this.appver = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_APPVERSION));
        this.iStatus = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_ISTATUS));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_SYNCED));
        this.syncDate = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_SYNC_DATE));
        this.gpsLat = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_GPSACC));

        aTTHydrate(cursor.getString(cursor.getColumnIndexOrThrow(AttendanceTable.COLUMN_ATT)));
        return this;
    }

    public void aTTHydrate(String string) throws JSONException {
        Log.d(TAG, "aTTHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.attenddt = json.getString("attenddt");
            this.attendat = json.getString("attendat");
            this.attendatx = json.getString("attendatx");
            this.facility = json.getString("facility");


        }
    }

    public String aTTtoString() throws JSONException {
        Log.d(TAG, "aTTtoString: ");
        JSONObject json = new JSONObject();
        json.put("attenddt", attenddt)
                .put("attendat", attendat)
                .put("attendatx", attendatx)
                .put("facility", facility);
        return json.toString();
    }


    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(AttendanceTable.COLUMN_ID, this.id);
        json.put(AttendanceTable.COLUMN_UID, this.uid);
        json.put(AttendanceTable.COLUMN_PROJECT_NAME, this.projectName);
        json.put(AttendanceTable.COLUMN_SNO, this.sno);
        json.put(AttendanceTable.COLUMN_USERNAME, this.userName);
        json.put(AttendanceTable.COLUMN_SYSDATE, this.sysDate);
        json.put(AttendanceTable.COLUMN_DEVICEID, this.deviceId);
        json.put(AttendanceTable.COLUMN_DEVICETAGID, this.deviceTag);
        //    json.put(FormsTable.COLUMN_ENTRY_TYPE, this.entryType);
        json.put(AttendanceTable.COLUMN_ISTATUS, this.iStatus);
        json.put(AttendanceTable.COLUMN_SYNCED, this.synced);
        json.put(AttendanceTable.COLUMN_SYNC_DATE, this.syncDate);
        json.put(AttendanceTable.COLUMN_APPVERSION, this.appver);
        json.put(AttendanceTable.COLUMN_GPSLAT, this.gpsLat);
        json.put(AttendanceTable.COLUMN_GPSLNG, this.gpsLng);
        json.put(AttendanceTable.COLUMN_GPSDATE, this.gpsDT);
        json.put(AttendanceTable.COLUMN_GPSACC, this.gpsAcc);
        json.put(AttendanceTable.COLUMN_ATT, new JSONObject(aTTtoString()));
        return json;
    }
}
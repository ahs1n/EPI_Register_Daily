package edu.aku.hassannaqvi.epi_register_daily.models;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.aku.hassannaqvi.epi_register_daily.BR;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;


public class FormVB extends BaseObservable implements Observable {

    private final String TAG = "FormVB";
    private final transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();
    // APP VARIABLES
    private String projectName = PROJECT_NAME;
    // APP VARIABLES
    private String id = _EMPTY_;
    private String uid = _EMPTY_;
    private String wid = _EMPTY_;
    private String aid = _EMPTY_;
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
    private String backfilename = _EMPTY_;
    private String frontfilename = _EMPTY_;
    private String childfilename = _EMPTY_;
    private String gpsLat = _EMPTY_;
    private String gpsLng = _EMPTY_;
    private String gpsDT = _EMPTY_;
    private String gpsAcc = _EMPTY_;
    private String cardNo = _EMPTY_;
    private String vb04aName = _EMPTY_;
    private String ucCode = _EMPTY_;
    private String facilityCode = _EMPTY_;
    private String villageCode = _EMPTY_;

    // FIELD VARIABLES
    private String vb01 = _EMPTY_;
    private String vb02 = _EMPTY_;
    private String vb03 = _EMPTY_;
    private String vb04a = _EMPTY_;
    private String vb04 = _EMPTY_;
    private String vb04bd = _EMPTY_;
    private String vb04bm = _EMPTY_;
    private String vb04by = _EMPTY_;
    private String vb04dk = _EMPTY_;
    private String vb05y = _EMPTY_;
    private String vb05m = _EMPTY_;
    private String vb05d = _EMPTY_;
    private String vb05a = _EMPTY_;
    private String vb06 = _EMPTY_;
    private String vb07 = _EMPTY_;
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
    private String vb08wdt = _EMPTY_;
    private String vb08wa = _EMPTY_;
    private String vb08wb = _EMPTY_;
    private String vb08wc = _EMPTY_;
    private String vb08wd = _EMPTY_;
    private String vb08we = _EMPTY_;
    private String vb09 = _EMPTY_;
    private String vb08cadt = _EMPTY_;
    private String vb08cbdt = _EMPTY_;
    private String vb08ccdt = _EMPTY_;
    private String vb08cddt = _EMPTY_;
    private String vb08cedt = _EMPTY_;
    private String vb08cfdt = _EMPTY_;
    private String vb08cgdt = _EMPTY_;
    private String vb08chdt = _EMPTY_;
    private String vb08cidt = _EMPTY_;


    public FormVB() {

/*        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setUserName(MainApp.user.getUserName());
        setDeviceId(MainApp.deviceid);
        setAppver(MainApp.appInfo.getAppVersion());
        setAppver(MainApp.appInfo.getAppVersion());*/

    }


    public void populateMeta() {

        setSysDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()));
        setUserName(MainApp.user.getUserName());
        setUcCode(MainApp.user.getUccode());
        setDeviceId(MainApp.deviceid);
        setUuid(MainApp.formVA.getUid());  // not applicable in Form table
        setWid(MainApp.workLocation.getUid());
        setAid(MainApp.attendance.getUid());
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

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
    public String getBackfilename() {
        return backfilename;
    }

    public void setBackfilename(String backfilename) {
        if (this.backfilename.equals(backfilename)) return;
        this.backfilename = backfilename;

        notifyPropertyChanged(BR.backfilename);
    }

    @Bindable
    public String getFrontfilename() {
        return frontfilename;
    }

    public void setFrontfilename(String frontfilename) {
        if (this.frontfilename.equals(frontfilename)) return;
        this.frontfilename = frontfilename;

        notifyPropertyChanged(BR.frontfilename);
    }

    @Bindable
    public String getChildfilename() {
        return childfilename;
    }

    public void setChildfilename(String childfilename) {
        if (this.childfilename.equals(childfilename)) return;
        this.childfilename = childfilename;

        notifyPropertyChanged(BR.childfilename);
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getVb04aName() {
        return vb04aName;
    }

    public void setVb04aName(String vb04aName) {
        this.vb04aName = vb04aName;
    }

    @Bindable
    public String getVb01() {
        return vb01;
    }

    public void setVb01(String vb01) {
        this.vb01 = vb01;
        notifyPropertyChanged(BR.vb01);
    }

    @Bindable
    public String getVb02() {
        return vb02;
    }

    public void setVb02(String vb02) {
        this.vb02 = vb02;
        setCardNo(vb02);
        notifyPropertyChanged(BR.vb02);
    }

    @Bindable
    public String getVb03() {
        return vb03;
    }

    public void setVb03(String vb03) {
        this.vb03 = vb03;
        setVb04bd(vb03.equals("2") ? this.vb04bd : "");
        setVb04bm(vb03.equals("2") ? this.vb04bm : "");
        setVb04by(vb03.equals("2") ? this.vb04by : "");

        setVb05d(vb03.equals("2") ? this.vb05d : "");
        setVb05m(vb03.equals("2") ? this.vb05m : "");

        setVb08ca(vb03.equals("2") ? this.vb08ca : "");
        setVb08cb(vb03.equals("2") ? this.vb08cb : "");
        setVb08cc(vb03.equals("2") ? this.vb08cc : "");
        setVb08cd(vb03.equals("2") ? this.vb08cd : "");
        setVb08ce(vb03.equals("2") ? this.vb08ce : "");
        setVb08cf(vb03.equals("2") ? this.vb08cf : "");
        setVb08cg(vb03.equals("2") ? this.vb08cg : "");
        setVb08ch(vb03.equals("2") ? this.vb08ch : "");
        setVb08ci(vb03.equals("2") ? this.vb08ci : "");
        setVb08cadt(vb03.equals("2") ? this.vb08cadt : "");
        setVb08cbdt(vb03.equals("2") ? this.vb08cbdt : "");
        setVb08ccdt(vb03.equals("2") ? this.vb08ccdt : "");
        setVb08cddt(vb03.equals("2") ? this.vb08cddt : "");
        setVb08cedt(vb03.equals("2") ? this.vb08cedt : "");
        setVb08cfdt(vb03.equals("2") ? this.vb08cfdt : "");
        setVb08cgdt(vb03.equals("2") ? this.vb08cgdt : "");
        setVb08chdt(vb03.equals("2") ? this.vb08chdt : "");
        setVb08cidt(vb03.equals("2") ? this.vb08cidt : "");

        setVb05a(vb03.equals("2") ? this.vb05a : "");
        setVb09(vb03.equals("2") ? this.vb09 : "");

        setFrontfilename(vb03.equals("2") ? this.frontfilename : "");
        setBackfilename(vb03.equals("2") ? this.backfilename : "");
        setChildfilename(vb03.equals("2") ? this.childfilename : "");

        setVb08w(vb03.equals("1") ? this.vb08w : "");
        setVb08wdt(vb03.equals("1") ? this.vb08wdt : "");
        notifyPropertyChanged(BR.vb03);
    }

    @Bindable
    public String getVb04a() {
        return vb04a;
    }

    public void setVb04a(String vb04a) {
        this.vb04a = vb04a;
        setVb04aName(vb04a);
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
    public String getVb04bd() {
        return vb04bd;
    }

    public void setVb04bd(String vb04bd) {
        this.vb04bd = vb04bd;
        CaluculateAge();
        notifyPropertyChanged(BR.vb04bd);
    }

    @Bindable
    public String getVb04bm() {
        return vb04bm;
    }

    public void setVb04bm(String vb04bm) {
        this.vb04bm = vb04bm;
        CaluculateAge();
        notifyPropertyChanged(BR.vb04bm);
    }

    @Bindable
    public String getVb04by() {
        return vb04by;
    }

    public void setVb04by(String vb04by) {
        this.vb04by = vb04by;
        CaluculateAge();
        notifyPropertyChanged(BR.vb04by);
    }


    @Bindable
    public String getVb04dk() {
        return vb04dk;
    }

    public void setVb04dk(String vb04dkdk) {
        this.vb04dk = vb04dkdk;
        setVb04bd(vb04dkdk.equals("98") ? "" : this.vb04bd);
        setVb04bm(vb04dkdk.equals("98") ? "" : this.vb04bm);
        setVb04by(vb04dkdk.equals("9998") ? "" : this.vb04by);

        setVb05d(vb04dk.equals("98") ? this.vb05d : "");
        setVb05m(vb04dk.equals("98") ? this.vb05m : "");
        setVb05y(vb04dk.equals("98") ? this.vb05y : "");
        notifyPropertyChanged(BR.vb04dk);
    }

    @Bindable
    public String getVb05y() {
        return vb05y;
    }

    public void setVb05y(String vb05y) {
        this.vb05y = vb05y;
        notifyPropertyChanged(BR.vb05y);
    }

    @Bindable
    public String getVb05m() {
        return vb05m;
    }

    public void setVb05m(String vb05m) {
        this.vb05m = vb05m;
        notifyPropertyChanged(BR.vb05m);
    }

    @Bindable
    public String getVb05d() {
        return vb05d;
    }

    public void setVb05d(String vb05d) {
        this.vb05d = vb05d;
        notifyPropertyChanged(BR.vb05d);
    }

    @Bindable
    public String getVb05a() {
        return vb05a;
    }

    public void setVb05a(String vb05a) {
        this.vb05a = vb05a;
        notifyPropertyChanged(BR.vb05a);
    }

    @Bindable
    public String getVb06() {
        return vb06;
    }

    public void setVb06(String vb06) {
        this.vb06 = vb06;
        notifyPropertyChanged(BR.vb06);
    }

    @Bindable
    public String getVb07() {
        return vb07;
    }

    public void setVb07(String vb07) {
        this.vb07 = vb07;
        notifyPropertyChanged(BR.vb07);
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
    public String getVb08wdt() {
        return vb08wdt;
    }

    public void setVb08wdt(String vb08wdt) {
        this.vb08wdt = vb08wdt;
        notifyPropertyChanged(BR.vb08wdt);
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

    @Bindable
    public String getVb09() {
        return vb09;
    }

    public void setVb09(String vb09) {
        this.vb09 = vb09;
        notifyPropertyChanged(BR.vb09);
    }


    @Bindable
    public String getVb08cadt() {
        return vb08cadt;
    }

    public void setVb08cadt(String vb08cadt) {
        this.vb08cadt = vb08cadt;
        notifyPropertyChanged(BR.vb08cadt);
    }

    @Bindable
    public String getVb08cbdt() {
        return vb08cbdt;
    }

    public void setVb08cbdt(String vb08cbdt) {
        this.vb08cbdt = vb08cbdt;
        notifyPropertyChanged(BR.vb08cbdt);
    }

    @Bindable
    public String getVb08ccdt() {
        return vb08ccdt;
    }

    public void setVb08ccdt(String vb08ccdt) {
        this.vb08ccdt = vb08ccdt;
        notifyPropertyChanged(BR.vb08ccdt);
    }

    @Bindable
    public String getVb08cddt() {
        return vb08cddt;
    }

    public void setVb08cddt(String vb08cddt) {
        this.vb08cddt = vb08cddt;
        notifyPropertyChanged(BR.vb08cddt);
    }

    @Bindable
    public String getVb08cedt() {
        return vb08cedt;
    }

    public void setVb08cedt(String vb08cedt) {
        this.vb08cedt = vb08cedt;
        notifyPropertyChanged(BR.vb08cedt);
    }

    @Bindable
    public String getVb08cfdt() {
        return vb08cfdt;
    }

    public void setVb08cfdt(String vb08cfdt) {
        this.vb08cfdt = vb08cfdt;
        notifyPropertyChanged(BR.vb08cfdt);
    }

    @Bindable
    public String getVb08cgdt() {
        return vb08cgdt;
    }

    public void setVb08cgdt(String vb08cgdt) {
        this.vb08cgdt = vb08cgdt;
        notifyPropertyChanged(BR.vb08cgdt);
    }

    @Bindable
    public String getVb08chdt() {
        return vb08chdt;
    }

    public void setVb08chdt(String vb08chdt) {
        this.vb08chdt = vb08chdt;
        notifyPropertyChanged(BR.vb08chdt);
    }

    @Bindable
    public String getVb08cidt() {
        return vb08cidt;
    }

    public void setVb08cidt(String vb08cidt) {
        this.vb08cidt = vb08cidt;
        notifyPropertyChanged(BR.vb08cidt);
    }

    @Bindable
    public String getUcCode() {
        return ucCode;
    }

    public void setUcCode(String ucCode) {
        this.ucCode = ucCode;
    }

    @Bindable
    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
        notifyPropertyChanged(BR.villageCode);
    }

    @Bindable
    public String getFacilityCode() {
        return facilityCode;
    }

    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
        notifyPropertyChanged(BR.facilityCode);
    }


    public FormVB Hydrate(Cursor cursor) throws JSONException {
        this.id = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_ID));
        this.uid = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_UID));
        this.wid = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_WID));
        this.aid = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_AID));
        this.uuid = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_UUID));
        this.projectName = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_PROJECT_NAME));
        this.sno = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SNO));
        this.userName = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_USERNAME));
        this.ucCode = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_UC_CODE));
        this.villageCode = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_VILLAGE_CODE));
        this.facilityCode = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_FACILITY_CODE));
        this.sysDate = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SYSDATE));
        this.deviceId = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_DEVICEID));
        this.deviceTag = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_DEVICETAGID));
        this.cardNo = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_CARD_NO));
        this.vb04aName = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_VB04A_NAME));
        //   this.entryType = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_ENTRY_TYPE));
        this.appver = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_APPVERSION));
        this.iStatus = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_ISTATUS));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SYNCED));
        this.syncDate = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_SYNC_DATE));
        this.gpsLat = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_GPSLNG));
        this.gpsDT = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_GPSDATE));
        this.gpsAcc = cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_GPSACC));

        vBHydrate(cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_VB)));
        vACHydrate(cursor.getString(cursor.getColumnIndexOrThrow(FormsVBTable.COLUMN_VAC)));
        return this;
    }

    public void vBHydrate(String string) throws JSONException {
        Log.d(TAG, "vBHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.vb01 = json.getString("vb01");
            this.vb02 = json.getString("vb02");
            this.vb03 = json.getString("vb03");
            this.vb04a = json.getString("vb04a");
            this.vb04 = json.getString("vb04");
            this.vb04bd = json.getString("vb04bd");
            this.vb04bm = json.getString("vb04bm");
            this.vb04by = json.getString("vb04by");
            this.vb05y = json.getString("vb05y");
            this.vb05m = json.getString("vb05m");
            this.vb05d = json.getString("vb05d");
            this.vb05a = json.getString("vb05a");
            this.vb06 = json.getString("vb06");
            this.vb07 = json.getString("vb07");
            this.vb09 = json.getString("vb09");

        }
    }

    public void vACHydrate(String string) throws JSONException {
        Log.d(TAG, "vACHydrate: " + string);
        if (string != null) {
            JSONObject json = null;
            json = new JSONObject(string);
            this.vb08ca = json.getString("vb08ca");
            this.vb08cadt = json.getString("vb08cadt");
            this.vb08cb = json.getString("vb08cb");
            this.vb08cbdt = json.getString("vb08cbdt");
            this.vb08cc = json.getString("vb08cc");
            this.vb08ccdt = json.getString("vb08ccdt");
            this.vb08cd = json.getString("vb08cd");
            this.vb08cddt = json.getString("vb08cddt");
            this.vb08ce = json.getString("vb08ce");
            this.vb08cedt = json.getString("vb08cedt");
            this.vb08cf = json.getString("vb08cf");
            this.vb08cfdt = json.getString("vb08cfdt");
            this.vb08cg = json.getString("vb08cg");
            this.vb08cgdt = json.getString("vb08cgdt");
            this.vb08ch = json.getString("vb08ch");
            this.vb08chdt = json.getString("vb08chdt");
            this.vb08ci = json.getString("vb08ci");
            this.vb08cidt = json.getString("vb08cidt");
            this.vb08w = json.getString("vb08w");
            this.vb08wdt = json.getString("vb08wdt");
            this.backfilename = json.getString("backfilename");
            this.frontfilename = json.getString("frontfilename");
            this.childfilename = json.getString("childfilename");

        }
    }


    public String vBtoString() throws JSONException {
        Log.d(TAG, "vBtoString: ");
        JSONObject json = new JSONObject();
        json.put("vb01", vb01)
                .put("vb02", vb02)
                .put("vb03", vb03)
                .put("vb04a", vb04a)
                .put("vb04", vb04)
                .put("vb04bd", vb04bd)
                .put("vb04bm", vb04bm)
                .put("vb04by", vb04by)
                .put("vb05y", vb05y)
                .put("vb05m", vb05m)
                .put("vb05d", vb05d)
                .put("vb05a", vb05a)
                .put("vb06", vb06)
                .put("vb07", vb07)
                .put("vb09", vb09);
        return json.toString();
    }

    public String vACtoString() throws JSONException {
        Log.d(TAG, "vACtoString: ");
        JSONObject json = new JSONObject();
        json.put("vb08ca", vb08ca)
                .put("vb08cadt", vb08cadt)
                .put("vb08cb", vb08cb)
                .put("vb08cbdt", vb08cbdt)
                .put("vb08cc", vb08cc)
                .put("vb08ccdt", vb08ccdt)
                .put("vb08cd", vb08cd)
                .put("vb08cddt", vb08cddt)
                .put("vb08ce", vb08ce)
                .put("vb08cedt", vb08cedt)
                .put("vb08cf", vb08cf)
                .put("vb08cfdt", vb08cfdt)
                .put("vb08cg", vb08cg)
                .put("vb08cgdt", vb08cgdt)
                .put("vb08ch", vb08ch)
                .put("vb08chdt", vb08chdt)
                .put("vb08ci", vb08ci)
                .put("vb08cidt", vb08cidt)
                .put("vb08w", vb08w)
                .put("vb08wdt", vb08wdt)
                .put("backfilename", backfilename)
                .put("frontfilename", frontfilename)
                .put("childfilename", childfilename);
        return json.toString();
    }


    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(FormsVBTable.COLUMN_ID, this.id);
        json.put(FormsVBTable.COLUMN_UID, this.uid);
        json.put(FormsVBTable.COLUMN_WID, this.wid);
        json.put(FormsVBTable.COLUMN_AID, this.aid);
        json.put(FormsVBTable.COLUMN_PROJECT_NAME, this.projectName);
        json.put(FormsVBTable.COLUMN_UUID, this.uuid);
        json.put(FormsVBTable.COLUMN_SNO, this.sno);
        json.put(FormsVBTable.COLUMN_USERNAME, this.userName);
        json.put(FormsVBTable.COLUMN_UC_CODE, this.ucCode);
        json.put(FormsVBTable.COLUMN_VILLAGE_CODE, this.villageCode);
        json.put(FormsVBTable.COLUMN_FACILITY_CODE, this.facilityCode);
        json.put(FormsVBTable.COLUMN_SYSDATE, this.sysDate);
        json.put(FormsVBTable.COLUMN_DEVICEID, this.deviceId);
        json.put(FormsVBTable.COLUMN_DEVICETAGID, this.deviceTag);
        json.put(FormsVBTable.COLUMN_CARD_NO, this.cardNo);
        json.put(FormsVBTable.COLUMN_VB04A_NAME, this.vb04aName);
        //    json.put(FormsVBTable.COLUMN_ENTRY_TYPE, this.entryType);
        json.put(FormsVBTable.COLUMN_ISTATUS, this.iStatus);
        json.put(FormsVBTable.COLUMN_SYNCED, this.synced);
        json.put(FormsVBTable.COLUMN_SYNC_DATE, this.syncDate);
        json.put(FormsVBTable.COLUMN_APPVERSION, this.appver);
        json.put(FormsVBTable.COLUMN_GPSLAT, this.gpsLat);
        json.put(FormsVBTable.COLUMN_GPSLNG, this.gpsLng);
        json.put(FormsVBTable.COLUMN_GPSDATE, this.gpsDT);
        json.put(FormsVBTable.COLUMN_GPSACC, this.gpsAcc);
        json.put(FormsVBTable.COLUMN_VB, new JSONObject(vBtoString()));
        json.put(FormsVBTable.COLUMN_VAC, new JSONObject(vACtoString()));
        return json;
    }


    private void CaluculateAge() {
        Log.d(TAG, "CaluculateAge: " + this.vb04by + "-" + this.vb04bm + "-" + this.vb04bd);

        if (!this.vb04by.equals("") && !this.vb04by.equals("9998") && !this.vb04bm.equals("") && !this.vb04bd.equals("")) {

            int day = !this.vb04bd.equals("98") ? Integer.parseInt(this.vb04bd) : 15;
            int month = !this.vb04bm.equals("98") ? Integer.parseInt(this.vb04bm) : 6;
            int year = Integer.parseInt(this.vb04by);

            SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd", Locale.ENGLISH);
            String todayDate = df.format(Calendar.getInstance().getTime());
            Calendar cal = Calendar.getInstance();
            Calendar cur = Calendar.getInstance();

            try {
                cal.setTime(df.parse(year + " " + month + " " + day));
                long millis = System.currentTimeMillis() - cal.getTimeInMillis();
                cal.setTimeInMillis(millis);

             /*   int mYear = cal.get(Calendar.YEAR)-1970;
                int mMonth = cal.get(Calendar.MONTH);
                int mDay = cal.get(Calendar.DAY_OF_MONTH)-1;

                Log.d(TAG, "CaluculateAge: " + (mYear) + "-" + mMonth + "-" + mDay);
*/
                long tYear = MILLISECONDS.toDays(millis) / 365;
                long tMonth = (MILLISECONDS.toDays(millis) - (tYear * 365)) / 30;
                long tDay = MILLISECONDS.toDays(millis) - ((tYear * 365) + (tMonth * 30));

                Log.d(TAG, "CaluculateAge: Y-" + tYear + " M-" + tMonth + " D-" + tDay);
                setVb05d(String.valueOf(tDay));
                setVb05m(String.valueOf(tMonth));
                setVb05y(String.valueOf(tYear));
                //setAge(String.valueOf(((tYear) * 12) + tMonth));


        /*        String.format("%d min, %d sec",
                        ,
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                );*/

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
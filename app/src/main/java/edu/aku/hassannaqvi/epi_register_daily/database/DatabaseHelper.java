package edu.aku.hassannaqvi.epi_register_daily.database;

import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.IBAHC;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.PROJECT_NAME;
import static edu.aku.hassannaqvi.epi_register_daily.core.MainApp.formVB;
import static edu.aku.hassannaqvi.epi_register_daily.core.UserAuth.checkPassword;
import static edu.aku.hassannaqvi.epi_register_daily.database.CreateTable.DATABASE_VERSION;
import static edu.aku.hassannaqvi.epi_register_daily.database.CreateTable.SQL_CREATE_ENTRYLOGS;
import static edu.aku.hassannaqvi.epi_register_daily.database.CreateTable.SQL_CREATE_FORMSVA;
import static edu.aku.hassannaqvi.epi_register_daily.database.CreateTable.SQL_CREATE_FORMSVB;
import static edu.aku.hassannaqvi.epi_register_daily.database.CreateTable.SQL_CREATE_USERS;
import static edu.aku.hassannaqvi.epi_register_daily.database.CreateTable.SQL_CREATE_VERSIONAPP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.EntryLogTable;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormCRTable;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormWRTable;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVATable;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.FormsVBTable;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.UsersTable;
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.VersionTable;
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp;
import edu.aku.hassannaqvi.epi_register_daily.models.EntryLog;
import edu.aku.hassannaqvi.epi_register_daily.models.FormCR;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVA;
import edu.aku.hassannaqvi.epi_register_daily.models.FormVB;
import edu.aku.hassannaqvi.epi_register_daily.models.FormWR;
import edu.aku.hassannaqvi.epi_register_daily.models.Users;
import edu.aku.hassannaqvi.epi_register_daily.models.VersionApp;



/*import edu.aku.hassannaqvi.naunehal.models.Immunization;*/

/**
 * @author hassan.naqvi on 11/30/2016.
 * @update ali azaz on 01/07/21
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = PROJECT_NAME + ".db";
    public static final String DATABASE_COPY = PROJECT_NAME + "_copy.db";
    private final String TAG = "DatabaseHelper";
    private static final String DATABASE_PASSWORD = IBAHC;
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMSVA);
        db.execSQL(SQL_CREATE_FORMSVB);
        db.execSQL(SQL_CREATE_VERSIONAPP);
        db.execSQL(SQL_CREATE_ENTRYLOGS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            case 2:
        }
    }


    //ADDITION in DB
    public Long addFormVA(FormVA formVA) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(FormsVATable.COLUMN_PROJECT_NAME, formVA.getProjectName());
        values.put(FormsVATable.COLUMN_UID, formVA.getUid());
        values.put(FormsVATable.COLUMN_SNO, formVA.getSno());
        values.put(FormsVATable.COLUMN_USERNAME, formVA.getUserName());
        values.put(FormsVATable.COLUMN_SYSDATE, formVA.getSysDate());
        values.put(FormsVATable.COLUMN_VA, formVA.vAtoString());
        values.put(FormsVATable.COLUMN_GPSLAT, formVA.getGpsLat());
        values.put(FormsVATable.COLUMN_GPSLNG, formVA.getGpsLng());
        values.put(FormsVATable.COLUMN_GPSDATE, formVA.getGpsDT());
        values.put(FormsVATable.COLUMN_GPSACC, formVA.getGpsAcc());
        values.put(FormsVATable.COLUMN_ISTATUS, formVA.getiStatus());
        values.put(FormsVATable.COLUMN_DEVICETAGID, formVA.getDeviceTag());
        values.put(FormsVATable.COLUMN_DEVICEID, formVA.getDeviceId());
        values.put(FormsVATable.COLUMN_APPVERSION, formVA.getAppver());
        values.put(FormsVATable.COLUMN_SYNCED, formVA.getSynced());
        values.put(FormsVATable.COLUMN_SYNC_DATE, formVA.getSyncDate());

        long newRowId;
        newRowId = db.insert(
                FormsVATable.TABLE_NAME,
                FormsVATable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addFormVB(FormVB formVB) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(FormsVBTable.COLUMN_PROJECT_NAME, formVB.getProjectName());
        values.put(FormsVBTable.COLUMN_UID, formVB.getUid());
        values.put(FormsVBTable.COLUMN_UUID, formVB.getUuid());
        values.put(FormsVBTable.COLUMN_SNO, formVB.getSno());
        values.put(FormsVBTable.COLUMN_USERNAME, formVB.getUserName());
        values.put(FormsVBTable.COLUMN_SYSDATE, formVB.getSysDate());
        values.put(FormsVBTable.COLUMN_VB, formVB.vBtoString());
        values.put(FormsVBTable.COLUMN_ISTATUS, formVB.getiStatus());
        values.put(FormsVBTable.COLUMN_DEVICETAGID, formVB.getDeviceTag());
        values.put(FormsVBTable.COLUMN_DEVICEID, formVB.getDeviceId());
        values.put(FormsVBTable.COLUMN_APPVERSION, formVB.getAppver());
        values.put(FormsVBTable.COLUMN_SYNCED, formVB.getSynced());
        values.put(FormsVBTable.COLUMN_SYNC_DATE, formVB.getSyncDate());

        long newRowId;
        newRowId = db.insert(
                FormsVBTable.TABLE_NAME,
                FormsVBTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addEntryLog(EntryLog entryLog) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(EntryLogTable.COLUMN_PROJECT_NAME, entryLog.getProjectName());
        values.put(EntryLogTable.COLUMN_UUID, entryLog.getUuid());
        values.put(EntryLogTable.COLUMN_PSU_CODE, entryLog.getPsuCode());
        values.put(EntryLogTable.COLUMN_HHID, entryLog.getHhid());
        values.put(EntryLogTable.COLUMN_USERNAME, entryLog.getUserName());
        values.put(EntryLogTable.COLUMN_SYSDATE, entryLog.getSysDate());
        values.put(EntryLogTable.COLUMN_ISTATUS, entryLog.getiStatus());
        values.put(EntryLogTable.COLUMN_ISTATUS96x, entryLog.getiStatus96x());
        values.put(EntryLogTable.COLUMN_ENTRY_TYPE, entryLog.getEntryType());
        values.put(EntryLogTable.COLUMN_ENTRY_DATE, entryLog.getEntryDate());
        values.put(EntryLogTable.COLUMN_DEVICEID, entryLog.getDeviceId());
        values.put(EntryLogTable.COLUMN_SYNCED, entryLog.getSynced());
        values.put(EntryLogTable.COLUMN_SYNC_DATE, entryLog.getSyncDate());
        values.put(EntryLogTable.COLUMN_APPVERSION, entryLog.getAppver());

        long newRowId;
        newRowId = db.insertOrThrow(
                EntryLogTable.TABLE_NAME,
                EntryLogTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addCR(FormCR cr) throws JSONException {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormCRTable.COLUMN_PROJECT_NAME, cr.getProjectName());
        values.put(FormCRTable.COLUMN_UID, cr.getUid());
        values.put(FormCRTable.COLUMN_USERNAME, cr.getUserName());
        values.put(FormCRTable.COLUMN_SYSDATE, cr.getSysDate());
        values.put(FormCRTable.COLUMN_ISTATUS, cr.getiStatus());
        values.put(FormCRTable.COLUMN_DEVICETAGID, cr.getDeviceTag());
        values.put(FormCRTable.COLUMN_DEVICEID, cr.getDeviceId());
        values.put(FormCRTable.COLUMN_APPVERSION, cr.getAppver());
        values.put(FormCRTable.COLUMN_START_TIME, cr.getStartTime());
        values.put(FormCRTable.COLUMN_END_TIME, cr.getEndTime());

        // Put all JSON as xxtoString()
        values.put(FormCRTable.COLUMN_CR, cr.cRtoString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormCRTable.TABLE_NAME,
                FormCRTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public Long addWR(FormWR wr) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(FormWRTable.COLUMN_PROJECT_NAME, wr.getProjectName());
        values.put(FormWRTable.COLUMN_UID, wr.getUid());
        values.put(FormWRTable.COLUMN_USERNAME, wr.getUserName());
        values.put(FormWRTable.COLUMN_SYSDATE, wr.getSysDate());
        values.put(FormWRTable.COLUMN_ISTATUS, wr.getiStatus());
        values.put(FormWRTable.COLUMN_DEVICETAGID, wr.getDeviceTag());
        values.put(FormWRTable.COLUMN_DEVICEID, wr.getDeviceId());
        values.put(FormWRTable.COLUMN_APPVERSION, wr.getAppver());
        values.put(FormWRTable.COLUMN_START_TIME, wr.getStartTime());
        values.put(FormWRTable.COLUMN_END_TIME, wr.getEndTime());

        // Put all JSON as xxtoString()
        values.put(FormWRTable.COLUMN_WR, wr.wRtoString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormWRTable.TABLE_NAME,
                FormWRTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    //UPDATE in DB
    public int updatesFormVAColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsVATable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.formVA.getId())};

        return db.update(FormsVATable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public int updatesFormVBColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormsVBTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(formVB.getId())};

        return db.update(FormsVBTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public int updatesEntryLogColumn(String column, String value, String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = EntryLogTable._ID + " =? ";
        String[] selectionArgs = {id};

        return db.update(EntryLogTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public int updateCrColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormCRTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.cr.getId())};

        return db.update(FormCRTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public int updateWrColumn(String column, String value) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = FormWRTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.wr.getId())};

        return db.update(FormWRTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FormCRTable.COLUMN_ISTATUS, MainApp.cr.getiStatus());

        // Which row to update, based on the ID
        String selection = FormCRTable.COLUMN_ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.cr.getId())};

        return db.update(FormCRTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    /*
     * Functions that dealing with table data
     * */

    public boolean doLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalArgumentException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause = UsersTable.COLUMN_USERNAME + "=? ";
        String[] whereArgs = {username};
        String groupBy = null;
        String having = null;
        String orderBy = UsersTable.COLUMN_ID + " ASC";

        Users loggedInUser = new Users();
        c = db.query(
                UsersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            loggedInUser = new Users().hydrate(c);

        }

        c.close();

        db.close();

        if (checkPassword(password, loggedInUser.getPassword())) {
            MainApp.user = loggedInUser;
            return c.getCount() > 0;
        } else {
            return false;
        }
    }


    public ArrayList<FormCR> getFormsByDate(String sysdate) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = {
                FormCRTable._ID,
                FormCRTable.COLUMN_UID,
                FormCRTable.COLUMN_SYSDATE,
                FormCRTable.COLUMN_USERNAME,
                FormCRTable.COLUMN_ISTATUS,
                FormCRTable.COLUMN_SYNCED,

        };
        String whereClause = FormCRTable.COLUMN_SYSDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + sysdate + " %"};
        String groupBy = null;
        String having = null;
        String orderBy = FormCRTable.COLUMN_ID + " ASC";
        ArrayList<FormCR> allForms = new ArrayList<>();
        try {
            c = db.query(
                    FormCRTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                FormCR forms = new FormCR();
                forms.setId(c.getString(c.getColumnIndexOrThrow(FormCRTable.COLUMN_ID)));
                forms.setUid(c.getString(c.getColumnIndexOrThrow(FormCRTable.COLUMN_UID)));
                forms.setSysDate(c.getString(c.getColumnIndexOrThrow(FormCRTable.COLUMN_SYSDATE)));
                forms.setUserName(c.getString(c.getColumnIndexOrThrow(FormCRTable.COLUMN_USERNAME)));
                allForms.add(forms);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allForms;
    }


    // istatus examples: (1) or (1,9) or (1,3,5)
    public FormCR getFormByAssessNo(String uid, String istatus) throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        whereClause = FormCRTable.COLUMN_UID + "=? AND " +
                FormCRTable.COLUMN_ISTATUS + " in " + istatus;

        String[] whereArgs = {uid};

        String groupBy = null;
        String having = null;

        String orderBy = FormCRTable.COLUMN_ID + " ASC";

        FormCR allFC = null;
        try {
            c = db.query(
                    FormCRTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                allFC = new FormCR().Hydrate(c);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public ArrayList<Cursor> getDatabaseManagerData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase(DATABASE_PASSWORD);
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(Query, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (Exception sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    /*public int updateTemp(String assessNo, String temp) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_TSF305, temp);
        values.put(FormsTable.COLUMN_SYSDATE, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(new Date().getTime()) + "-Updated");
        values.put(FormsTable.COLUMN_ISTATUS, "1");
        values.put(FormsTable.COLUMN_SYNCED, (byte[]) null);

        String selection = FormsTable.COLUMN_ASSESMENT_NO + " =? AND " + FormsTable.COLUMN_ISTATUS + " =? ";
        // String selection = FormsTable.COLUMN_ASSESMENT_NO + " =? ";
        String[] selectionArgs = {assessNo, "9"};
        // String[] selectionArgs = {assessNo};

        return db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }*/


    public int syncVersionApp(JSONObject VersionList) {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(VersionTable.TABLE_NAME, null, null);
        long count = 0;
        try {
            JSONObject jsonObjectCC = ((JSONArray) VersionList.get(VersionTable.COLUMN_VERSION_PATH)).getJSONObject(0);
            VersionApp Vc = new VersionApp();
            Vc.sync(jsonObjectCC);

            ContentValues values = new ContentValues();

            values.put(VersionTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            count = db.insert(VersionTable.TABLE_NAME, null, values);
            if (count > 0) count = 1;

        } catch (Exception ignored) {
        } finally {
            db.close();
        }

        return (int) count;
    }


    public int syncUser(JSONArray userList) {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(UsersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < userList.length(); i++) {

                JSONObject jsonObjectUser = userList.getJSONObject(i);

                Users user = new Users();
                user.sync(jsonObjectUser);
                ContentValues values = new ContentValues();

                values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
                values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
                values.put(UsersTable.COLUMN_FULLNAME, user.getFullname());
                values.put(UsersTable.COLUMN_ENABLED, user.getEnabled());
                values.put(UsersTable.COLUMN_ISNEW_USER, user.getNewUser());
                values.put(UsersTable.COLUMN_PWD_EXPIRY, user.getPwdExpiry());
                values.put(UsersTable.COLUMN_DESIGNATION, user.getDesignation());
                values.put(UsersTable.COLUMN_DIST_ID, user.getDist_id());
                long rowID = db.insert(UsersTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncUser(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }
/*

    public int syncClusters(JSONArray clusterList) {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(ClustersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < clusterList.length(); i++) {

                JSONObject json = clusterList.getJSONObject(i);

                Clusters cluster = new Clusters();
                cluster.sync(json);
                ContentValues values = new ContentValues();

                values.put(ClustersTable.COLUMN_DISTRICT_NAME, cluster.getDistrictName());
                values.put(ClustersTable.COLUMN_DISTRICT_CODE, cluster.getDistrictCode());
                values.put(ClustersTable.COLUMN_CITY_NAME, cluster.getCityName());
                values.put(ClustersTable.COLUMN_CITY_CODE, cluster.getCityCode());
                values.put(ClustersTable.COLUMN_CLUSTER_NO, cluster.getClusterNo());
                long rowID = db.insert(ClustersTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncClusters(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

    public int syncRandom(JSONArray list) {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(RandomTable.TABLE_NAME, null, null);
        int insertCount = 0;
        try {
            for (int i = 0; i < list.length(); i++) {

                JSONObject json = list.getJSONObject(i);

                RandomHH ran = new RandomHH();
                ran.sync(json);
                ContentValues values = new ContentValues();
                values.put(RandomTable.COLUMN_ID, ran.getID());
                values.put(RandomTable.COLUMN_SNO, ran.getSno());
                values.put(RandomTable.COLUMN_CLUSTER_NO, ran.getClusterNo());
                values.put(RandomTable.COLUMN_HH_NO, ran.getHhno());
                values.put(RandomTable.COLUMN_HEAD_HH, ran.getHeadhh());
                long rowID = db.insert(RandomTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;
            }

        } catch (Exception e) {
            Log.d(TAG, "syncRandom(e): " + e);
            db.close();
        } finally {
            db.close();
        }
        return insertCount;
    }

*/


    //get UnSyncedTables
    public JSONArray getUnsyncedFormVA() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        /*whereClause = FormsTable.COLUMN_SYNCED + " = '' AND " +
                FormsTable.COLUMN_ISTATUS + "!= ''";*/
        whereClause = FormsVATable.COLUMN_SYNCED + " = '' ";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = FormsVATable.COLUMN_ID + " ASC";

        JSONArray allForms = new JSONArray();
        c = db.query(
                FormsVATable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            /** WorkManager Upload
             /*Form fc = new Form();
             allFC.add(fc.Hydrate(c));*/
            Log.d(TAG, "getUnsyncedFormVA: " + c.getCount());
            FormVA formVA = new FormVA();
            allForms.put(formVA.Hydrate(c).toJSONObject());


        }

        c.close();
        db.close();

        Log.d(TAG, "getUnsyncedFormVA: " + allForms.toString().length());
        Log.d(TAG, "getUnsyncedFormVA: " + allForms);
        return allForms;
    }


    public JSONArray getUnsyncedFormVB() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        /*whereClause = FormsTable.COLUMN_SYNCED + " = '' AND " +
                FormsTable.COLUMN_ISTATUS + "!= ''";*/
        whereClause = FormsVBTable.COLUMN_SYNCED + " = '' ";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = FormsVBTable.COLUMN_ID + " ASC";

        JSONArray allForms = new JSONArray();
        c = db.query(
                FormsVBTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            /** WorkManager Upload
             /*Form fc = new Form();
             allFC.add(fc.Hydrate(c));*/
            Log.d(TAG, "getUnsyncedFormVB: " + c.getCount());
            FormVB formVB = new FormVB();
            allForms.put(formVB.Hydrate(c).toJSONObject());

        }

        c.close();
        db.close();

        Log.d(TAG, "getUnsyncedFormVB: " + allForms.toString().length());
        Log.d(TAG, "getUnsyncedFormVB: " + allForms);
        return allForms;
    }


    public JSONArray getUnsyncedEntryLog() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause;
        whereClause = EntryLogTable.COLUMN_SYNCED + " = '' ";

        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = EntryLogTable.COLUMN_ID + " ASC";

        JSONArray all = new JSONArray();
        c = db.query(
                EntryLogTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            Log.d(TAG, "getUnsyncedEntryLog: " + c.getCount());
            EntryLog entryLog = new EntryLog();
            all.put(entryLog.Hydrate(c).toJSONObject());
        }
        Log.d(TAG, "getUnsyncedEntryLog: " + all.toString().length());
        Log.d(TAG, "getUnsyncedEntryLog: " + all);
        return all;
    }

    public JSONArray getUnsyncedFormCR() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        whereClause = FormCRTable.COLUMN_SYNCED + " is null ";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = FormCRTable.COLUMN_ID + " ASC";

        JSONArray allCR = new JSONArray();
        try {
            c = db.query(
                    FormCRTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                /** WorkManager Upload
                 /*Form fc = new Form();
                 allFC.add(fc.Hydrate(c));*/
                Log.d(TAG, "getUnsyncedFormCR: " + c.getCount());
                FormCR cr = new FormCR();
                allCR.put(cr.Hydrate(c).toJSONObject());
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        Log.d(TAG, "getUnsyncedFormCR: " + allCR.toString().length());
        Log.d(TAG, "getUnsyncedFormCR: " + allCR);
        return allCR;
    }

    public JSONArray getUnsyncedFormWR() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        whereClause = FormWRTable.COLUMN_SYNCED + " is null ";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = FormWRTable.COLUMN_ID + " ASC";

        JSONArray allWR = new JSONArray();
        try {
            c = db.query(
                    FormWRTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                /** WorkManager Upload
                 /*Form fc = new Form();
                 allFC.add(fc.Hydrate(c));*/
                Log.d(TAG, "getUnsyncedFormWR: " + c.getCount());
                FormWR wr = new FormWR();
                allWR.put(wr.Hydrate(c).toJSONObject());
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        Log.d(TAG, "getUnsyncedFormWR: " + allWR.toString().length());
        Log.d(TAG, "getUnsyncedFormWR: " + allWR);
        return allWR;
    }

    //update SyncedTables
    public void updateSyncedFormVA(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsVATable.COLUMN_SYNCED, true);
        values.put(FormsVATable.COLUMN_SYNC_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsVATable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsVATable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedFormVB(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsVBTable.COLUMN_SYNCED, true);
        values.put(FormsVBTable.COLUMN_SYNC_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsVBTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsVBTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public void updateSyncedEntryLog(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(EntryLogTable.COLUMN_SYNCED, true);
        values.put(EntryLogTable.COLUMN_SYNC_DATE, new Date().toString());
        String where = EntryLogTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};
        int count = db.update(
                EntryLogTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedFormCR(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormCRTable.COLUMN_SYNCED, true);
        values.put(FormCRTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormCRTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormCRTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedFormWR(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormWRTable.COLUMN_SYNCED, true);
        values.put(FormWRTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormWRTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                FormWRTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }


    public int updatePassword(String hashedPassword) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(UsersTable.COLUMN_PASSWORD, hashedPassword);
        values.put(UsersTable.COLUMN_ISNEW_USER, "");

        String selection = UsersTable.COLUMN_USERNAME + " =? ";
        String[] selectionArgs = {MainApp.user.getUserName()};

        return db.update(UsersTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }




/*    public void updateSyncedSamp(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(SamplesTable.COLUMN_SYNCED, true);
        values.put(SamplesTable.COLUMN_SYNCED_DATE, new Date().toString());

        String where = SamplesTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                SamplesTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }*/


    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase(DATABASE_PASSWORD);
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }


    public List<FormVB> getAllMembers() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c;
        String[] columns = null;
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy = FormsVBTable.COLUMN_ID + " ASC";
        List<FormVB> allForm = new ArrayList<>();

        c = db.query(
                FormsVBTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            try {
                FormVB formVB = new FormVB().Hydrate(c);
                allForm.add(formVB);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        c.close();
        db.close();
        return allForm;
    }


}
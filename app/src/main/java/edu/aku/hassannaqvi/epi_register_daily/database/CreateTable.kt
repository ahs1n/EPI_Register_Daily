package edu.aku.hassannaqvi.epi_register_daily.database

import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.*
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp.PROJECT_NAME

object CreateTable {

    const val DATABASE_NAME = "$PROJECT_NAME.db"
    const val DATABASE_COPY = "${PROJECT_NAME}_copy.db"
    const val DATABASE_VERSION = 1

    const val SQL_CREATE_FORMSVA = ("CREATE TABLE "
            + FormsVATable.TABLE_NAME + "("
            + FormsVATable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsVATable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsVATable.COLUMN_UID + " TEXT,"
            + FormsVATable.COLUMN_SNO + " TEXT,"
            + FormsVATable.COLUMN_USERNAME + " TEXT,"
            + FormsVATable.COLUMN_SYSDATE + " TEXT,"
            + FormsVATable.COLUMN_ISTATUS + " TEXT,"
            + FormsVATable.COLUMN_DEVICEID + " TEXT,"
            + FormsVATable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsVATable.COLUMN_SYNCED + " TEXT,"
            + FormsVATable.COLUMN_SYNC_DATE + " TEXT,"
            + FormsVATable.COLUMN_APPVERSION + " TEXT,"
            + FormsVATable.COLUMN_GPSLAT + " TEXT,"
            + FormsVATable.COLUMN_GPSLNG + " TEXT,"
            + FormsVATable.COLUMN_GPSDATE + " TEXT,"
            + FormsVATable.COLUMN_GPSACC + " TEXT,"
            + FormsVATable.COLUMN_VA + " TEXT"
            + " );"
            )

    const val SQL_CREATE_FORMSVB = ("CREATE TABLE "
            + FormsVBTable.TABLE_NAME + "("
            + FormsVBTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsVBTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsVBTable.COLUMN_UID + " TEXT,"
            + FormsVBTable.COLUMN_UUID + " TEXT,"
            + FormsVBTable.COLUMN_SNO + " TEXT,"
            + FormsVBTable.COLUMN_USERNAME + " TEXT,"
            + FormsVBTable.COLUMN_SYSDATE + " TEXT,"
            + FormsVBTable.COLUMN_ISTATUS + " TEXT,"
            + FormsVBTable.COLUMN_DEVICEID + " TEXT,"
            + FormsVBTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsVBTable.COLUMN_CARD_NO + " TEXT,"
            + FormsVBTable.COLUMN_VB04A_NAME + " TEXT,"
            + FormsVBTable.COLUMN_SYNCED + " TEXT,"
            + FormsVBTable.COLUMN_SYNC_DATE + " TEXT,"
            + FormsVBTable.COLUMN_APPVERSION + " TEXT,"
            + FormsVATable.COLUMN_GPSLAT + " TEXT,"
            + FormsVATable.COLUMN_GPSLNG + " TEXT,"
            + FormsVATable.COLUMN_GPSDATE + " TEXT,"
            + FormsVATable.COLUMN_GPSACC + " TEXT,"
            + FormsVBTable.COLUMN_VB + " TEXT"
            + " );"
            )

    const val SQL_CREATE_VACCINE = ("CREATE TABLE "
            + VaccinesTable.TABLE_NAME + "("
            + VaccinesTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VaccinesTable.COLUMN_PROJECT_NAME + " TEXT,"
            + VaccinesTable.COLUMN_UID + " TEXT,"
            + VaccinesTable.COLUMN_UUID + " TEXT,"
            + VaccinesTable.COLUMN_SNO + " TEXT,"
            + VaccinesTable.COLUMN_USERNAME + " TEXT,"
            + VaccinesTable.COLUMN_SYSDATE + " TEXT,"
            + VaccinesTable.COLUMN_ISTATUS + " TEXT,"
            + VaccinesTable.COLUMN_DEVICEID + " TEXT,"
            + VaccinesTable.COLUMN_DEVICETAGID + " TEXT,"
            + VaccinesTable.COLUMN_SYNCED + " TEXT,"
            + VaccinesTable.COLUMN_SYNC_DATE + " TEXT,"
            + VaccinesTable.COLUMN_APPVERSION + " TEXT,"
            + VaccinesTable.COLUMN_VB02 + " TEXT,"
            + VaccinesTable.COLUMN_VB04A + " TEXT,"
            + VaccinesTable.COLUMN_VB04 + " TEXT,"
            + VaccinesTable.COLUMN_VB08C_CODE + " TEXT,"
            + VaccinesTable.COLUMN_VB08C_ANTIGEN + " TEXT,"
            + VaccinesTable.COLUMN_VB08C_DATE + " TEXT,"
            + VaccinesTable.COLUMN_VB08W_CODE + " TEXT,"
            + VaccinesTable.COLUMN_VB08W_ANTIGEN + " TEXT,"
            + VaccinesTable.COLUMN_VB08W_DATE + " TEXT,"
            + VaccinesTable.COLUMN_VACCINE + " TEXT"
            + " );"
            )

    const val SQL_CREATE_ATTENDANCE = ("CREATE TABLE "
            + AttendanceTable.TABLE_NAME + "("
            + AttendanceTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + AttendanceTable.COLUMN_PROJECT_NAME + " TEXT,"
            + AttendanceTable.COLUMN_UID + " TEXT,"
            + AttendanceTable.COLUMN_SNO + " TEXT,"
            + AttendanceTable.COLUMN_USERNAME + " TEXT,"
            + AttendanceTable.COLUMN_SYSDATE + " TEXT,"
            + AttendanceTable.COLUMN_ISTATUS + " TEXT,"
            + AttendanceTable.COLUMN_DEVICEID + " TEXT,"
            + AttendanceTable.COLUMN_DEVICETAGID + " TEXT,"
            + AttendanceTable.COLUMN_SYNCED + " TEXT,"
            + AttendanceTable.COLUMN_SYNC_DATE + " TEXT,"
            + AttendanceTable.COLUMN_APPVERSION + " TEXT,"
            + AttendanceTable.COLUMN_GPSLAT + " TEXT,"
            + AttendanceTable.COLUMN_GPSLNG + " TEXT,"
            + AttendanceTable.COLUMN_GPSDATE + " TEXT,"
            + AttendanceTable.COLUMN_GPSACC + " TEXT,"
            + AttendanceTable.COLUMN_ATT + " TEXT"
            + " );"
            )

    const val SQL_CREATE_FORMCR = ("CREATE TABLE "
            + FormCRTable.TABLE_NAME + "("
            + FormCRTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormCRTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormCRTable.COLUMN_UID + " TEXT,"
            + FormCRTable.COLUMN_USERNAME + " TEXT,"
            + FormCRTable.COLUMN_SYSDATE + " TEXT,"
            + FormCRTable.COLUMN_START_TIME + " TEXT,"
            + FormCRTable.COLUMN_END_TIME + " TEXT,"
            + FormCRTable.COLUMN_ISTATUS + " TEXT,"
            + FormCRTable.COLUMN_DEVICEID + " TEXT,"
            + FormCRTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormCRTable.COLUMN_SYNCED + " TEXT,"
            + FormCRTable.COLUMN_SYNCED_DATE + " TEXT,"
            + FormCRTable.COLUMN_APPVERSION + " TEXT,"
            + FormCRTable.COLUMN_CR + " TEXT"
            + " );"
            )

    const val SQL_CREATE_FORMWR = ("CREATE TABLE "
            + FormWRTable.TABLE_NAME + "("
            + FormWRTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormWRTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormWRTable.COLUMN_UID + " TEXT,"
            + FormWRTable.COLUMN_USERNAME + " TEXT,"
            + FormWRTable.COLUMN_SYSDATE + " TEXT,"
            + FormWRTable.COLUMN_START_TIME + " TEXT,"
            + FormWRTable.COLUMN_END_TIME + " TEXT,"
            + FormWRTable.COLUMN_ISTATUS + " TEXT,"
            + FormWRTable.COLUMN_DEVICEID + " TEXT,"
            + FormWRTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormWRTable.COLUMN_SYNCED + " TEXT,"
            + FormWRTable.COLUMN_SYNCED_DATE + " TEXT,"
            + FormWRTable.COLUMN_APPVERSION + " TEXT,"
            + FormWRTable.COLUMN_WR + " TEXT"
            + " );"
            )

    const val SQL_CREATE_USERS = ("CREATE TABLE "
            + UsersTable.TABLE_NAME + "("
            + UsersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULLNAME + " TEXT,"
            + UsersTable.COLUMN_DIST_ID + " TEXT,"
            + UsersTable.COLUMN_ENABLED + " TEXT,"
            + UsersTable.COLUMN_ISNEW_USER + " TEXT,"
            + UsersTable.COLUMN_PWD_EXPIRY + " TEXT,"
            + UsersTable.COLUMN_DESIGNATION + " TEXT"
            + " );"
            )


    const val SQL_CREATE_VERSIONAPP = ("CREATE TABLE "
            + VersionTable.TABLE_NAME + " ("
            + VersionTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + VersionTable.COLUMN_VERSION_CODE + " TEXT, "
            + VersionTable.COLUMN_VERSION_NAME + " TEXT, "
            + VersionTable.COLUMN_PATH_NAME + " TEXT "
            + ");"
            )

    const val SQL_CREATE_ENTRYLOGS = ("CREATE TABLE "
            + EntryLogTable.TABLE_NAME + "("
            + EntryLogTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EntryLogTable.COLUMN_PROJECT_NAME + " TEXT,"
            + EntryLogTable.COLUMN_UID + " TEXT,"
            + EntryLogTable.COLUMN_UUID + " TEXT,"
            + EntryLogTable.COLUMN_PSU_CODE + " TEXT,"
            + EntryLogTable.COLUMN_HHID + " TEXT,"
            + EntryLogTable.COLUMN_USERNAME + " TEXT,"
            + EntryLogTable.COLUMN_SYSDATE + " TEXT,"
            + EntryLogTable.COLUMN_DEVICEID + " TEXT,"
            + EntryLogTable.COLUMN_ENTRY_DATE + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS96x + " TEXT,"
            + EntryLogTable.COLUMN_ENTRY_TYPE + " TEXT,"
            + EntryLogTable.COLUMN_SYNCED + " TEXT,"
            + EntryLogTable.COLUMN_SYNC_DATE + " TEXT,"
            + EntryLogTable.COLUMN_APPVERSION + " TEXT"
            + " );"
            )

    const val SQL_CREATE_HF = ("CREATE TABLE " + TableHealthFacilities.TABLE_NAME + "("
            + TableHealthFacilities.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TableHealthFacilities.COLUMN_HF_CODE + " TEXT,"
            + TableHealthFacilities.COLUMN_HF_NAME + " TEXT,"
            + TableHealthFacilities.COLUMN_UC_CODE + " TEXT"
            + " );")

    const val SQL_CREATE_UC = ("CREATE TABLE " + TableUCs.TABLE_NAME + "("
            + TableUCs.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TableUCs.COLUMN_UC_CODE + " TEXT,"
            + TableUCs.COLUMN_UC_NAME + " TEXT"
            + " );")

    const val SQL_CREATE_VILLAGES = ("CREATE TABLE " + TableVillages.TABLE_NAME + "("
            + TableVillages.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TableVillages.COLUMN_UC_CODE + " TEXT,"
            + TableVillages.COLUMN_VILLAGE_CODE + " TEXT,"
            + TableVillages.COLUMN_VILLAGE_NAME + " TEXT"
            + " );")
}
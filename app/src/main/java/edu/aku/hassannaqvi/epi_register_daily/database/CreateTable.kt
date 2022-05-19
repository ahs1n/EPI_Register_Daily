package edu.aku.hassannaqvi.epi_register_daily.database

import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.*
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp.PROJECT_NAME

object CreateTable {

    const val DATABASE_NAME = "$PROJECT_NAME.db"
    const val DATABASE_COPY = "${PROJECT_NAME}_copy.db"
    const val DATABASE_VERSION = 1

    const val SQL_CREATE_FORMS = ("CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsTable.COLUMN_UID + " TEXT,"
            + FormsTable.COLUMN_SNO + " TEXT,"
            + FormsTable.COLUMN_USERNAME + " TEXT,"
            + FormsTable.COLUMN_SYSDATE + " TEXT,"
            + FormsTable.COLUMN_ISTATUS + " TEXT,"
            + FormsTable.COLUMN_DEVICEID + " TEXT,"
            + FormsTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsTable.COLUMN_SYNCED + " TEXT,"
            + FormsTable.COLUMN_SYNC_DATE + " TEXT,"
            + FormsTable.COLUMN_APPVERSION + " TEXT,"
            + FormsTable.COLUMN_VA + " TEXT"
            + " );"
            )

    const val SQL_CREATE_FORMSVB = ("CREATE TABLE "
            + FormsVBTable.TABLE_NAME + "("
            + FormsVBTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + FormsVBTable.COLUMN_PROJECT_NAME + " TEXT,"
            + FormsVBTable.COLUMN_UID + " TEXT,"
            + FormsVBTable.COLUMN_SNO + " TEXT,"
            + FormsVBTable.COLUMN_USERNAME + " TEXT,"
            + FormsVBTable.COLUMN_SYSDATE + " TEXT,"
            + FormsVBTable.COLUMN_ISTATUS + " TEXT,"
            + FormsVBTable.COLUMN_DEVICEID + " TEXT,"
            + FormsVBTable.COLUMN_DEVICETAGID + " TEXT,"
            + FormsVBTable.COLUMN_SYNCED + " TEXT,"
            + FormsVBTable.COLUMN_SYNC_DATE + " TEXT,"
            + FormsVBTable.COLUMN_APPVERSION + " TEXT,"
            + FormsVBTable.COLUMN_VB + " TEXT"
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
            + UsersTable.COLUMN_FULLNAME + " TEXT"
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
}
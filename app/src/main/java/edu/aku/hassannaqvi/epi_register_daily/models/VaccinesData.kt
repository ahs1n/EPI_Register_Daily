package edu.aku.hassannaqvi.epi_register_daily.models

import android.database.Cursor
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.TableVaccinesData
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp._EMPTY_
import org.json.JSONObject

/**
 * Created by hussain.siddiqui on 10/06/2022.
 */

class VaccinesData {
    var ucCode: String = _EMPTY_
    var aID: String = _EMPTY_
    var uID: String = _EMPTY_
    var uUID: String = _EMPTY_
    var villageCode: String = _EMPTY_
    var villageName: String = _EMPTY_
    var facilityCode: String = _EMPTY_
    var vBO2: String = _EMPTY_
    var vB04: String = _EMPTY_
    var vB04A: String = _EMPTY_
    var vB08CC0DE: String = _EMPTY_
    var vB08CANT: String = _EMPTY_
    var vB08CDT: String = _EMPTY_
    var vB08WC0DE: String = _EMPTY_
    var vB08WANT: String = _EMPTY_
    var vB08WDT: String = _EMPTY_

    fun sync(jsonObject: JSONObject): VaccinesData {
        ucCode = jsonObject.getString(TableVaccinesData.COLUMN_UC_CODE)
        aID = jsonObject.getString(TableVaccinesData.COLUMN_AID)
        uID = jsonObject.getString(TableVaccinesData.COLUMN_UID)
        uUID = jsonObject.getString(TableVaccinesData.COLUMN_UUID)
        villageCode = jsonObject.getString(TableVaccinesData.COLUMN_VILLAGE_CODE)
        //villageName = jsonObject.getString(TableVaccinesData.COLUMN_VILLAGE_NAME)
        facilityCode = jsonObject.getString(TableVaccinesData.COLUMN_FACILITY_CODE)
        vBO2 = jsonObject.getString(TableVaccinesData.COLUMN_VB02)
        vB04 = jsonObject.getString(TableVaccinesData.COLUMN_VB04)
        vB04A = jsonObject.getString(TableVaccinesData.COLUMN_VB04A)
        vB08CC0DE = jsonObject.getString(TableVaccinesData.COLUMN_VB08C_CODE)
        vB08CANT = jsonObject.getString(TableVaccinesData.COLUMN_VB08C_ANT)
        vB08CDT = jsonObject.getString(TableVaccinesData.COLUMN_VB08C_DT)
        vB08WC0DE = jsonObject.getString(TableVaccinesData.COLUMN_VB08W_CODE)
        vB08WANT = jsonObject.getString(TableVaccinesData.COLUMN_VB08W_ANT)
        vB08WDT = jsonObject.getString(TableVaccinesData.COLUMN_VB08W_DT)
        return this
    }

    fun hydrate(cursor: Cursor): VaccinesData {
        ucCode =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_UC_CODE))
        aID =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_AID))
        uID =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_UID))
        uUID =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_UUID))
        villageCode =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VILLAGE_CODE))
        facilityCode =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_FACILITY_CODE))
        villageName =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VILLAGE_NAME))
        vBO2 =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB02))
        vB04 =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB04))
        vB04A =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB04A))
        vB08CC0DE =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB08C_CODE))
        vB08CANT =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB08C_ANT))
        vB08CDT =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB08C_DT))
        vB08WC0DE =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB08W_CODE))
        vB08WANT =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB08W_ANT))
        vB08WDT =
            cursor.getString(cursor.getColumnIndexOrThrow(TableVaccinesData.COLUMN_VB08W_DT))

        return this
    }





}
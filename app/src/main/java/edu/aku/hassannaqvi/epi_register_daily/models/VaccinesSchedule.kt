package edu.aku.hassannaqvi.epi_register_daily.models

import android.database.Cursor
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.VaccineSchedule
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp._EMPTY_
import org.json.JSONObject

/**
 * Created by hussain.siddiqui on 10/06/2022.
 */

class VaccinesSchedule {

    var vname: String = _EMPTY_
    var vgroup: String = _EMPTY_
    var bydob: String = _EMPTY_
    var byprvdoze: String = _EMPTY_
    var comments: String = _EMPTY_


    fun sync(jsonObject: JSONObject): VaccinesSchedule {
        vname = jsonObject.getString(VaccineSchedule.COLUMN_VNAME)
        vgroup = jsonObject.getString(VaccineSchedule.COLUMN_VGROUP)
        bydob = jsonObject.getString(VaccineSchedule.COLUMN_BYDOB)
        byprvdoze = jsonObject.getString(VaccineSchedule.COLUMN_BYPRVDOZE)
        comments = jsonObject.getString(VaccineSchedule.COLUMN_COMMENTS)

        return this
    }

    fun hydrate(cursor: Cursor): VaccinesSchedule {
        vname =
            cursor.getString(cursor.getColumnIndexOrThrow(VaccineSchedule.COLUMN_VNAME))
        vgroup =
            cursor.getString(cursor.getColumnIndexOrThrow(VaccineSchedule.COLUMN_VGROUP))
        bydob =
            cursor.getString(cursor.getColumnIndexOrThrow(VaccineSchedule.COLUMN_BYDOB))
        byprvdoze =
            cursor.getString(cursor.getColumnIndexOrThrow(VaccineSchedule.COLUMN_BYPRVDOZE))
        comments =
            cursor.getString(cursor.getColumnIndexOrThrow(VaccineSchedule.COLUMN_COMMENTS))

        return this
    }





}
package edu.aku.hassannaqvi.epi_register_daily.models

import android.database.Cursor
import edu.aku.hassannaqvi.epi_register_daily.contracts.TableContracts.TableUCs
import edu.aku.hassannaqvi.epi_register_daily.core.MainApp._EMPTY_
import org.json.JSONObject

/**
 * Created by hussain.siddiqui on 10/06/2022.
 */

class UCs {
    var ucCode: String = _EMPTY_
    var ucName: String = _EMPTY_

    fun sync(jsonObject: JSONObject): UCs {
        ucCode = jsonObject.getString(TableUCs.COLUMN_UC_CODE)
        ucName = jsonObject.getString(TableUCs.COLUMN_UC_NAME)
        return this
    }

    fun hydrate(cursor: Cursor): UCs {
        ucCode =
            cursor.getString(cursor.getColumnIndexOrThrow(TableUCs.COLUMN_UC_CODE))
        ucName =
            cursor.getString(cursor.getColumnIndexOrThrow(TableUCs.COLUMN_UC_NAME))

        return this
    }


}
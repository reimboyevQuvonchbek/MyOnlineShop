package uz.quvonchbek.myonlineshop.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor


class MyShared {
    var KEY_1 = "key_1"
    var activeUser: String?
        get() = sharedPreferences2!!.getString(KEY_1, "")
        set(str) {
            editor2 = sharedPreferences2?.edit()
            editor2?.putString(KEY_1, str)
            editor2?.commit()
        }

    companion object {
        var myshared2 = MyShared()
        var sharedPreferences2: SharedPreferences? = null
        var editor2: Editor? = null
        fun getInstance(context: Context): MyShared {
            if (sharedPreferences2 == null) {
                sharedPreferences2 = context.getSharedPreferences("datalist", Context.MODE_PRIVATE)
            }
            return myshared2
        }
        fun clearkesh(){
            editor2?.clear()
        }

    }
}
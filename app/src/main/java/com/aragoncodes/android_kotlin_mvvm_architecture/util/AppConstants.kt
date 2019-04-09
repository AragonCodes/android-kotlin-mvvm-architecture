package com.aragoncodes.android_kotlin_mvvm_architecture.util

import com.aragoncodes.android_kotlin_mvvm_architecture.R

object AppConstants {

    // Config
    val API_STATUS_CODE_LOCAL_ERROR = 0
    val DB_NAME = "AppLocalDb"
    val NULL_INDEX = -1L
    val PREF_NAME = "AppPref"
    val SEED_DATABASE_CUSTOMS = "seed/customs.json"
    val STATUS_CODE_FAILED = "failed"
    val STATUS_CODE_SUCCESS = "success"
    val TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss"

    // Strings
    const val PENDING : String = "Pending"
    const val ACCEPTED : String = "Accepted"
    const val CANCELED : String = "Canceled"
    const val DECLINED : String = "Declined"
    const val ONGOING : String = "Ongoing"
    const val DONE : String = "Done"

    // Arrays
    val MONTH_NAME : Array<Int> = arrayOf(
        R.string.month_01, R.string.month_02, R.string.month_03, R.string.month_04,
        R.string.month_05, R.string.month_06, R.string.month_07, R.string.month_08,
        R.string.month_09, R.string.month_10, R.string.month_11, R.string.month_12
    )

    // Mutable Maps
    val USER_TYPE_MAP : MutableMap<Int, String?> = mutableMapOf (
        R.string.user_type_1 to "1",
        R.string.user_type_2 to "2"
    )

    // Reversed Maps
    val USER_TYPE_REVERSED_MAP : Map<String?, Int> = USER_TYPE_MAP.entries.associate{(k,v)-> v to k}

} // This utility class is not publicly instantiable
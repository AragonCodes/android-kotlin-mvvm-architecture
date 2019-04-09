package com.aragoncodes.android_kotlin_mvvm_architecture.util

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.util.Log
import android.util.Patterns
import com.aragoncodes.android_kotlin_mvvm_architecture.R
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseRelation
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

object CommonUtils {

    val timestamp: String
        get() = SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())

    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun formatStringToDouble(num: String): Double {
        return when (num!!.contains(",")){
            true -> num.replace(",",".").toDouble()
            false -> num.toDouble()
        }
    }

    fun getFieldValuesFromParseArray(array: List<ParseObject>, field: String): String? {
        var tempString = ""

        if (array.isNotEmpty()) {
            array.forEach {
                tempString += it.getString(field)+", "
            }
            return tempString.removeSuffix(", ")
        }
        return tempString
    }

    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        // validate email and password
        if (email.isNullOrBlank()) {
            return false
        }
        if (!CommonUtils.isEmailValid(email)) {
            return false
        }
        return (!password.isNullOrBlank())
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
        val manager = context.assets
        val fileIs = manager.open(jsonFileName)

        val size = fileIs.available()
        val buffer = ByteArray(size)
        fileIs.read(buffer)
        fileIs.close()

        return String(buffer, Charset.defaultCharset())
    }

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)

        var len = inputStream.read(buffer)
        while ((len) != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }

    fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 85, stream)
        return stream.toByteArray()
    }

    fun logParseObject(parseObject: ParseObject) {
        try {
            val jsonObject = parseObjectToJson(parseObject)
            Log.d("TAG", jsonObject.toString(4))
        } catch (e: ParseException) {
            Log.e("TAG", e.message)
        } catch (e: JSONException) {
            Log.e("TAG", e.message)
        }
    }

    @Throws(ParseException::class, JSONException::class)
    private fun parseObjectToJson(parseObject: ParseObject): JSONObject {
        val jsonObject = JSONObject()
        parseObject.fetchIfNeeded<ParseObject>()
        val keys = parseObject.keySet()
        for (key in keys) {
            val objectValue = parseObject.get(key)
            when (objectValue) {
                is ParseObject -> jsonObject.put(key, parseObjectToJson(parseObject.getParseObject(key)))
                // keep in mind about "pointer" to it self, will gain stackoverlow
                is ParseRelation<*> -> {
                    // handle relation
                }
                else -> jsonObject.put(key, objectValue.toString())
            }
        }
        return jsonObject
    }

} // This utility class is not publicly instantiable
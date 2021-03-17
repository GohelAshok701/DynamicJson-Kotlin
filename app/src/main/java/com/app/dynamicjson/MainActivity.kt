package com.app.dynamicjson

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val resultList: MutableList<TestResponse.Result> = mutableListOf()
    var dynamicJson =
        "{\"report\": {\"data\": {\"results\": {\"558952cca6d73d7d81c2eb9d\": {\"Max\": -1,\"Min\": -1,\"Slope\": -1},\"558ce148a6d73d7d81c2fa8a\": {\"Max\": -2,\"Min\": -1,\"Slope\": -2}}}}}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = JSONObject(dynamicJson.replace("\\", ""))
        val jsonObjectReport = jsonString.getJSONObject("report")
        val jsonObjectData = jsonObjectReport.getJSONObject("data")
        val jsonOjectResult = jsonObjectData.getJSONObject("results")

        val it: Iterator<String> = jsonOjectResult.keys()
        while (it.hasNext()) {
            val key = it.next()
            try {
                if (jsonOjectResult.get(key) is JSONObject) {
                    val dynamicObjectKey: JSONObject = jsonOjectResult.getJSONObject(key)
                    val gson = Gson()
                    val result: TestResponse.Result = gson.fromJson(
                        dynamicObjectKey.toString(),
                        TestResponse.Result::class.java
                    )
                    resultList.add(result)
                } else {
                    println(key + ":" + jsonOjectResult.getString(key))
                }
            } catch (e: Throwable) {
                try {
                    println(key + ":" + jsonOjectResult.getString(key))
                } catch (ee: Exception) {
                }
                e.printStackTrace()
            }
        }

        Log.d("main", "onCreate:" + resultList.toString())
    }
}
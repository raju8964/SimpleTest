package com.cord.simpletest.utils


import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONException


class SharedPref private constructor(){
    private var editor:SharedPreferences.Editor?=null
    companion object{
        private var sharedPref: SharedPref?=null
        private  var pref:SharedPreferences?=null
        fun getInstance(context:Context): SharedPref {
            if (pref ==null){
                synchronized(this){
                    if (pref ==null){
                        sharedPref = SharedPref()
                        pref =context.getSharedPreferences(Common.SharedPref_Table,Context.MODE_PRIVATE)
                    }
                }
            }
            return sharedPref!!
        }
    }
    /// save array
    fun saveArray(key:String,list:JSONArray){
        val gson = Gson()
        try {  val textList: JSONArray = list
            val jsonText = gson.toJson(textList)
            if (editor!=null) {
                editor!!.putString(key, jsonText)
                editor!!.apply()
            }
        } catch (error:JSONException){
            Log.e("sdjkbvk",  " error ${error.toString()}")
        }}
    //// save data
    fun saveData(key:String?,value:String?){
        editor= pref!!.edit()
        editor!!.putString(key,value).apply()
        editor!!.commit()
    }
    /// getData
    fun getData(key:String):String{
        var value=""
        value= pref!!.getString(key,"")!!
        return value
    }
    //// clear data
    fun clearData(){
        if (editor!=null){
            editor!!.clear().commit()
        }}
    //// remove data
    fun removeData(value:String) {
        if (editor != null) {
            editor!!.remove(value)
            editor!!.commit();
        }
    }


}
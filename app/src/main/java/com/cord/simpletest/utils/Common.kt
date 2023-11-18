package com.cord.simpletest.utils


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.cord.simpletest.R

import com.google.gson.Gson
import org.json.JSONObject


class Common {
    companion object {
        const val USERNAME = "username"
        const val UserId = "userid"
        const val Email = "email"
        const val FNAME = "f_name"
        const val LNAME = "l_name"
        const val TR_PIN = "tr_pin"
        const val parent_customer_id = "parent_customer_id"
        const val CUSTOMERID = "customer_id"
        const val DR_CUSTOMERID = "direct_customer_id"
        const val direct_customer_id = "direct_customer_id"
        const val POSITION = "position"
        const val PHONE = "phone"
        const val bsacode = "bsacode"
        const val RANK = "rank"
        const val percentage = "percentage"
        const val gender = "gender"
        const val dob = "dob"
        const val ADDRESS = "address"
        const val CITY = "city"
        const val STATE = "state"
        const val COUNTRY = "country"
        const val BEARER_TOKEN = "bearertoken"
        const val URLs = "url"


        const val RDATE = "rdate"
        const val TOKEN = "token"
        const val PINCODE = "pincode"
        const val SLIDERARR = "bannerlist"
        const val PRODUCTSARR = "products"
        const val LATESTPRODUCTSARR = "latest_products"
        const val LOWESTPRODUCTLIST = "lowest_price_products"
        const val CATEGORYARR = "categories"
        const val WEBSTOREARR = "webStores"


        const val BOOKINGID_ARRAY = "booking_id_array"
        const val DEVICEIP = "deviceip"

        const val UID = "Uid"
        const val Fuid = "fuid"
        const val PASSWORD = "password"
        const val Address = "address"
        const val DEVICEID = "deviceid"
        const val Contact = "contact"
        const val CustomerId = "customerid"
        const val SharedPref_Table = "SPTable"
        const val TYPE = "type"
        const val TOKEN_TYPE = "token_type"
        const val ACCESS_TOKEN = "access_token"
        const val PROFILE_PIC = "profile_pic"
        const val isLogin = "userlogin"
        const val BOOKINGID = "booking_id"
        const val BOOKINGID_LOGIN = "booking_id_login"
        const val NOTIF_COUNT = "notification_count"
        const val CURRENCY = "currency"
        const val FLEET_UNIT = "fleet_unit"
        const val CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
        const val DATABASE_NAME = "CBDMOVERS"
        const val DATABASE_VERSION = 2
        const val ADDTOCART = "addtocart"

        ///// table name
        const val dtRegister = "Register"
        const val dtLogin = "Login"
        const val dtBookToMove = "Booktomove"
        const val ScreenName = "screenname"


        /////// progress bar
        fun progressDialog(context: Context): Dialog {
            val dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog_ui, null)

            val width = context.resources.displayMetrics.widthPixels
            val height = dialog.window!!.attributes.height
            dialog.window!!.setLayout(width, height)
            dialog.setContentView(inflate)
            dialog.setCancelable(true)
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
            var gif = dialog.findViewById(R.id.gif_icon) as ImageView
            gif.visibility = View.GONE

            dialog.setOnCancelListener {
                Log.e("dajkcnkadn", "Hello ${context.javaClass.name}")
                if ((context).javaClass.name.contains("MainActivity")) {

                } else {
                    (context as Activity).finish()
                }
            }
            return dialog

        }

        //// email verification
        fun isValidEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }


        ///// save login data into sharedpref
        fun mloginData(context: Context, obj: JSONObject) {
            SharedPref.getInstance(context).apply {
                obj.getJSONObject("data").apply {
                    saveData(Email, getString("email"))
                    saveData(UserId, getString("id"))
                    saveData(ACCESS_TOKEN, getString("access_token") + "")
                    saveData(USERNAME, getString("name") + "")
                    saveData(TYPE, getString("device_type") + "")
                    saveData(PHONE, getString("phone") + "")
                    saveData(COUNTRY, getString("country") + "")
                    saveData(isLogin, "true")

                }
                ///// save booking array to display booking status on booking status screen
                val gson = Gson()
                val jsonText = gson.toJson(obj.getJSONArray("Bookings"))
                saveData(BOOKINGID_ARRAY, jsonText)
                saveData(BOOKINGID, obj.getJSONArray("Bookings").getString(0) + "")
            }
        }

    }}


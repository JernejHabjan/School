package si.roglan.EMP_Seminarska

import android.app.Activity
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import org.json.JSONException
import org.json.JSONObject
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class VolleyHelper {
    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"


    fun getUsername(activity: Activity, usernameText: EditText, googleID: String){
        val service = "/ServicePersonData.svc"
        val operationContract = "/User/" + googleID

        val requestURL = SERVER_URL + service + operationContract;
        val strReq = JsonObjectRequest(Request.Method.GET, requestURL,
            Response.Listener { response ->
                if (response != null) {
                    try {
                        if(response.getString("name") != "null"){
                            val username = response.getString("name");
                            Log.e("Volley", "Set username to " + username)
                            usernameText.setText(username, TextView.BufferType.EDITABLE);
                        }
                    } catch (e: JSONException) {
                        //Unable to retreive username -> use google's instead
                        Log.e("Volley", "Invalid JSON Object.")
                    }
                } else {
                    Log.e("Volley", "No responses found")
                }
            },

            Response.ErrorListener { error ->
                Log.e("Volley", "Error while calling REST API.")
                Log.e("Volley", error.toString())
            }
        )

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(strReq)
    }

    fun AddUserIfNotInDatabase(activity: Activity, account: GoogleSignInAccount) {
        val service = "/ServicePersonData.svc"
        val operationContract = "/User"
        val personID = "/" + account.id.toString()

        val requestURL = SERVER_URL + service + operationContract + personID;
        val strReq = JsonObjectRequest(Request.Method.GET, requestURL,
                Response.Listener { response ->
                    if (response != null) {
                        try {
                            if(response.getString("googleID") != "null"){
                                val googleID = response.getString("googleID");
                                Log.e("Volley", "CONTAINS IN DATABASE -> ID: " + googleID)
                            }
                            else{
                                Log.e("Volley", "USER NOT FOUND IN DATABASE: Added '" +
                                        account.displayName + "'");
                                this.addUser(activity, account)
                            }
                        } catch (e: JSONException) {
                            Log.e("Volley", "Invalid JSON Object.")
                        }
                    } else {
                        Log.e("Volley", "No responses found")
                    }
                },

                Response.ErrorListener { error ->
                    Log.e("Volley", "Error while calling REST API.")
                    Log.e("Volley", error.toString())
                }
        )

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(strReq)
    }


    fun writeRequest(params: JSONObject, service: String, operationContract: String): JsonObjectRequest {
        val req = JsonObjectRequest(Request.Method.POST, SERVER_URL + service + operationContract, params, // not tested but this should send json object
                Response.Listener<JSONObject> { response ->
                    try {
                        VolleyLog.v("Response:%n %s", response.toString(4))
                        Log.d("VOLLEY RESPONSE", response.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { error -> VolleyLog.e("Error: ", error.message) })
        return req
    }


    fun addUser(activity: Activity, account: GoogleSignInAccount) {
        val params = JSONObject()
        params.put("roleID", "1")
        params.put("googleID", account.id.toString())
        params.put("name", account.displayName.toString())
        params.put("email", account.email.toString())


        val service = "/ServicePersonData.svc"
        val operationContract = "/User"

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(writeRequest(params, service, operationContract))
        Log.e("Volley", "Added User")
    }


    fun updateUser(activity: Activity, googleID: String,
                   name: String = "", roleID: Int = 0, email: String = "")
    {
        val params = JSONObject()
        params.put("roleID", "0")
        params.put("googleID", googleID)
        params.put("name", name)
        params.put("email",email)


        val service = "/ServicePersonData.svc"
        val operationContract = "/UpdateUser"

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(writeRequest(params, service, operationContract))
        Log.e("Volley", "Updated User")
    }


    fun getPassengerData(activity: Activity, passengerData: ArrayList<String>, orderId: Int){
        val service = "/ServicePersonData.svc"
        val operationContract = "/Passengers/" + orderId;
        val requestURL = SERVER_URL + service + operationContract;

        val strReq = JsonArrayRequest(Request.Method.GET, requestURL,
            Response.Listener { response ->
                //val passengerData: ArrayList<String> = ArrayList<String>()
                if (response != null) {
                    passengerData.clear()
                    for (i in 0 until response.length()) {
                        try {
                            val jsonObject = response.getJSONObject(i)

                            passengerData.add(jsonObject.getString("name"))
                            passengerData.add(jsonObject.getString("surname"))
                            passengerData.add(jsonObject.getString("gender"))

                            val c = Calendar.getInstance();
                            c.add(Calendar.YEAR, -jsonObject.getString("age").toInt())
                            passengerData.add(SimpleDateFormat("dd.MM.yyyy").format(c.time))

                        } catch (e: JSONException) {
                            // If there is an error then output this to the logs.
                            Log.e("Volley", "Invalid JSON Object.")
                        }
                    }
                } else {
                    Log.e("Volley", "No responses found")
                }
            },

            Response.ErrorListener { error ->
                // If there a HTTP error then add a note to our repo list.
                Log.e("Volley", "Error while calling REST API.")
                Log.e("Volley", error.toString())
            }
        )

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(strReq)
    }


    fun reformatDate(date: String): String{
        val dateArray = date.split('.')
        if(dateArray.size != 3)return date

        val newDate = dateArray[1] + "." + dateArray[0] + "." + dateArray[2]
        return newDate
    }

    fun addTravel(activity: Activity, googleID: String, price: Float, discount: Float,
                  nakupData: ArrayList<String>, passengerData: ArrayList<String>)
    {
        Log.i("PASSENGERS", "NUM_PASSENGERS: " + passengerData.size/4)

        val params = JSONObject()
        params.put("googleID", googleID)
        params.put("planeName", "Default_Plane_Name")
        params.put("planeCompany", "Default_Plane_Company")
        params.put("fromLocation", nakupData!![0])
        params.put("toLocation", nakupData!![1])
        params.put("departureDate", reformatDate(nakupData!![2]))
        params.put("departureClass", nakupData!![3])
        params.put("price", price.toString())
        params.put("discount", discount.toString())

        //Has return flight
        if (nakupData!!.size > 4) {
            params.put("returnDate", reformatDate(nakupData!![4]))
            params.put("returnClass", nakupData!![5])
        }else{
            params.put("returnDate", "12.31.9999")
            params.put("returnClass", "-")
        }

        val copyPassengers = ArrayList<String>(passengerData)

        //Convert date of birth to "almost" accurate age
        var i = 0
        val thisYear = Calendar.getInstance().get(Calendar.YEAR)
        while(i < copyPassengers.size){
            val year = copyPassengers[i + 3].split('.')[2].toInt()
            val age = (thisYear - year).toString()
            copyPassengers[i + 3] = age

            i += 4
        }

        params.put("passengerData", copyPassengers)

        //TODO convert date to age
        //params.put("passengerData", "")//passengerData

        Log.i("Travel PARAMETERS", params.toString())

        val service = "/ServiceTravelData.svc"
        val operationContract = "/Travel"

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(writeRequest(params, service, operationContract))
        Log.i("Volley", "Added travel entry")
    }

    fun getTravels(travelsFragment: TravelsFragment, googleID: String): ArrayList<TravelData>{
        val travels: ArrayList<TravelData> = ArrayList<TravelData>()

        val service = "/ServiceTravelData.svc"
        val operationContract = "/Travels/" + googleID;
        val requestURL = SERVER_URL + service + operationContract;

        val strReq = JsonArrayRequest(Request.Method.GET, requestURL,
                Response.Listener { response ->
                    if (response != null) {
                        travelsFragment.mTravelsData.clear()

                        for (i in 0 until response.length()) {
                            try {
                                val jsonObject = response.getJSONObject(i)
                                //Log.i("JSON", jsonObject.toString())

                                val travelData = TravelData()
                                travelData.mClass = ""
                                travelData.mReturnClass = ""
                                travelData.mPrice = jsonObject.getString("initialPrice").toFloat()
                                travelData.mOrderId = jsonObject.getString("orderID").toInt();
                                travelData.mFromLocation = jsonObject.getString("fromLocationName")
                                travelData.mToLocation = jsonObject.getString("toLocationName")
                                travelData.mDate = jsonObject.getString("initialDate")
                                travelData.mReturnDate = jsonObject.getString("returnDate")

                                travelData.mDate = travelData.mDate.split(" ")[0].replace('/', '.');
                                travelData.mReturnDate = travelData.mReturnDate.split(" ")[0].replace('/', '.');

                                travelData.mDate = reformatDate(travelData.mDate)
                                travelData.mReturnDate = reformatDate(travelData.mReturnDate)

                                //If invalid date -> no return date
                                if(travelData.mReturnDate.split('.')[2] == "9999"){
                                    travelData.mReturnDate = "";
                                    travelData.mReturnClass = "";
                                }

                                travelsFragment.mTravelsData.add(travelData)

                            } catch (e: JSONException) {
                                Log.e("Volley", "Invalid JSON Object.")
                            }
                        }

                    } else {
                        Log.e("Volley", "No responses found")
                    }

                    travelsFragment.updateTravelsList(travelsFragment.view)
                },

                Response.ErrorListener { error ->
                    // If there a HTTP error then add a note to our repo list.
                    Log.e("Volley", "Error while calling REST API.")
                    Log.e("Volley", error.toString())
                }
        )

        val requestQueue = Volley.newRequestQueue(travelsFragment.activity)
        requestQueue.add(strReq)

        return travels
    }

    fun removeOrder(view: View?, activity: Activity, orderID: Int){
        val service = "/ServiceTravelData.svc"
        val operationContract = "/Order/" + orderID
        val url = SERVER_URL + service + operationContract

        val deleteRequest = StringRequest(Request.Method.DELETE, url,
                Response.Listener { response ->
                    Snackbar.make(view!!, "Potovanje '" + orderID + "' ste uspešno odstranili",
                            Snackbar.LENGTH_LONG).show()
                },
                Response.ErrorListener {
                    // error.
                }
        )

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(deleteRequest)

        Log.e("Volley", "Removed order id '" + orderID + "'")
    }

}





























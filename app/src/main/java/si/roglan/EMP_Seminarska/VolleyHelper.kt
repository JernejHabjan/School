package si.roglan.EMP_Seminarska

import android.app.Activity
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


class VolleyHelper {
    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"


    fun AddUserIfNotInDatabase(activity: Activity, account: GoogleSignInAccount) {
        val requestQueue = Volley.newRequestQueue(activity)

        // VOLLEY GET PERSON
        val service = "/ServicePersonData.svc"
        val operationContract = "/User"
        val personID = "/" + account.id.toString()


        val requestURL = SERVER_URL + service + operationContract + personID;
        val strReq = JsonObjectRequest(Request.Method.GET, requestURL,
                Response.Listener { response ->
                    // Check the length of our response (to see if the user has any repos)
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
                            // If there is an error then output this to the logs.
                            Log.e("Volley", "Invalid JSON Object.")
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

    fun renameUser(activity: Activity, googleID: String, username: String){
        val service = "/ServicePersonData.svc"
        val operationContract = "/RenameUser"

        val params = JSONObject()
        params.put("googleID", googleID)
        params.put("username", username)

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(writeRequest(params, service, operationContract))
        Log.e("Volley", "Renamed user")
    }

    fun getPassengerData(activity: Activity, orderId: Int): ArrayList<String> {
        val passengerData: ArrayList<String> = ArrayList<String>()

        val service = "/ServiceTravelData.svc"
        val operationContract = "/Passengers/" + orderId;
        val requestURL = SERVER_URL + service + operationContract;

        val strReq = JsonObjectRequest(Request.Method.GET, requestURL,
                Response.Listener { response ->
                    if (response != null) {
                        try {
                            //passengerData
                            Log.e("Volley", response.toString());
                        } catch (e: JSONException) {
                            // If there is an error then output this to the logs.
                            Log.e("Volley", "Invalid JSON Object.")
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

        return passengerData
    }

    fun addTravel(activity: Activity, nakupData: ArrayList<String>, passengerData: ArrayList<String>){
        Log.i("PASSENGERS", "NUM_PASSENGERS: " + passengerData.size)

        val service = "/ServiceTravelData.svc"
        val operationContract = "/Travel"

        val params = JSONObject()
        params.put("passengerData", passengerData)
        params.put("departureLocation", nakupData!![0])
        params.put("arrivalLocation", nakupData!![1])
        params.put("departureDate", nakupData!![2])
        params.put("departureClass", nakupData!![3])

        //Has return flight
        if (nakupData!!.size > 4) {
            params.put("returnDate", nakupData!![4])
            params.put("returnClass", nakupData!![5])
        }

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(writeRequest(params, service, operationContract))
        Log.e("Volley", "Added travel entry")
    }

    fun getTravels(activity: Activity, googleID: String): ArrayList<TravelData>{
        val travels: ArrayList<TravelData> = ArrayList<TravelData>()

        val service = "/ServiceTravelData.svc"
        val operationContract = "/Travels/" + googleID;
        val requestURL = SERVER_URL + service + operationContract;

        val strReq = JsonObjectRequest(Request.Method.GET, requestURL,
                Response.Listener { response ->
                    if (response != null) {
                        for (i in 0 until response.length()) {
                            try {
                                val jsonObj = response.getJSONObject(i.toString())

                            } catch (e: JSONException) {
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


        //TODO TMP REMOVE
        val tmpTravel: TravelData = TravelData()
        tmpTravel.mOrderId=5
        tmpTravel.mFromLocation="Ljubljana, Slovenia"
        tmpTravel.mToLocation="New York, USA"
        tmpTravel.mDate="1.1.1970"
        tmpTravel.mClass="First"
        tmpTravel.mReturnDate="1.1.1980"
        tmpTravel.mReturnClass="First"
        travels.add(tmpTravel)

        return travels
    }

    fun removeOrder(activity: Activity, orderID: Int){
        val service = "/ServiceTravelData.svc"
        val operationContract = "/RemoveOrer"

        val params = JSONObject()
        params.put("orderID", orderID)

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(writeRequest(params, service, operationContract))
        Log.e("Volley", "Removed order")
    }

}





























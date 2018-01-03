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


    fun addPassengers(activity: Activity, passengerData: ArrayList<String>){
        Log.i("PASSENGERS", "NUM_PASSENGERS: " + passengerData.size)

        val params = JSONObject()
        params.put("roleID", passengerData)

        val service = "/ServiceTravelData.svc"
        val operationContract = "/Passengers"
        val flightID = "/" + "1"

        val requestURL = SERVER_URL + service + operationContract + flightID;
        val strReq = JsonObjectRequest(Request.Method.GET, requestURL,
                Response.Listener { response ->
                    if (response != null) {
                        try {
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
    }

    fun addTravel(activity: Activity, nakupData: ArrayList<String>){
        val params = JSONObject()

        val lokacija_odhoda = nakupData!![0]
        val lokacija_prihoda = nakupData!![1]
        val datum_odhoda = nakupData!![2]
        val razred_odhoda = nakupData!![3]

        print("ODHOD: " + lokacija_odhoda + " PRIHOD: " + lokacija_prihoda);
        print("DATUM_ODHODA: " + datum_odhoda)
        print("RAZRED_ODHODA: " + razred_odhoda)


       /*
            "Prvi" -> factor *= 2.0f
            "Poslovni" -> factor *= 1.5f
            "Ekonomski" -> factor *= 1.2f
        */

        //Has return flight
        if (nakupData!!.size > 4) {
            val datum_prihoda = nakupData!![4]
            val razred_prihoda = nakupData!![5]
        }


        val service = "/ServiceTravelData.svc"
        val operationContract = "/Travel"


        //val requestQueue = Volley.newRequestQueue(activity)
        //requestQueue.add(writeRequest(params, service, operationContract))
        //Log.e("Volley", "Added travel entry")
    }

}

package si.roglan.EMP_Seminarska

import android.app.Activity
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import org.json.JSONException
import org.json.JSONObject

class VolleyHelper {
    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"


    fun doesDatabaseContainGID(activity: Activity, GID: String): Boolean {

        val requestQueue = Volley.newRequestQueue(activity)


        // VOLLEY GET PERSON
        val service = "/ServicePersonData.svc"
        val operationContract = "/GetUser"
        val personID = "/" + GID

        var contains_local = false

        val strReq = StringRequest(Request.Method.GET, SERVER_URL + service + operationContract + personID,
                Response.Listener { response ->
                    // Check the length of our response (to see if the user has any repos)
                    if (response != null) {
                        try {
                            contains_local = true

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
        return contains_local
    }


    fun writeRequest(params: HashMap<String, String>, service: String, operationContract: String): JsonObjectRequest {

        val req = JsonObjectRequest(SERVER_URL + service + operationContract, JSONObject(params), // not tested but this should send json object
                Response.Listener<JSONObject> { response ->
                    try {
                        VolleyLog.v("Response:%n %s", response.toString(4))
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { error -> VolleyLog.e("Error: ", error.message) })
        return req
    }

    fun addUser(activity: Activity, account: GoogleSignInAccount) {
        val params = HashMap<String, String>()
        params.put("GoogleID", account.id.toString())
        params.put("name", account.displayName.toString())
        params.put("email", account.email.toString())
        params.put("email", account.email.toString())

        val service = "/ServicePersonData.svc"
        val operationContract = "/AddUser"


        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(writeRequest(params, service, operationContract))
    }


}

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


class VolleyHelper {
    private var SERVER_URL = "http://asistentslivko.azurewebsites.net"


    fun AddUserIfNotInDatabase(activity: Activity, account: GoogleSignInAccount) {

        val requestQueue = Volley.newRequestQueue(activity)


        // VOLLEY GET PERSON
        val service = "/ServicePersonData.svc"
        val operationContract = "/User"
        val personID = "/" + account.id.toString()



        val strReq = JsonObjectRequest(Request.Method.GET, SERVER_URL + service + operationContract + personID,
                Response.Listener { response ->
                    // Check the length of our response (to see if the user has any repos)
                    if (response != null) {
                        try {
                            if(response.getString("googleID") != "null"){

                                Log.e("Volley", response.getString("googleID"))

                                Log.e("Volley", "CONTAINS IN DATABASE")
                            }
                            else{
                                Log.e("Volley", "DOESNT CONTAIN IN DATABASE")


                                    VolleyHelper().addUser(activity, account)
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


}

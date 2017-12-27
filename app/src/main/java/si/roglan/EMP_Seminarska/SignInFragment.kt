package si.roglan.EMP_Seminarska


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task


class SignInFragment : Fragment(), View.OnClickListener {


    private val TAG = "SignInActivity"
    private val RC_SIGN_IN = 9001
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var mStatusTextView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_sign_in, container, false)

        // Views
        mStatusTextView = view!!.findViewById(R.id.status) as TextView?

        // Button listeners
        view.findViewById(R.id.sign_in_button).setOnClickListener(this)
        view.findViewById(R.id.sign_out_button).setOnClickListener(this)
        view.findViewById(R.id.disconnect_button).setOnClickListener(this)

        //Log.i("TEST", this.toString())

        // [START configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        /*val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id)).requestEmail()
                .build()
        */

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(Scope(Scopes.PROFILE))
                .build()


        // [END configure_signin]

        // [START build_client]
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
        // [END build_client]

        // [START customize_button]
        // Set the dimensions of the sign-in button.
        //val signInButton = view.findViewById(R.id.sign_in_button)
        //signInButton.setSize(SignInButton.SIZE_STANDARD)
        //signInButton.setColorScheme(SignInButton.COLOR_LIGHT)
        // [END customize_button]
        return view
    }


    override fun onStart() {
        super.onStart()

        // [START on_start_sign_in]
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(context)
        updateUI(account)
        // [END on_start_sign_in]
    }

    // [START onActivityResult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            Log.e("bla", "GotMail")

            handleSignInResult(task)
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            Log.e("bla", "TRYING GETTING RESULT")
            val account = completedTask.getResult(ApiException::class.java)
            Log.e("bla", "GOT RESULT - UPDATING UI....")
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

            Log.e(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }

    }
    // [END handleSignInResult]

    // [START signIn]
    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    // [END signIn]

    // [START signOut]
    private fun signOut() {

        mGoogleSignInClient!!.signOut()
                .addOnCompleteListener({
                    // [START_EXCLUDE]
                    updateUI(null)
                    // [END_EXCLUDE]
                })
    }
    // [END signOut]

    // [START revokeAccess]
    private fun revokeAccess() {

        mGoogleSignInClient!!.revokeAccess()
                .addOnCompleteListener({
                    // [START_EXCLUDE]
                    updateUI(null)
                    // [END_EXCLUDE]
                })
    }

    // [END revokeAccess]
    /*private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = mAuth.getCurrentUser()
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // ...
                })
    }*/
    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {

            mStatusTextView!!.text = getString(R.string.signed_in_fmt, account.displayName)

            view!!.findViewById(R.id.sign_in_button).visibility = View.GONE
            view!!.findViewById(R.id.sign_out_and_disconnect).visibility = View.VISIBLE
        } else {
            mStatusTextView!!.setText(R.string.signed_out)

            view!!.findViewById(R.id.sign_in_button).visibility = View.VISIBLE
            view!!.findViewById(R.id.sign_out_and_disconnect).visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.sign_in_button -> signIn()
            R.id.sign_out_button -> signOut()
            R.id.disconnect_button -> revokeAccess()
        }
    }


}

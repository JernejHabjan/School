package si.roglan.EMP_Seminarska


import android.content.Context
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

//==================================================================================================
    private lateinit var activityCommander: LoginListener

    internal interface LoginListener {
        fun onLogin(username: String)
        fun onLogout()
    }

    override fun onAttach(context: Context?) {
        try {
            activityCommander = (context as LoginListener?)!!
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString())
        }

        super.onAttach(context)
    }

//==================================================================================================
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater!!.inflate(R.layout.fragment_sign_in, container, false)
        mStatusTextView = view!!.findViewById(R.id.status) as TextView?

        view.findViewById(R.id.sign_in_button).setOnClickListener(this)
        view.findViewById(R.id.sign_out_button).setOnClickListener(this)
        view.findViewById(R.id.disconnect_button).setOnClickListener(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(Scope(Scopes.PROFILE))
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(context, gso)

        //val signInButton = view.findViewById(R.id.sign_in_button)
        //signInButton.setSize(SignInButton.SIZE_STANDARD)
        //signInButton.setColorScheme(SignInButton.COLOR_LIGHT)

        return view
    }


    override fun onStart() {
        super.onStart()

        // Check if a Google account is already signed in
        val account = GoogleSignIn.getLastSignedInAccount(context)
        updateState(account)
    }

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

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            Log.e("bla", "TRYING GETTING RESULT")
            val account = completedTask.getResult(ApiException::class.java)
            Log.e("bla", "GOT RESULT - UPDATING UI....")
            // Signed in successfully, show authenticated UI.
            updateState(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.

            Log.e(TAG, "signInResult:failed code=" + e.statusCode)
            updateState(null)
        }

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        mGoogleSignInClient!!.signOut().addOnCompleteListener({
            updateState(null)
        })
    }

    private fun revokeAccess() {
        mGoogleSignInClient!!.revokeAccess().addOnCompleteListener({
            updateState(null)
        })
    }

    private fun updateState(account: GoogleSignInAccount?) {
        if (account != null) {
            activityCommander.onLogin(account.displayName.toString())

            mStatusTextView!!.text = getString(R.string.signed_in_fmt, account.displayName)

            view!!.findViewById(R.id.sign_in_button).visibility = View.GONE
            view!!.findViewById(R.id.sign_out_and_disconnect).visibility = View.VISIBLE
        } else {
            activityCommander.onLogout()

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

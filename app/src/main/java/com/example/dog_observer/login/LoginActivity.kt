package com.example.dog_observer.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dog_observer.MainActivity
import com.example.dog_observer.di.DaggerLoginComponent
import com.example.dog_observer.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class LoginActivity : AppCompatActivity() {

    private val component by lazy{
        DaggerLoginComponent.builder()
            .Build()
    }
   // private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginActivityViewModel>(){
        component.viewModelFactory()
    }
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = requireNotNull(_binding) { "Binding not set" }
   // private lateinit var mGoogleSignInClient: GoogleSignInClient
    //private lateinit var getContent: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
   /*     val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Toast.makeText(this,"getResult",Toast.LENGTH_SHORT).show()
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(it.data)
                handleSignInResult(task)
        }*/
    }
    fun onClick(view: View)
    {
       // val signInIntent = mGoogleSignInClient.signInIntent
   //     getContent.launch(signInIntent)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        val acct = GoogleSignIn.getLastSignedInAccount(this)


    }


    override fun onStart() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account != null)
        {
            Toast.makeText(this,viewModel.str ,Toast.LENGTH_SHORT).show()
        }
        super.onStart()
    }

    fun updateUI(account: GoogleSignInAccount?)
    {
        // TODO: user already signed
    }

  /*  private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(this,"SUCCESS",Toast.LENGTH_SHORT).show()
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this,"signInResult:failed code=\""+ e.statusCode,Toast.LENGTH_SHORT).show()
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }*/

    override fun onDestroy() {
        super.onDestroy()
    }
}
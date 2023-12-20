package com.example.selldo

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.selldo.databinding.ActivityMainBinding
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signinsubmit.setOnClickListener {
            val email : Editable? = binding.emailtv.text
            val password : Editable? = binding.passwordtv.text
            if(!email.isNullOrBlank() && !password.isNullOrBlank()){
                if(true) {
                    viewModel.loginRequest(LoginRequest("android", User(email.toString(), password.toString())))
                    Toast.makeText(this, "Valid Syntax", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Validation failed",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginResponseLiveData.observe(this){state->

            processResponse(state)
        }

    }

    private fun processResponse(state: ResponseStatus<LoginResponse>?){

        when(state)
        {
            is ResponseStatus.Success -> {
                if(state.data!=null) {
                    Toast.makeText(this, state.data.sales_id.toString(), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "empty response", Toast.LENGTH_SHORT).show()
                }
            }

            else -> {
                Toast.makeText(this, "Invalid Credentails", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun String.isValidEmail(): Boolean {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
        )

        return EMAIL_ADDRESS_PATTERN.matcher(this).matches()
    }

    fun String.isValidPassword(): Boolean {
        val PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$")
        return PASSWORD_PATTERN.matcher(this).matches()
    }

}
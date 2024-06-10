package com.cs.banking

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.cs.banking.databinding.ActivityFullscreenBinding
import com.cs.banking.models.LoginRequest
import com.cs.banking.models.LoginResponse
import com.cs.banking.networking.ApiClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var binding: ActivityFullscreenBinding? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityFullscreenBinding.inflate(layoutInflater)
            setContentView(binding?.root)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            textChangeListener()
            binding?.login?.setOnClickListener(View.OnClickListener {
                if (validate())
                    getLogin()
            })
        } catch (e: Exception) {
            return
        }
    }

    companion object {

    }


    private fun getLogin() {
        binding?.errText?.visibility = View.GONE
        val request = LoginRequest()
        request.corporateId = "6873261"
        request.userId = "TEST001"
        request.password = "12121212"

//        request.corporateId =  binding.corporateIdEditText.text.toString().trim()
//        request.userId = binding.userIdEditText.text.toString().trim()
//        request.password = binding.passwordEditText.text.toString().trim()
        AppUtils.getNewProgressDialog(this@MainActivity, "Please Wait...", R.color.primary_color)
        ApiClient.apiService.login(request).enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                AppUtils.dismissProgressDialog()
                if (response.isSuccessful) {
                    val result: LoginResponse? = response.body()
                    Log.d("respppp", Gson().toJson(response.body()))
                    if (result != null) {
                        if (result.statusCode == "00") {
                            val intent = Intent()
                            intent.putExtra(AppConstant.RESPONSE_KEY, result)
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        }
                    }
                } else {
                    binding?.errText?.text = "Something went wrong"
                    binding?.errText?.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                try {
                    binding?.errText?.text = "Something went wrong"
                    binding?.errText?.visibility = View.VISIBLE
                    AppUtils.dismissProgressDialog()
                } catch (e: Exception) {
                    Log.e("error", e.message.toString())
                }
            }

        })
    }

    private fun validate(): Boolean {
        if (binding?.corporateIdEditText?.text.toString().trim().isEmpty()) {
            binding?.corporateIdLayout?.error = "Please Enter Corporate Id"
            return false
        } else {
            binding?.corporateIdLayout?.error = null
        }
        if (binding?.userIdEditText?.text.toString().trim().isEmpty()) {
            binding?.userIdLayout?.error = "Please Enter User Id"
            return false
        } else {
            binding?.userIdLayout?.error = null
        }
        if (binding?.passwordEditText?.text.toString().trim().isEmpty()) {
            binding?.passwordLayout?.error = "Please Enter Password"
            return false
        } else {
            binding?.passwordLayout?.error = null
        }
        return true
    }

    private fun textChangeListener() {
        binding?.corporateIdEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Code to execute before the text changes
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (binding?.corporateIdEditText?.text.toString().trim().isEmpty()) {
                    binding?.corporateIdLayout?.error = "Please Enter Corporate Id"
                } else {
                    binding?.corporateIdLayout?.error = null
                }
            }

            override fun afterTextChanged(editable: Editable) {
            }
        })
        binding?.userIdEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Code to execute before the text changes
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (binding?.userIdEditText?.text.toString().trim().isEmpty()) {
                    binding?.userIdLayout?.error = "Please Enter User Id"
                } else {
                    binding?.userIdLayout?.error = null
                }
            }

            override fun afterTextChanged(editable: Editable) {
            }
        })

        binding?.passwordEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                // Code to execute before the text changes
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (binding?.passwordEditText?.text.toString().trim().isEmpty()) {
                    binding?.passwordLayout?.error = "Please Enter Password"

                } else {
                    binding?.passwordLayout?.error = null
                }
            }

            override fun afterTextChanged(editable: Editable) {
            }
        })
    }


}
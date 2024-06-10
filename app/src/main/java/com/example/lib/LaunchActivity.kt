package com.example.lib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.cs.banking.AppManager
import com.google.android.material.button.MaterialButton

class LaunchActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)



        findViewById<MaterialButton>(R.id.buttonClick).setOnClickListener(View.OnClickListener {
            AppManager(this@LaunchActivity).startTransactionWithLauncher(
                this@LaunchActivity,
                resultLauncher
            )
        })


        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    data?.getStringExtra("response")?.let {
                        Log.d("mannet_result", it)
                    }
                }
            }
    }
}
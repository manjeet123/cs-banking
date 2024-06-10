package com.cs.banking

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

class AppManager(context: Context, listener: LoginListener? = null) {


    public fun startTransaction(
        context: Activity,
        requestCode: Int,
    ) {
        context.startActivityForResult(Intent(context, MainActivity::class.java), requestCode)
    }

    public fun startTransactionWithLauncher(
        context: Activity,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        resultLauncher.launch(Intent(context, MainActivity::class.java))
    }
}



package com.cs.banking

object SaveReference {
    private var loginListener: LoginListener? = null


    fun saveReference(loginListener: LoginListener?) {
        SaveReference.loginListener = loginListener
    }

    fun getLoginReference(loginListener: LoginListener?): LoginListener? {
        return loginListener
    }


}
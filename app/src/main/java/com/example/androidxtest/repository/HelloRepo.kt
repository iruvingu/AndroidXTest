package com.example.androidxtest.repository

import android.util.Log
import java.lang.Exception

interface HelloRepo{
    fun sayHello(): String
}

class HelloRepoImpl(): HelloRepo {

    override fun sayHello(): String = "Hello Koin"

}
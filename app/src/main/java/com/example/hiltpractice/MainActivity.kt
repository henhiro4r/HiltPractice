package com.example.hiltpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
        println(someClass.doSomething())
    }
}

class SomeClass
@Inject
constructor(
    private val someOtherClass: SomeOtherClass
) {
    fun doAThing() : String{
        return "Some string"
    }

    fun doSomething(): String{
        return someOtherClass.doSomething()
    }
}

class SomeOtherClass
@Inject
constructor() {
    fun doSomething(): String{
        return "Look another string"
    }
}
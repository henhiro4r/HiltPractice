package com.example.hiltpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // field injection
    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
        println(someClass.doAThing2())
    }
}

class SomeClass
@Inject
constructor(
    @Impl1 private val someInterfaceImpl: SomeInterface,
    @Impl2 private val someInterfaceImpl2: SomeInterface
) {
    fun doAThing(): String {
        return "Look :${someInterfaceImpl.getSomething()}"
    }

    fun doAThing2(): String {
        return "Look :${someInterfaceImpl2.getSomething()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor(): SomeInterface{
    override fun getSomething(): String {
        return "Something1"
    }
}

class SomeInterfaceImpl2
@Inject
constructor(): SomeInterface{
    override fun getSomething(): String {
        return "Something2"
    }
}

interface SomeInterface {
    fun getSomething(): String
}

@InstallIn(ApplicationComponent::class)
@Module
class MyModule{
    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface(): SomeInterface{
        return SomeInterfaceImpl()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2(): SomeInterface{
        return SomeInterfaceImpl2()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1
annotation class Impl2
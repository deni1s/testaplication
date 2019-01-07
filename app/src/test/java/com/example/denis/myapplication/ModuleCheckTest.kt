package com.example.denis.myapplication

import com.example.denis.myapplication.modules.appModule
import com.example.denis.myapplication.modules.realmModule
import com.example.denis.myapplication.modules.remoteDataSourceModule
import com.example.denis.myapplication.modules.testNewsApp
import org.junit.After
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.test.KoinTest
import org.koin.test.checkModules
import org.mockito.Mockito.mock

class ModuleCheckTest : KoinTest {

    val mockedAndroidContext = module {
        single { mock(Application::class.java) }
    }

    @After
    fun after() {
    }

    @Test
    fun testRemoteConfiguration() {
        checkModules(listOf(remoteDataSourceModule))
    }

    @Test
    fun testTestConfiguration() {
        checkModules(testNewsApp)
    }
}
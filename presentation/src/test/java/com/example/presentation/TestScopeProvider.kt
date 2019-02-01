package com.example.presentation

import com.example.presentation.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class TestScopeProvider : CoroutineContextProvider() {

    @ExperimentalCoroutinesApi
    override val uiScope: CoroutineScope =  CoroutineScope(Dispatchers.Unconfined)
    @ExperimentalCoroutinesApi
    override val POOL: CoroutineScope = CoroutineScope(Dispatchers.Unconfined)

}
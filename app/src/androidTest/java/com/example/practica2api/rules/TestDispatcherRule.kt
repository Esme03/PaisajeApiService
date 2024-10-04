package com.example.practica2api.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class TestDispatcherRule(
    val testDispatcherRule: TestDispatcher =
        UnconfinedTestDispatcher()
): TestWatcher() {
    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcherRule)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}
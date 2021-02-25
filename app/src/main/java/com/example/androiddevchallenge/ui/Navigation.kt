/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.utils.getMutableStateOf

enum class ScreenName { DASHBOARD, PROFILE }

sealed class Screen(val id: ScreenName) {
    object Dashboard : Screen(ScreenName.DASHBOARD)
    data class PuppyProfile(val postId: String) : Screen(ScreenName.PROFILE)
}

private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"
private const val SIS_PUPPY = "pupyy"

private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {
        // add extra keys for various types here
        if (this is Screen.PuppyProfile) {
            it.putString(SIS_PUPPY, postId)
        }
    }
}

private fun Bundle.toScreen(): Screen {
    return when (ScreenName.valueOf(getStringOrThrow(SIS_NAME))) {
        ScreenName.DASHBOARD -> Screen.Dashboard
        ScreenName.PROFILE -> {
            val postId = getStringOrThrow(SIS_PUPPY)
            Screen.PuppyProfile(postId)
        }
    }
}

private fun Bundle.getStringOrThrow(key: String) =
    requireNotNull(getString(key)) { "Missing key '$key' in $this" }

class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = Screen.Dashboard,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Screen.Dashboard
        currentScreen = Screen.Dashboard
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}

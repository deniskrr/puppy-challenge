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
package com.example.androiddevchallenge

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.data.AppContainer
import com.example.androiddevchallenge.data.puppies.PuppyRepository
import com.example.androiddevchallenge.ui.NavigationViewModel
import com.example.androiddevchallenge.ui.PuppyProfileScreen
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.dashboard.DashboardScreen
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyApp(appContainer: AppContainer, navigationViewModel: NavigationViewModel) {
    MyTheme {
        AppContent(
            puppyRepository = appContainer.puppyRepository,
            navigationViewModel = navigationViewModel
        )
    }
}

@Composable
fun AppContent(puppyRepository: PuppyRepository, navigationViewModel: NavigationViewModel) {
    Crossfade(targetState = navigationViewModel.currentScreen) {
        Surface(color = MaterialTheme.colors.background) {
            when (navigationViewModel.currentScreen) {
                is Screen.Dashboard -> {
                    DashboardScreen(
                        puppyRepository = puppyRepository,
                        navigateTo = navigationViewModel::navigateTo
                    )
                }
                is Screen.PuppyProfile -> {
                    PuppyProfileScreen(
                        puppyRepository = puppyRepository,
                        onBack = navigationViewModel::onBack
                    )
                }
            }
        }
    }
}

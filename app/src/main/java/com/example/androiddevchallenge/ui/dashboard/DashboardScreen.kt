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
package com.example.androiddevchallenge.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.data.puppies.PuppyRepository
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.state.UiState
import com.example.androiddevchallenge.utils.produceUiState

@Composable
fun DashboardScreen(
    puppyRepository: PuppyRepository,
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val (puppyUiState, refreshPuppy, clearError) = produceUiState(puppyRepository) {
        getPuppies()
    }

    DashboardScreen(
        puppies = puppyUiState.value,
        navigateTo = navigateTo,
        scaffoldState = scaffoldState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    puppies: UiState<List<Puppy>>,
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            if (puppies.data != null) {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    content = {
                        items(puppies.data) {
                            PuppyCard(
                                puppy = it,
                                onClick = {
                                    navigateTo(Screen.PuppyProfile(it.id))
                                }
                            )
                        }
                    }
                )
            }
        }
    )
}

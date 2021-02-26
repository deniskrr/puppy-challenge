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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
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

    val adopted by puppyRepository.observeAdopted().collectAsState(setOf())

    DashboardScreen(
        puppies = puppyUiState.value,
        adopted = adopted,
        navigateTo = navigateTo,
        scaffoldState = scaffoldState
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    puppies: UiState<List<Puppy>>,
    adopted: Set<String>,
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = LocalContentColor.current
                    )
                },
            )
        },
        scaffoldState = scaffoldState,
        content = {
            if (puppies.loading) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (puppies.data != null) {
                    Column {
                        Text(
                            "Tap on a puppy to visit its profile",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(16.dp)
                        )
                        LazyVerticalGrid(
                            cells = GridCells.Fixed(2),
                            content = {
                                items(puppies.data) {
                                    PuppyCard(
                                        puppy = it,
                                        onClick = {
                                            navigateTo(Screen.PuppyProfile(it.id))
                                        },
                                        adopted = adopted.contains(it.id)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}

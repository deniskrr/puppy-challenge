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
package com.example.androiddevchallenge.ui.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.model.Gender
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.data.puppies.PuppyRepository
import com.example.androiddevchallenge.data.puppies.puppies
import com.example.androiddevchallenge.ui.common.IconText
import com.example.androiddevchallenge.ui.state.UiState
import com.example.androiddevchallenge.utils.produceUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PuppyProfileScreen(
    puppyId: String,
    puppyRepository: PuppyRepository,
    onBack: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    val (puppyUiState, refreshPuppy, clearError) = produceUiState(puppyRepository) {
        getPuppy(puppyId)
    }

    val coroutineScope = rememberCoroutineScope()

    PuppyProfileScreen(
        adoptionHandler = {
            coroutineScope.launch(Dispatchers.IO) {
                puppyRepository.adoptPuppy(puppyId)
                withContext(Dispatchers.Main) {
                    onBack()
                }
            }
        },
        puppyState = puppyUiState.value,
        onBack = onBack,
        scaffoldState = scaffoldState
    )
}

@Composable
fun PuppyProfileScreen(
    adoptionHandler: () -> Unit,
    puppyState: UiState<Puppy>,
    onBack: () -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Puppy for Adoption",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = LocalContentColor.current
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
        content = {
            if (puppyState.loading) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else if (puppyState.data != null) {
                val puppy = puppyState.data
                Column(Modifier.fillMaxHeight()) {
                    val modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                    PuppyAvatar(puppy.imageId, modifier)
                    Column(
                        modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AdoptMeButton(adoptionHandler)
                    }
                }
                PuppyInfo(puppy)
            }
        }
    )
}

@Composable
private fun PuppyInfo(puppy: Puppy) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            puppy.name,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                        PuppyGender(puppy)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(puppy.race)
                        PuppyAge(age = puppy.age)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    PuppyAddress(puppy.address)
                }
            }
        }
    }
}

@Composable
private fun PuppyAvatar(
    @DrawableRes avatarId: Int,
    modifier: Modifier
) {
    Image(
        painter = painterResource(id = avatarId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun PuppyGender(puppy: Puppy) {
    Icon(
        imageVector = if (puppy.gender == Gender.MALE) Icons.Outlined.Male else Icons.Outlined.Female,
        contentDescription = null,
        modifier = Modifier.size(32.dp)
    )
}

@Composable
private fun PuppyAddress(address: String) {
    IconText(
        imageVector = Icons.Outlined.LocationOn,
        text = address,
        modifier = Modifier
            .size(18.dp)
    )
}

@Composable
fun PuppyAge(age: Int) {
    IconText(
        imageVector = Icons.Outlined.AccessTime, text = "$age years old",
        modifier = Modifier
            .size(18.dp),
    )
}

@Composable
fun AdoptMeButton(adoptionHandler: () -> Unit) {
    Button(modifier = Modifier.fillMaxWidth(), onClick = { adoptionHandler() }) {
        Text(
            text = "Take me home",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        )
    }
}

@Preview
@Composable
fun PuppyInfoPreview() {
    val mockPuppy = puppies.first()

    PuppyInfo(puppy = mockPuppy)
}

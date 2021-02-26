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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Gender
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.utils.scrim

@Composable
fun PuppyCard(puppy: Puppy, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Card(shape = RoundedCornerShape(32.dp), contentColor = Color.White) {
            Image(
                painter = painterResource(id = puppy.imageId), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(0.85f)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.85f)
                    .scrim(
                        listOf(
                            Color(0, 0, 0, 0),
                            Color(0, 0, 0, 0),
                            MaterialTheme.colors.primary.copy(alpha = 0.7f)
                        ),
                    ),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.85f)
                    .padding(16.dp, 8.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    puppy.name,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    puppy.address, style = MaterialTheme.typography.subtitle2, maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    PuppyAge(age = puppy.age)
                    Spacer(modifier = Modifier.width(8.dp))
                    PuppyGender(gender = puppy.gender)
                }
            }
        }
    }
}

@Composable
fun PuppyAge(age: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_time),
            contentDescription = null,
            modifier = Modifier
                .size(14.dp)
        )
        Text(
            "$age years old",
            style = MaterialTheme.typography.subtitle2,
            maxLines = 1,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun PuppyGender(gender: Gender) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(
                id = if (gender == Gender.MALE) R.drawable.ic_male else R.drawable.ic_female
            ),
            contentDescription = null,
            modifier = Modifier
                .size(14.dp)
        )
        Text(
            text = gender.stringRepresentation,
            style = MaterialTheme.typography.subtitle2,
            maxLines = 1,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

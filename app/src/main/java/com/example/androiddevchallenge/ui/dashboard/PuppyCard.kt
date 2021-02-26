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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Female
import androidx.compose.material.icons.outlined.Male
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.model.Gender
import com.example.androiddevchallenge.data.model.Puppy
import com.example.androiddevchallenge.data.puppies.puppies
import com.example.androiddevchallenge.ui.common.IconText
import com.example.androiddevchallenge.utils.scrim

@Composable
fun PuppyCard(puppy: Puppy, onClick: () -> Unit, adopted: Boolean) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Card(
            shape = RoundedCornerShape(32.dp),
            contentColor = Color.White,
            modifier = if (!adopted) Modifier.clickable(
                onClick = onClick,
            ) else Modifier,
        ) {
            Image(
                painter = painterResource(id = puppy.imageId), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(0.85f)
                    .fillMaxWidth()
            )
            TransparentGradient(if (adopted) Color.Black else MaterialTheme.colors.primary)
            PuppyInfo(puppy, adopted = adopted)
        }
    }
}

@Composable
private fun TransparentGradient(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
            .scrim(
                listOf(
                    Color(0, 0, 0, 0),
                    Color(0, 0, 0, 0),
                    color.copy(alpha = 0.7f)
                ),
            ),
    )
}

@Composable
private fun PuppyInfo(puppy: Puppy, adopted: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            puppy.name,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = if (adopted) TextDecoration.LineThrough else null
            )
        )
        Text(
            puppy.race, style = MaterialTheme.typography.subtitle2, maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            PuppyAge(age = puppy.age)
            PuppyGender(gender = puppy.gender)
        }
    }
}

@Composable
fun PuppyAge(age: Int) {
    IconText(
        imageVector = Icons.Outlined.AccessTime,
        text = "$age years old",
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
            .size(18.dp),
    )
}

@Composable
fun PuppyGender(gender: Gender) {
    IconText(
        imageVector = if (gender == Gender.MALE) Icons.Outlined.Male else Icons.Outlined.Female,
        text = gender.stringRepresentation,
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier.size(18.dp),
    )
}

@Preview
@Composable
fun PuppyCardPreview() {
    val mockPuppy = puppies.first()

    PuppyCard(puppy = mockPuppy, onClick = { }, adopted = false)
}

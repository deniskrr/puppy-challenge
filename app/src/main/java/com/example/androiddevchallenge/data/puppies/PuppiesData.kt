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
package com.example.androiddevchallenge.data.puppies

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.model.Gender
import com.example.androiddevchallenge.data.model.Puppy
import java.util.UUID

val puppies = listOf(
    Puppy(
        id = UUID.randomUUID().toString(),
        name = "Amber",
        race = "American Bulldog",
        age = 1,
        Gender.FEMALE,
        address = "Mancton, NB",
        imageId = R.drawable.amber,
    ),
    Puppy(
        id = UUID.randomUUID().toString(),
        name = "Kane",
        race = "Pit Bull Terrier",
        age = 6,
        Gender.MALE,
        address = "Amherst, NS",
        imageId = R.drawable.kane,
    ),
    Puppy(
        id = UUID.randomUUID().toString(),
        name = "Harry",
        race = "Shih Tzu",
        age = 2,
        Gender.MALE,
        address = "Sebec, ME",
        imageId = R.drawable.harry,
    ),
    Puppy(
        id = UUID.randomUUID().toString(),
        name = "Rudolph",
        race = "Labrador Retriever",
        age = 6,
        Gender.MALE,
        address = "Green Cove Springs, FL",
        imageId = R.drawable.rudolph,
    ),
    Puppy(
        id = UUID.randomUUID().toString(),
        name = "Dutchess",
        race = "Greyhound Mix",
        age = 4,
        Gender.FEMALE,
        address = "Paris, ME",
        imageId = R.drawable.dutchess,
    ),
    Puppy(
        id = UUID.randomUUID().toString(),
        name = "Spencer",
        race = "Husky Mix",
        age = 9,
        Gender.MALE,
        address = "Sainte-Angele-de-Monnoir, QC",
        imageId = R.drawable.spencer,
    ),
    Puppy(
        id = UUID.randomUUID().toString(),
        name = "King",
        race = "Pit Bull Terrier Mix ",
        age = 5,
        Gender.MALE,
        address = "South Portland, ME",
        imageId = R.drawable.king,
    ),
)

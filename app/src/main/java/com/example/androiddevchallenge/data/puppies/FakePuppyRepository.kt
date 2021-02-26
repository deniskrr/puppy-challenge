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

import com.example.androiddevchallenge.data.Result
import com.example.androiddevchallenge.data.model.Puppy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class FakePuppyRepository : PuppyRepository {

    private val adopted = MutableStateFlow<Set<String>>(setOf())

    override suspend fun getPuppy(puppyId: String): Result<Puppy> {
        // Simulate loading
        delay(1000)

        return withContext(Dispatchers.IO) {
            val puppy = puppies.find { it.id == puppyId }
            if (puppy == null) {
                Result.Error(IllegalArgumentException("Unable to find post"))
            } else {
                Result.Success(puppy)
            }
        }
    }

    override suspend fun getPuppies(): Result<List<Puppy>> {
        // Simulate loading
        delay(1000)

        return Result.Success(puppies)
    }

    override fun observeAdopted(): Flow<Set<String>> {
        return adopted
    }

    override suspend fun adoptPuppy(puppyId: String) {
        val adoptedSet = adopted.value.toMutableSet()
        adoptedSet.add(puppyId)
        adopted.value = adoptedSet
    }
}

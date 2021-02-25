package com.example.androiddevchallenge.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun DashboardScreen(
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    DashboardScreen(
        puppies = listOf("Puppy 1", "Puppy 2"),
        navigateTo = navigateTo,
        scaffoldState = scaffoldState
    )
}


@Composable
fun DashboardScreen(
    puppies: List<String>,
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            LazyColumn {
                items(puppies) {
                    Text(it)
                }
            }
        }
    )
}
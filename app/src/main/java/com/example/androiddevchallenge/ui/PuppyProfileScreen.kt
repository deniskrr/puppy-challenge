package com.example.androiddevchallenge.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable

@Composable
fun PuppyProfileScreen(
    onBack: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    PuppyProfileScreen(
        puppies = listOf(),
        onBack = onBack,
        scaffoldState = scaffoldState
    )
}


@Composable
fun PuppyProfileScreen(
    puppies: List<String>,
    onBack: () -> Unit,
    scaffoldState: ScaffoldState
) {
    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            Text("Hey2")
        }
    )
}
package com.example.androiddevchallenge

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.DashboardScreen
import com.example.androiddevchallenge.ui.NavigationViewModel
import com.example.androiddevchallenge.ui.PuppyProfileScreen
import com.example.androiddevchallenge.ui.Screen
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyApp(navigationViewModel: NavigationViewModel) {
    MyTheme {
        AppContent(navigationViewModel = navigationViewModel)
    }
}

@Composable
fun AppContent(navigationViewModel: NavigationViewModel) {
    Crossfade(targetState = navigationViewModel.currentScreen) {
        Surface(color = MaterialTheme.colors.background) {
            when (navigationViewModel.currentScreen) {
                is Screen.Dashboard -> {
                    DashboardScreen(navigateTo = navigationViewModel::navigateTo)
                }
                is Screen.PuppyProfile -> {
                    PuppyProfileScreen(onBack = navigationViewModel::onBack)
                }
            }
        }
    }

}
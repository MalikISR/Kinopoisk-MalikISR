package com.example.kinopoiskmalik.ui.screens.main.films

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kinopoiskmalik.ui.screens.main.navigation.mainNavigationRoute
fun NavGraphBuilder.films(
) {
    composable(
        route = mainNavigationRoute,
    ) {
        FilmsRoute()
    }
}

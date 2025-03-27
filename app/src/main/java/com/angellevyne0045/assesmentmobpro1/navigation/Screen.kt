package com.angellevyne0045.assesmentmobpro1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}
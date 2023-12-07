package com.cmps312.newshub.ui.naviagtion

sealed class Screen(val route: String, val title: String) {

    data object NewsListScreen : Screen(route = "News Screen", title = "News List")
    data object AddNewsArticleScreen : Screen(route = "News Editor", title = "News Editor")




}

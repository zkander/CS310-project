package com.cmps312.newshub.ui.naviagtion

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmps312.newshub.ui.viewmodel.NewsHubViewModel
import com.cmps312.newshub.ui.views.AddNewsArticle
import com.cmps312.newshub.ui.views.NewsListScreen

@Composable
fun AppNavHost(navHostController: NavHostController, paddingValues: PaddingValues) {
    val newsHubViewModel =
        viewModel<NewsHubViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)


    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsListScreen.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        composable(route = Screen.NewsListScreen.route) {
            NewsListScreen(
                newsHubViewModel = newsHubViewModel,
                onAddArticle =
                {
                    newsHubViewModel.selectedArticle.id=-1
                    navHostController.navigate(Screen.AddNewsArticleScreen.route)
                },
                onDeleteArticle = {
                    newsHubViewModel.deleteArticle(it)
                }, onEditArticle = {
                    newsHubViewModel.selectedArticle = it
                    navHostController.navigate(Screen.AddNewsArticleScreen.route)
                }

            )
        }
        composable(route = Screen.AddNewsArticleScreen.route) {
            AddNewsArticle(
                newsHubViewModel = newsHubViewModel,
                onAddNewsArticle = {
                    newsHubViewModel.addArticle(it)
                    navHostController.navigate(Screen.NewsListScreen.route)
                }
            )
        }


    }

}
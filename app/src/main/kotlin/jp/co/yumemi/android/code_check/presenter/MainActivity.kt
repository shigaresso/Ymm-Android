/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.presenter

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.android.code_check.Item
import jp.co.yumemi.android.code_check.presenter.ui.screen.RepositoryScreen
import jp.co.yumemi.android.code_check.presenter.ui.screen.SearchRepositoriesScreen
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "searchRepositoriesScreen") {
                composable("searchRepositoriesScreen") {
                    SearchRepositoriesScreen() { item ->
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "item",
                            value = item
                        )
                        navController.navigate("repositoryScreen")
                    }
                }
                composable(
                    "repositoryScreen"
                ) {
                    val item =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Item>("item")
                    RepositoryScreen(item!!)
                }
            }
        }
    }

    companion object {
        lateinit var lastSearchDate: Date
    }
}
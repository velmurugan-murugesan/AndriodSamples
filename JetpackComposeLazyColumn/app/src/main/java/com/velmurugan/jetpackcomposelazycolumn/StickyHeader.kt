package com.velmurugan.jetpackcomposelazycolumn

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeader(context: Context,navHostController: NavHostController) {

    val movies = getData(context)
    val grouped = movies.groupBy{it.category}

    LazyColumn(){
        grouped.forEach { (section, sectionPersons) ->
            stickyHeader {
                Text(
                    text = "SECTION: $section",
                    color = Color.White,
                    modifier = Modifier
                        .background(color = Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
            items(items = sectionPersons) {movie ->
                MovieItem(movie = movie)
            }

        }
    }
}


@Preview
@Composable
fun StickyHeaderPreview() {
    StickyHeader(context = LocalContext.current, navHostController = rememberNavController())
}
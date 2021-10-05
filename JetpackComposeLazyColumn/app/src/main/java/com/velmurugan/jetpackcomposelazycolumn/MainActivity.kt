package com.velmurugan.jetpackcomposelazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.velmurugan.jetpackcomposelazycolumn.ui.theme.JetpackComposeLazyColumnTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLazyColumnTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {


                    val navController = rememberNavController()
                    val context = LocalContext.current

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            Home(navController)
                        }
                        composable("lazyColumn") {
                            LazyColumnExample(context)
                        }
                        composable("expandableColumn") {
                            ExpandableColumn(context)
                        }
                        composable("stickyHeader") {
                            StickyHeader(context, navController)
                        }
                        composable("lazyRow") {
                            LazyRowExample(context = context)
                        }

                        composable("gridView") {
                            GridView(context = context)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Home(navController: NavHostController) {

    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate("lazyColumn") }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Lazy Column")
        }

        Button(onClick = { navController.navigate("expandableColumn") }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Expandable Column")
        }

        Button(onClick = { navController.navigate("stickyHeader") }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Sticky Header")
        }

        Button(onClick = { navController.navigate("lazyRow") }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Lazy Row")
        }

        Button(onClick = { navController.navigate("gridView") }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Grid View")
        }
    }

}

@Preview
@Composable
fun HomePreview() {
    Home(navController = rememberNavController())
}


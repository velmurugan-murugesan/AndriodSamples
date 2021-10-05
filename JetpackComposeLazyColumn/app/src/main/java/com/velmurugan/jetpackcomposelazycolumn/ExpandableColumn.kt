package com.velmurugan.jetpackcomposelazycolumn

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation

@ExperimentalAnimationApi
@Composable
fun ExpandableColumn(context: Context) {
    var selectedPosition by remember {
        mutableStateOf(-1)
    }
    LazyColumn(Modifier.fillMaxWidth()) {
        itemsIndexed(items = getData(context)) {index,movie ->
            ExpandableMovieItem(movie = movie,index,selectedPosition) {
                selected -> selectedPosition = selected
            }
        }
    }

}

@Preview
@Composable
fun ExpandableColumnPreview() {

}
@ExperimentalAnimationApi
@Composable
fun ExpandableMovieItem(movie: Movie,currentPosition: Int, selectedPosition: Int, onSelected: (Int) -> Unit) {


    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onSelected(currentPosition) }, shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface() {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = movie.imageUrl,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.placeholder)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = movie.desc,
                    modifier = Modifier
                        .size(70.dp)
                        .weight(0.2f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    AnimatedVisibility(visible = selectedPosition == currentPosition) {

                        Text(
                            text = movie.desc,
                            style = MaterialTheme.typography.body1,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                }
            }
        }
    }
}
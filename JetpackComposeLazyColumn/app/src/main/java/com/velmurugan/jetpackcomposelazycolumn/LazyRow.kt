package com.velmurugan.jetpackcomposelazycolumn

import android.content.Context
import android.graphics.fonts.FontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LazyRowExample(context: Context) {

    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(Color(0xFFF0EAD6))) {
        items(items = getData(context)) {movie ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(6.dp),
                backgroundColor = Color(0xFFFF5470),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = movie.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(25.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}


@Preview
@Composable
fun LazyRowPreview() {
    LazyRowExample(LocalContext.current)
}
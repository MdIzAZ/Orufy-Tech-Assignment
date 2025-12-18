package com.example.orufytechassignment.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.orufytechassignment.R
import kotlinx.coroutines.delay

@Composable
fun HomeCarousel() {

    val images = listOf(
        R.drawable.carousel_1,
        R.drawable.carousel_2,
        R.drawable.carousel_3,
        R.drawable.carousel_1,
        R.drawable.carousel_2,
        R.drawable.carousel_3,
        R.drawable.carousel_1,
        R.drawable.carousel_2,
        R.drawable.carousel_3,
    )

    val pagerState = rememberPagerState { images.size }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1800)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % (images.size))
        }
    }



    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentPadding = PaddingValues(horizontal = 40.dp),
        ) { page ->


            Image(
                painter = painterResource(images[page]),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxSize()
            )


        }

        Spacer(modifier = Modifier.height(8.dp))

        PagerIndicators(
            total = images.size,
            current = pagerState.currentPage
        )
    }
}

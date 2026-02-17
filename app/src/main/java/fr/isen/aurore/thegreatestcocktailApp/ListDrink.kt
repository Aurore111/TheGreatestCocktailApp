package fr.isen.aurore.thegreatestcocktailApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun ListDrink(modifier: Modifier = Modifier, category: String = "") {

        val categories = listOf(
            "Coca-Cola Classic",
            "Pepsi Cola",
            "Diet Coke/Coke Zero",
            "Dr Pepper",
            "Sprite",
            "Fanta"
        )

        LazyVerticalGrid(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFFFF4E6))
                .padding(16.dp),
               // .padding(paddingValues = innerPadding), //obliger pour la top barre
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(categories) { category ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFE5CC))
                )
                {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = category,
                            modifier = Modifier.padding(12.dp),
                            color = Color(0xFF5D4037),
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarre() {
    CenterAlignedTopAppBar(
        title =
            {
                Text("List Drinks")
            },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        )
    )
}

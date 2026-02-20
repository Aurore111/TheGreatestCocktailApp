package fr.isen.aurore.thegreatestcocktailApp

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.aurore.thegreatestcocktailApp.`package`.Drinks
import fr.isen.aurore.thegreatestcocktailApp.`package`.DrinksModel
import fr.isen.aurore.thegreatestcocktailApp.`package`.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun DrinkScreen(modifier: Modifier, category: String)
{

    val drinks = remember { mutableStateOf<List<DrinksModel>>(listOf()) }
    LaunchedEffect(Unit) {
        val call: Call<Drinks> = NetworkManager.api.getDrinksByCategory(category.replace(" ", "_"))
        call.enqueue(object : Callback<Drinks> {
            override fun onResponse(p0: Call<Drinks?>, p1: Response<Drinks?>) {
               drinks.value = p1.body()?.drinks ?: listOf()
            }
            override fun onFailure(p0: Call<Drinks?>, p1: Throwable) {
                Log.e("error", p1.message.toString())
            }
        })
    }
                LazyVerticalGrid( modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFFFFE5CC))
                        .padding(16.dp),
                      //  .padding(paddingValues = innerPadding), //obliger pour la top barre
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(drinks.value) { drink ->
                        val context = LocalContext.current
                        Button (onClick = {
                            val intent = Intent(context, RecetteActivity::class.java)
                            intent.putExtra("drinkId", drink.id)
                            context.startActivity(intent)
                        },
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            shape = RoundedCornerShape(size = 25.dp),
                            colors = ButtonColors(
                                containerColor = Color.White.copy(alpha = 0.7f),
                                contentColor = Color.White,
                                disabledContentColor = Color.Unspecified,
                                disabledContainerColor = Color.Unspecified
                            )
                        )
                        {
                                Text(
                                    text = drink.name,
                                    modifier = androidx.compose.ui.Modifier.padding(12.dp),
                                    color = Color(0xFF5D4037),
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.ExtraBold
                                )
                        }
                    }
                }
}

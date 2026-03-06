package fr.isen.aurore.thegreatestcocktailApp

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.aurore.thegreatestcocktailApp.`package`.Drinks
import fr.isen.aurore.thegreatestcocktailApp.`package`.DrinksModel
import fr.isen.aurore.thegreatestcocktailApp.`package`.NetworkManager
import retrofit2.Call  //copier de DrinkScreen.kt
import retrofit2.Callback //copier de DrinkScreen.kt
import retrofit2.Response //copier de DrinkScreen.kt
//attention import retrofit2

@Composable
fun SearchScreen(modifier: Modifier){ //recherche boisson par nom
    val drinks = remember { mutableStateOf<List<DrinksModel>>(listOf()) }
    var query by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE5CC))
            .padding(16.dp)
    ) {
        OutlinedTextField( // Barre de recherche   ; tout seul apparaitra une barre avec des fonctionnalités sur le coté
            value = query,
            onValueChange = { newQuery ->
                query = newQuery
                if (newQuery.length >= 2) {
                    val call = NetworkManager.api.searchCocktailByName(newQuery)
                    call.enqueue(object : Callback<Drinks> {
                        override fun onResponse(p0: Call<Drinks?>, p1: Response<Drinks?>) {
                            drinks.value = p1.body()?.drinks ?: listOf()
                        }
                        override fun onFailure(p0: Call<Drinks?>, p1: Throwable) {
                            Log.e("error", p1.message.toString())
                        }
                    })
                } else {
                    drinks.value = listOf()
                }
            },
            label = { Text("Rechercher un cocktail (par nom)") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (query.isEmpty()) {   // Si l'utilisateur n'a rien chercher, j'affiche le logo au centre
                Image(
                    painter = painterResource(id = R.drawable.logo_cocktail),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(250.dp) // taille logo
                        .align(Alignment.Center) // centre au milieu d ela page
                        .clip(CircleShape) //pour enlever le blanc autour de mon image
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(drinks.value) { drink ->
                        val context = LocalContext.current
                        Button(
                            onClick = {
                                val intent = Intent(context, RecetteActivity::class.java)
                                intent.putExtra("drinkId", drink.id)
                                intent.putExtra("fromCategory", true)
                                context.startActivity(intent)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            shape = RoundedCornerShape(25.dp),
                            colors = ButtonColors(
                                containerColor = Color.White.copy(alpha = 0.7f),
                                contentColor = Color.White,
                                disabledContentColor = Color.Unspecified,
                                disabledContainerColor = Color.Unspecified
                            )
                        ) {
                            Text(
                                text = drink.name,
                                modifier = Modifier.padding(12.dp),
                                color = Color(0xFF5D4037),
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.ExtraBold,
                                lineHeight = 26.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

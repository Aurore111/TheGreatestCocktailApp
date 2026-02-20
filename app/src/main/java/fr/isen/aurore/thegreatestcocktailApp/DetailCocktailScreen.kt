package fr.isen.aurore.thegreatestcocktailApp

import android.appwidget.AppWidgetHost
import android.net.http.X509TrustManagerExtensions
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorModel
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.aurore.thegreatestcocktailApp.ui.theme.TheGreatestCocktailAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import fr.isen.aurore.thegreatestcocktailApp.`package`.Drinks
import fr.isen.aurore.thegreatestcocktailApp.`package`.DrinksModel
import fr.isen.aurore.thegreatestcocktailApp.`package`.NetworkManager
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

data class Ingredient(
    val name: String,
    val measure: String
)
@Composable
fun DetailCocktailScreen(modifier: Modifier, snackbarHostState: SnackbarHostState) {
    // pas fait pareil si probleme que cours---------------------------------------

    val drink : MutableState<DrinksModel> = remember {mutableStateOf(DrinksModel())}
    val ingredients = listOf(
        Pair(drink.value.ingredient1, drink.value.measure1),
        Pair(drink.value.ingredient2, drink.value.measure2),
        Pair(drink.value.ingredient3, drink.value.measure3),
        Pair(drink.value.ingredient4, drink.value.measure4),
        Pair(drink.value.ingredient5, drink.value.measure5),
        Pair(drink.value.ingredient6, drink.value.measure6),
        Pair(drink.value.ingredient7, drink.value.measure7),
        Pair(drink.value.ingredient8, drink.value.measure8),
        Pair(drink.value.ingredient9, drink.value.measure9),
        Pair(drink.value.ingredient10, drink.value.measure10),
        Pair(drink.value.ingredient11, drink.value.measure11),
        Pair(drink.value.ingredient12, drink.value.measure12),
        Pair(drink.value.ingredient13, drink.value.measure13),
        Pair(drink.value.ingredient14, drink.value.measure14),
        Pair(drink.value.ingredient15, drink.value.measure15),
    ).filter { (ingredient, _) -> ingredient?.isNotBlank() == true } // garde seulement les non-vides

    LaunchedEffect(Unit) {
        val call: Call<Drinks> = NetworkManager.api.getRandomeCocktail()
        call.enqueue(object : Callback<Drinks> {
            override fun onResponse(p0: Call<Drinks?>, p1: Response<Drinks?>) {
                drink.value = p1.body()?.drinks?.first() ?: DrinksModel()
            }

            override fun onFailure(p0: Call<Drinks?>, p1: Throwable) {
                Log.e("error", p1.message.toString())
            }
        })
    }


    Scaffold(
        topBar = {
            TopAppBar(snackbarHostState = snackbarHostState)
        }) { innerPadding ->

        LazyColumn( //= scroll EN CLIQUANT si dépasse de mon écran
            modifier = modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize()
                .background(Color(0xFFFFE5CC))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = drink.value.name,  //on appel info de drinksModel donc bien mettre 'name'-----------------------------
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF3E2723),
                    textAlign = TextAlign.Center
                )
            }
            item {
                MonImage()
            }
            item {
                Text(
                    text = drink.value.category,
            )
            }
            item {
                Text(
                    text = drink.value.glass,
                )
            }
            item {
                  ingredients.forEach { (ingredient, measure) ->
                       Row(Modifier.wrapContentHeight()) {
                           Text(text = ingredient ?: "", color = Color.Black)
                           Spacer(Modifier.weight(weight = 1f))
                           Text(text = measure ?: "", color = Color.Black)
                   }
               }
            }
            item {

                Text(
                    text = drink.value.instruction   //-------------mettre d autre infos , voir fichier DrinkModel.kt
                )
            }
        }
    }
}

@Composable //boite des écritures
fun InfoCard(title: String, content: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    androidx.compose.material3.Card(
        modifier = Modifier.fillMaxWidth(),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.7f) //couleur boite
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF5D4037),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF5D4037)
                )
            }
            Spacer(Modifier.padding(4.dp))
            androidx.compose.material3.HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
            Spacer(Modifier.padding(4.dp))
            Text(
                text = content,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(snackbarHostState: SnackbarHostState) {
    CenterAlignedTopAppBar(
        title = {
            Text("Like it !")
        },
        actions = {
            val added = "Ajouté aux favoris"
            val removed = "Retiré des favoris"
//            val context = LocalContext.current

            val snackbarScope = rememberCoroutineScope ()
            val isFav = remember { mutableStateOf(false) }
            IconToggleButton(
                isFav.value,
                onCheckedChange = {
                    isFav.value = !isFav.value
                    // TOAST
//                    Toast.makeText(
//                        context,
//                        if (isFav.value) added else removed,
//                        Toast.LENGTH_SHORT
//                    ).show()
                    snackbarScope.launch {
                        snackbarHostState.showSnackbar(if (isFav.value) added else removed)
                    }
                }
            ) {
                Icon(
                    imageVector = if (isFav.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "fav",
                    tint = if (isFav.value) Color.Red else Color.Gray,
                )
            }
        }
    )
}



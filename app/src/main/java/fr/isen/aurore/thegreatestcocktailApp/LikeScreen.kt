package fr.isen.aurore.thegreatestcocktailApp

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.gson.Gson
import fr.isen.aurore.thegreatestcocktailApp.`package`.Drinks
import fr.isen.aurore.thegreatestcocktailApp.`package`.DrinksModel
import fr.isen.aurore.thegreatestcocktailApp.`package`.NetworkManager
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.typeOf

@Composable
fun LikeScreen(modifier: Modifier)
{ //c'est une copie de drinkScreen en partie ------------------------------------
   val sharedPreferences = SharePreferencesHelper(LocalContext.current)
   val favList = sharedPreferences.getFavoritesList()

    val drinks: MutableState<List<DrinksModel>> = remember {mutableStateOf<List<DrinksModel>> (listOf())}
    //val drinks : List<DrinksModel> = listOf<DrinksModel>()
   //val drinks : List<DrinksModel> = listOf<DrinksModel>()

    LaunchedEffect(Unit) {
        for (id in favList) {
            val call: Call<Drinks> =
                NetworkManager.api.getDrinkById(id)
            call.enqueue(object : Callback<Drinks> {
                override fun onResponse(p0: Call<Drinks?>, p1: Response<Drinks?>) {
                    drinks.value += p1.body()?.drinks ?: listOf()
                }

                override fun onFailure(p0: Call<Drinks?>, p1: Throwable) {
                    Log.e("error", p1.message.toString())
                }
            })
        }
    }

    if (favList.isEmpty()) //affiche un message si la page n'a pas de favoris
    {
        Box ( //box pour avoir le fond de couleur et la mise en page quand il n'y a pas de like
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFFFE5CC)),
            contentAlignment = Alignment.Center // centre le texte horizontalement et verticalement de ma page
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Aucun favoris",
                    // modifier = androidx.compose.ui.Modifier.padding(12.dp),
                    color = Color(0xFF5D4037),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 16.dp) // Espace entre le texte et le logo
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_cocktail),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(250.dp) // taille logo
                       // .align(Alignment.Center) // centre au milieu d ela page
                        .clip(CircleShape), //pour enlever le blanc autour de mon image
                )
            }
        }
    }else {
        LazyVerticalGrid(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFFFE5CC))
                .padding(16.dp),
            //  .padding(paddingValues = innerPadding), //obliger pour la top barre
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)//,
        ) {
            items(drinks.value) { drink ->
                val context = LocalContext.current
                Button(
                    onClick = {
                        val intent = Intent(context, RecetteActivity::class.java)
                        intent.putExtra("drinkId", drink.id)
                        intent.putExtra(
                            "fromCategory",
                            true
                        ) //faire retour sur la page precedente avec la fleche
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
                        text = drink.name,//drink.name,
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
}



package fr.isen.aurore.thegreatestcocktailApp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorModel
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.isen.aurore.thegreatestcocktailApp.ui.theme.TheGreatestCocktailAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                  /*  topBar = {
                        TopAppBar(snackbarHostState = snackbarHostState)
                    },*/
                    bottomBar = {
                        barre()
                    }
                ) { innerPadding ->
                    CategoriesScreen(
                  //      name = "Android",
                        modifier = Modifier.padding(innerPadding),
                       // snackbarHostState = snackbarHostState  //A METTRE POUR DetailCocktailScreen
                    )
                }
            }
        }
    }
}

@Composable
fun MonImage() {
       Image(
            painter = painterResource(id = R.drawable.chocolat),
            contentDescription = "Description de l'image chocolat",
            modifier = Modifier
                .clip(CircleShape)
                //.fillMaxWidth()
                .size(180.dp),
           contentScale = ContentScale.Crop
        )
}


@Composable
fun barre() { //barre du bas icones
    NavigationBar()
    {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("A la une") },
            selected = true,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = null) },
            label = { Text("Catégories") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
            label = { Text("Favoris") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = null) },
            label = { Text("Recherche") },
            selected = false,
            onClick = { }
        )
    }
}


//-------entrainement-----

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Button({
            Log.d("button", "click on button")
        })
        {
            Text(
                text = "Hello $name!",
                color = Color.Magenta
            )
            Text("hi")
        }
        Text("hiiiiii")
        Row(Modifier) { Text("Row...") }
        Column(Modifier) { Text("Column...") }
        MonImage()
    }

}

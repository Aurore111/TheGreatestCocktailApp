package fr.isen.aurore.thegreatestcocktailApp

import android.content.Intent
import android.icu.text.CaseMap
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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.traceEventStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorModel
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.isen.aurore.thegreatestcocktailApp.ui.theme.TheGreatestCocktailAppTheme

enum class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
){
  Home(title = "A la une", Icons.Default.Home, route = "Home"),
  List(title = "Catégories", Icons.Default.Menu, route = "List"),
  Fav(title = "Favoris", Icons.Default.Favorite, route = "Fav")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                val startNavigationItem = NavigationItem.Home
                val currentNavigationItem : MutableState<NavigationItem> = remember { mutableStateOf( value = startNavigationItem) }
              //  val favoriteIds = remember { mutableStateListOf<String>() }
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                  /*  topBar = { //c'etait pour avoir une barre blanche en haut
                        TopAppBar(snackbarHostState = snackbarHostState)
                    },*/
                    bottomBar = {
                        NavigationBar {
                            NavigationItem.entries.forEach {  navigationItem ->
                                NavigationBarItem(
                                    selected = currentNavigationItem.value == navigationItem,
                                    onClick = {
                                        navController.navigate(navigationItem.route)
                                        currentNavigationItem.value = navigationItem
                                    },
                                    label = {
                                        Text(text = navigationItem.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = navigationItem.icon,
                                            contentDescription = ""
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = startNavigationItem.route
                        ){
                        NavigationItem.entries.forEach { navigationItem ->
                            composable ( navigationItem.route ){
                                when (navigationItem){
                                    NavigationItem.Home -> DetailCocktailScreen(Modifier.padding( paddingValues = innerPadding), snackbarHostState)
                                    NavigationItem.List -> CategoriesScreen(Modifier.padding( paddingValues = innerPadding))
                                    NavigationItem.Fav -> LikeScreen(Modifier.padding( paddingValues = innerPadding)) //a completer avec la page de favoris
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

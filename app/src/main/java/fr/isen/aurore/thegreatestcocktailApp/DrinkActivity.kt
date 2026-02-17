package fr.isen.aurore.thegreatestcocktailApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.aurore.thegreatestcocktailApp.ui.theme.TheGreatestCocktailAppTheme

class DrinkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val category : String = intent.getStringExtra("category").toString()
        setContent {
            TheGreatestCocktailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   DrinkScreen(modifier = Modifier.padding(paddingValues = innerPadding), category = category)
                }
            }
        }
    }
}

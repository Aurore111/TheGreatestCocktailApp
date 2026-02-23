package fr.isen.aurore.thegreatestcocktailApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.aurore.thegreatestcocktailApp.ui.theme.TheGreatestCocktailAppTheme

class RecetteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheGreatestCocktailAppTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val drinkId = intent.getStringExtra("drinkId") ?: ""
                val fromCategory = intent.getBooleanExtra("fromCategory", false)
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                )
                { innerPadding ->
                    DetailCocktailScreen(modifier = Modifier.padding( innerPadding),
                        snackbarHostState = snackbarHostState,
                        drinkId = drinkId,
                        fromCategory = fromCategory
                        )
                }
            }
        }
    }
}


package fr.isen.aurore.thegreatestcocktailApp.`package`

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(value = "random.png")
    fun getRandomeCocktail(): Call<Drinks>
}
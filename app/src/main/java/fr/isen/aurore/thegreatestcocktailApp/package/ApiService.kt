package fr.isen.aurore.thegreatestcocktailApp.`package`

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(value = "random.php")
    fun getRandomeCocktail(): Call<Drinks>

    @GET(value = "list.php?c=list")
    fun getListCategory(): Call<Drinks>

}
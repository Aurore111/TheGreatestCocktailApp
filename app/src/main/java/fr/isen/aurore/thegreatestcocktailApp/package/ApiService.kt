package fr.isen.aurore.thegreatestcocktailApp.`package`

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(value = "random.php")
    fun getRandomeCocktail(): Call<Drinks>

    @GET(value = "list.php?c=list")
    fun getListCategory(): Call<Drinks>

    @GET(value = "filter.php")
    fun getDrinksByCategory(@Query(value = "c") category: String): Call<Drinks>

    @GET("lookup.php")
    fun getDrinkById(@Query("i") id: String): Call<Drinks>
}
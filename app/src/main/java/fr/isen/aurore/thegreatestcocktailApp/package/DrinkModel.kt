package fr.isen.aurore.thegreatestcocktailApp.`package`

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Drinks(
    val drinks: List<DrinksModel>
): Serializable

class DrinksModel(
    @SerializedName(value = "idDrink") val id: String = "",
    @SerializedName(value = "strDrink") val name: String = "",
    @SerializedName(value = "strCategory") val category: String = "",
    @SerializedName(value = "strAlcoholic") val alcoholic: String = "",
    @SerializedName(value = "strGlass") val glass: String = "",
    @SerializedName(value = "strInstructionsFR") val instructionFR: String = "",
    @SerializedName(value = "strIngredient1") val ingredient1: String = "",
    @SerializedName(value = "strIngredient2") val ingredient2: String = "",
    @SerializedName(value = "strIngredient3") val ingredient3: String = "",
    @SerializedName(value = "strIngredient4") val ingredient4: String = "",
    @SerializedName(value = "strIngredient5") val ingredient5: String = "",
    @SerializedName(value = "strIngredient6") val ingredient6: String = "",
    @SerializedName(value = "strIngredient7") val ingredient7: String = "",
    @SerializedName(value = "strIngredient8") val ingredient8: String = "",
    @SerializedName(value = "strIngredient9") val ingredient9: String = "",
    @SerializedName(value = "strIngredient10") val ingredient10: String = "",
    @SerializedName(value = "strIngredient11") val ingredient11: String = "",
    @SerializedName(value = "strIngredient12") val ingredient12: String = "",
    @SerializedName(value = "strIngredient13") val ingredient13: String = "",
    @SerializedName(value = "strIngredient14") val ingredient14: String = "",
    @SerializedName(value = "strIngredient15") val ingredient15: String = "",
    @SerializedName(value = "strMeasure1") val measure1: String = "",
    @SerializedName(value = "strMeasure2") val measure2: String = "",
    @SerializedName(value = "strMeasure3") val measure3: String = "",
    @SerializedName(value = "strMeasure4") val measure4: String = "",
    @SerializedName(value = "strMeasure5") val measure5: String = "",
    @SerializedName(value = "strMeasure6") val measure6: String = "",
    @SerializedName(value = "strMeasure7") val measure7: String = "",
    @SerializedName(value = "strMeasure8") val measure8: String = "",
    @SerializedName(value = "strMeasure9") val measure9: String = "",
    @SerializedName(value = "strMeasure10") val measure10: String = "",
    @SerializedName(value = "strMeasure11") val measure11: String = "",
    @SerializedName(value = "strMeasure12") val measure12: String = "",
    @SerializedName(value = "strMeasure13") val measure13: String = "",
    @SerializedName(value = "strMeasure14") val measure14: String = "",
    @SerializedName(value = "strMeasure15") val measure15: String = ""
//voir note et ecrire tout ce qui est utile (copier coller et modifier la ligne du dessus)

):Serializable
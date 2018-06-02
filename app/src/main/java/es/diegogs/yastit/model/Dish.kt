package es.diegogs.yastit.model

import es.diegogs.yastit.R
import java.util.*

enum class AllergenTypes(val value: Int) {
    PEANUT(R.drawable.icon_alergeno_cacahuete),
    SHELLFRUIT(R.drawable.icon_alergeno_frutos_cascara),
    GLUTEN(R.drawable.icon_alergeno_gluten),
    SESAME(R.drawable.icon_alergeno_granos_sesamo),
    EGG(R.drawable.icon_alergeno_huevo),
    FISH(R.drawable.icon_alergenos_pescado)
}

data class Dish(val dishId: String = UUID.randomUUID().toString(),
                val name: String,
                val description: String,
                val price: Float,
                val photo: Int,
                private val allergens: List<AllergenTypes>? = null) {

    fun getAllergenCount() = if (allergens != null) allergens.size
    else 0

    fun getAllergen(at: Int) = if (allergens != null) allergens[at]
    else null
}
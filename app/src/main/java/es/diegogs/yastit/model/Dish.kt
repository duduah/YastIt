package es.diegogs.yastit.model

import es.diegogs.yastit.R
import java.io.Serializable
import java.util.*

enum class AllergenTypes(val allergenId: Int) {
    PEANUT(R.id.icon_alergeno_cacahuete),
    SHELLFRUIT(R.id.icon_alergeno_frutos_cascara),
    GLUTEN(R.id.icon_alergeno_gluten),
    SESAME(R.id.icon_alergeno_granos_sesamo),
    EGG(R.id.icon_alergeno_huevo),
    FISH(R.id.icon_alergeno_pescado)
}

data class Dish(val dishId: String = UUID.randomUUID().toString(),
                val name: String,
                val description: String,
                val price: Float,
                val photo: Int,
                private val allergens: List<AllergenTypes> = listOf()) {

    fun getAllergenCount() = allergens.size

    fun getAllergen(at: Int) = allergens[at]

    fun getAllergens() = allergens
}
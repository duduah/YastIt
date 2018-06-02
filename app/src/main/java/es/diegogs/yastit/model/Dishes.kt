package es.diegogs.yastit.model

import es.diegogs.yastit.R

object Dishes {
    private val dishes: List<Dish> = listOf(
            Dish(name = "Sartén de guisantes, trigueros y champiñones",
                    description = "Quisantes frescos salteados en mantequilla junto con unos espárragos trigueros de la huerta y unos champiñones de monte. Se deja al comensal salpimentar al gusto.",
                    price = 15.50f,
                    photo = R.drawable.sarten_guisantes_trigueros),
            Dish(name = "Solomillo de cerdo a la Wellington",
                    description = "Solomillo de cerdo hojaldrado y hecho lentamente al horno, con una salsa de exquisito brandy añejo.",
                    price = 37.45f,
                    photo = R.drawable.solomillo_wellington,
                    allergens = listOf(AllergenTypes.EGG, AllergenTypes.GLUTEN)),
            Dish(name = "Sopa fría de coco, lima y pollo crujiente",
                    description = "Pechuga de pollo crujiente en tempura de arroz en una sopa a base de caldo de pollo y aceite de coco. Aderezada con hojas de lima keffir y una ramita de lemongrass.",
                    price = 25f,
                    photo = R.drawable.sopa_fria_coco_lima_pollo,
                    allergens = listOf(AllergenTypes.GLUTEN, AllergenTypes.SHELLFRUIT, AllergenTypes.PEANUT, AllergenTypes.SESAME)),
            Dish(name = "Pasta con pesto de mejorana y aceitunas",
                    description = "Pasta corta o galletti con salsa pesto hecha con espinacas, hojas de mejorana fresca y almendras. Parmesano al gusto del comensal.",
                    price = 20.50f,
                    photo = R.drawable.pasta_mejorana,
                    allergens = listOf(AllergenTypes.SHELLFRUIT)),
            Dish(name = "Caballa curada a la bilbaina",
                    description = "Lomos de caballa macerados en jalapeño, vinagre de Jerez y aceite de oliva virgen extra emplatados con escamas de sal y brote o germinados.",
                    price = 12.25f,
                    photo = R.drawable.caballa_curada_bilbaina,
                    allergens = listOf(AllergenTypes.FISH, AllergenTypes.GLUTEN)),
            Dish(name = "Crema fría de espinacas, piñones y yogur",
                    description = "Popular crema de espinacas hecha con yogur y aderezada con piñones y limón.",
                    price = 10f,
                    photo = R.drawable.crema_fria_espinacas,
                    allergens = listOf(AllergenTypes.SHELLFRUIT)),
            Dish(name = "De segundo. Sartén de guisantes, trigueros y champiñones",
                    description = "Quisantes frescos salteados en mantequilla junto con unos espárragos trigueros de la huerta y unos champiñones de monte. Se deja al comensal salpimentar al gusto.",
                    price = 15.50f,
                    photo = R.drawable.sarten_guisantes_trigueros),
            Dish(name = "Fruta de temporada: naranjas",
                    description = "Naranjas del huerto del tío José, famosas por su extraordinario saber y abundante jugo.",
                    price = 3f,
                    photo = R.drawable.naranjas),
            Dish(name = "Panna cotta de limón",
                    description = "La tradicional receta de la panna cotta con hierbabuena fresca.",
                    price = 5f,
                    photo = R.drawable.pannacotta,
                    allergens = listOf(AllergenTypes.GLUTEN))
    )

    fun count() = dishes.size

    fun getDishes() = dishes

    fun getDish(id: String) = dishes.find { dish ->
        dish.dishId == id
    }

    /**
     * Overloading get operator
     */
    operator fun get(index: Int) = dishes[index]
    
    fun toArray() = dishes.toTypedArray()
}
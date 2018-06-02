package es.diegogs.yastit.model

data class Table (
        val tableNumber: Int,
        private var dishes: MutableList<Dish> = mutableListOf<Dish>()) {

    fun addDish(newDish: Dish) {
        dishes.add(newDish)
    }

    fun getDishesCount() = dishes.size

    fun getDishes() = dishes

    fun getDish(index: Int) = dishes.get(index)
}
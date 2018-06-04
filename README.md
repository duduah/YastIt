# YastIt
Práctica de fundamentos Android.


## Aclaraciones
La Actividad DishActivity se usa para ver el detalle del plato de tres formas:

1. Nuevo plato a agregar a una mesa. Se verá en modo edición, esto es, mostrando el campo de texto para introducir variantes y los botones de OK/CANCEL.
1. Plato ya perteneciente a una mesa. Se verá en modo edición al igual que en el caso anterior, permitiendo modiciar las variantes del plato en el campo de texto.
1. Detalle del plato. Sin más, para cuando se llame desde el menú sin que se vaya a asociar a ninguna mesa. Aunque está implementado me queda pendiente hacer un menú inicial que se abra desde la lista de mesas para ver el menú y desde ahí abrir el plato de esta forma, sin que se asocie a ninguna mesa.

## TODOs
### Pasar Activities a Fragments
La práctica se inició a base de Activities para posteriormente pasar a Fragments aquellas que se fuesen a reutilizar en otras versiones o tamaños de dispositivos.  
Lamentablemente no ha habido tiempo suficiente para hacerlo en todas las necesarias. Se ha implementado el **TableListFragment** y en un futuro se espera evolucionar el resto para añadir vistas con calificadores.

### CardView
Inicialmente utilicé la CardView para mostrar la lista de platos, pero por la forma en que he hecho el diseño veo mejor no incluirla.

### Undo con SnackBar
No me ha dado tiempo a implementar un *Undo* con SnackBar cuando se incluya un plato nuevo.

### Opción de borrar platos de la mesa.
Permitir eliminar algún plato asociado a la mesa.

### Limpiar la lista de platos de una mesa.
Permitir dar la opción en la actividad de la factura de pulsar un botón **Pagado** para eliminar los platos asociados a dicha mesa.

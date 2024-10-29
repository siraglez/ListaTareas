# ListaTareas
 
Link al repostorio: https://github.com/siraglez/ListaTareas.git

## Descripción del Código

Este proyecto es una aplicación simple de lista de tareas que permite gestionar tareas pendientes y hechas, además de cambiar el idioma de la interfaz entre español e inglés.

### Estructura de la Aplicación

1. **MainActivity**: Es la actividad principal donde el usuario puede agregar tareas, ver las tareas pendientes y las hechas, y cambiar el idioma de la aplicación.

2. **TareasPendientesActivity** y **TareasHechasActivity**: Actividades secundarias que muestran listas de tareas pendientes y tareas hechas respectivamente, y permiten regresar a la pantalla principal. 

### Funcionamiento de la Aplicación

#### MainActivity

- **Inicialización de Variables**: Se declaran y enlazan los elementos de la interfaz: EditText para ingresar tareas (`etTask`), botones para agregar tareas (`btnAdd`), mostrar tareas pendientes (`btnPending`) y hechas (`btnDone`), y un ToggleButton para cambiar de idioma (`btnIdioma`).

- **Cambio de Idioma**: La aplicación permite cambiar el idioma entre español e inglés usando el `ToggleButton`. Al cambiar el estado del botón, se guarda la preferencia en `SharedPreferences` y se recrea la actividad para aplicar el cambio de idioma.

- **Agregar Tarea**: Al hacer clic en el botón de agregar (`btnAdd`), la tarea ingresada en `etTask` se agrega a la lista de tareas pendientes (`pendingTasks`). Si el campo está vacío, se muestra un mensaje pidiendo que ingrese una tarea.

- **Mostrar Tareas Pendientes o Hechas**: Los botones `btnPending` y `btnDone` abren las actividades `TareasPendientesActivity` y `TareasHechasActivity` respectivamente, pasando las listas de tareas correspondientes mediante un Intent.

- **Marcar Tareas como Hechas**: Al hacer clic en una tarea en la lista de `pendingTasks`, se despliega un diálogo con opciones para eliminar o marcar la tarea como hecha. Si se marca como hecha, la tarea se mueve de la lista de pendientes a la lista de hechas.

#### TareasPendientesActivity y TareasHechasActivity

- Ambas actividades muestran las listas de tareas pasadas desde `MainActivity` en un `ListView` con un `ArrayAdapter`.

- Cada actividad incluye un botón para regresar a `MainActivity`.

### Funciones Principales

- **cambiarIdioma(idioma: String)**: Cambia el idioma de la aplicación configurando un nuevo `Locale`, guardando la preferencia en `SharedPreferences` y recreando la actividad para aplicar el cambio.

- **showTaskOptions(position: Int)**: Muestra un diálogo con opciones de eliminar o marcar como hecha la tarea seleccionada en `pendingTasks`. 

### Resumen de Almacenamiento

- **Preferencias de Usuario**: El idioma seleccionado se almacena en `SharedPreferences` para que la aplicación mantenga la preferencia de idioma al reiniciarse.

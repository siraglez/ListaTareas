package com.example.listatareas

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etTask: EditText
    private lateinit var btnAdd: Button
    private lateinit var lvTasks: ListView
    private lateinit var btnPending: Button
    private lateinit var btnDone: Button

    private val pendingTasks = mutableListOf<String>()
    private val doneTasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTask = findViewById(R.id.etTask)
        btnAdd = findViewById(R.id.btnAdd)
        lvTasks = findViewById(R.id.lvTasks)
        btnPending = findViewById(R.id.btnPending)
        btnDone = findViewById(R.id.btnDone)

        // Adapter para la lista de tareas
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pendingTasks)
        lvTasks.adapter = adapter

        // Botón para agregar tarea
        btnAdd.setOnClickListener {
            val task = etTask.text.toString()
            if (task.isNotEmpty()) {
                pendingTasks.add(task)
                etTask.text.clear()
                updateTaskList()
            } else {
                Toast.makeText(this, "Por favor ingresa una tarea", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para ver tareas pendientes
        btnPending.setOnClickListener {
            val intent = Intent(this, TareasPendientesActivity::class.java)
            intent.putStringArrayListExtra("PENDING_TASKS", ArrayList(pendingTasks))
            startActivity(intent)
        }

        // Botón para ver tareas hechas
        btnDone.setOnClickListener {
            val intent = Intent(this, TareasHechasActivity::class.java)
            intent.putStringArrayListExtra("DONE_TASKS", ArrayList(doneTasks))
            startActivity(intent)
        }

        // Evento de clic en la lista de tareas
        lvTasks.setOnItemClickListener { _, _, position, _ ->
            showTaskOptions(position)
        }
    }

    private fun updateTaskList() {
        adapter.notifyDataSetChanged()
    }

    private fun showTaskOptions(position: Int) {
        val task = pendingTasks[position]
        val options = arrayOf("Eliminar", "Hecho")

        AlertDialog.Builder(this)
            .setTitle(task)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> { // Eliminar
                        pendingTasks.removeAt(position)
                        updateTaskList()
                    }
                    1 -> { // Marcar como hecho
                        doneTasks.add(task)
                        pendingTasks.removeAt(position)
                        updateTaskList()
                    }
                }
            }
            .show()
    }
}

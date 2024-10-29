package com.example.listatareas

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val pendingTasks = mutableListOf<String>()
    private val doneTasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>
    private var showingPending = true

    // Declara las vistas que vas a usar
    private lateinit var lvTasks: ListView
    private lateinit var btnAdd: Button
    private lateinit var etTask: EditText
    private lateinit var btnPending: Button
    private lateinit var btnDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vincula las vistas usando findViewById
        lvTasks = findViewById(R.id.lvTasks)
        btnAdd = findViewById(R.id.btnAdd)
        etTask = findViewById(R.id.etTask)
        btnPending = findViewById(R.id.btnPending)
        btnDone = findViewById(R.id.btnDone)

        // Configura el adaptador para el ListView de tareas
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pendingTasks)
        lvTasks.adapter = adapter
        registerForContextMenu(lvTasks)

        // Botón para añadir una nueva tarea
        btnAdd.setOnClickListener {
            val task = etTask.text.toString()
            if (task.isNotBlank()) {
                pendingTasks.add(task)
                adapter.notifyDataSetChanged()
                etTask.text.clear()
            } else {
                Toast.makeText(this, getString(R.string.add_task_hint), Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para mostrar tareas pendientes
        btnPending.setOnClickListener {
            showingPending = true
            adapter.clear()
            adapter.addAll(pendingTasks)
            adapter.notifyDataSetChanged()
        }

        // Botón para mostrar tareas hechas
        btnDone.setOnClickListener {
            showingPending = false
            adapter.clear()
            adapter.addAll(doneTasks)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.task_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedTask = adapter.getItem(info.position) ?: return super.onContextItemSelected(item)

        when (item.itemId) {
            R.id.menu_done -> {
                if (showingPending) {
                    pendingTasks.remove(selectedTask)
                    doneTasks.add(selectedTask)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, getString(R.string.mark_as_done), Toast.LENGTH_SHORT).show()
                }
                return true
            }
            R.id.menu_delete -> {
                pendingTasks.remove(selectedTask)
                doneTasks.remove(selectedTask)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, getString(R.string.delete), Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}

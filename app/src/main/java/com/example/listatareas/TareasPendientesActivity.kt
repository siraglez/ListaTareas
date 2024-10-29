package com.example.listatareas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TareasPendientesActivity : AppCompatActivity() {

    private lateinit var lvPendingTasks: ListView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas_pendientes)

        lvPendingTasks = findViewById(R.id.lvPendingTasks)
        btnBack = findViewById(R.id.btnBack)

        // Obtener tareas pendientes de la MainActivity
        val pendingTasks = intent.getStringArrayListExtra("PENDING_TASKS") ?: arrayListOf()

        // Adapter para la lista de tareas pendientes
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pendingTasks)
        lvPendingTasks.adapter = adapter

        // Botón para regresar a la pantalla principal
        btnBack.setOnClickListener {
            finish()
        }
    }
}

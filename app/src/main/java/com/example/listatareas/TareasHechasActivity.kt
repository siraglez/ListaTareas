package com.example.listatareas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TareasHechasActivity : AppCompatActivity() {

    private lateinit var lvDoneTasks: ListView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas_hechas)

        lvDoneTasks = findViewById(R.id.lvDoneTasks)
        btnBack = findViewById(R.id.btnBack)

        // Obtener tareas hechas de la MainActivity
        val doneTasks = intent.getStringArrayListExtra("DONE_TASKS") ?: arrayListOf()

        // Adapter para la lista de tareas hechas
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, doneTasks)
        lvDoneTasks.adapter = adapter

        // Botón para regresar a la pantalla principal
        btnBack.setOnClickListener {
            finish()
        }
    }
}

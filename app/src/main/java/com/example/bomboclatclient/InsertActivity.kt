package com.example.bomboclatclient

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class InsertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            val nome = findViewById<EditText>(R.id.editTextNome).text.toString()
            val cognome = findViewById<EditText>(R.id.editTextCognome).text.toString()

            if (nome.isBlank() || cognome.isBlank()) {
                Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.188.31:5000/api/persone"
            val body = JSONObject()
            body.put("nome", nome)
            body.put("cognome", cognome)
            body.put("dataNascita", "2005-10-10")

            val request = JsonObjectRequest(Request.Method.POST, url, body, {
                Toast.makeText(this, "Utente aggiunto correttamente", Toast.LENGTH_SHORT).show()
            }, { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
            })
            queue.add(request)
        }
    }
}
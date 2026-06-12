package com.example.revistasuteq

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.revistasuteq.adapters.RevistaAdapter
import com.example.revistasuteq.models.Revistas
import com.example.revistasuteq.network.MySingleton
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var lvRevistas: ListView
    private lateinit var progressBar: ProgressBar
    private val revistasList = mutableListOf<Revistas>()
    private lateinit var adapter: RevistaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        lvRevistas = findViewById(R.id.lvRevistas)
        progressBar = findViewById(R.id.progressBar)

        adapter = RevistaAdapter(revistasList) { revista ->
            val intent = Intent(this, IssuesActivity::class.java)
            intent.putExtra("JOURNAL_ID", revista.j_id)
            startActivity(intent)
        }
        lvRevistas.adapter = adapter

        fetchRevistas()
    }

    private fun fetchRevistas() {
        progressBar.visibility = View.VISIBLE
        val url = "https://revistas.uteq.edu.ec/ws/journals.php"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                progressBar.visibility = View.GONE
                parseResponse(response)
            },
            { error ->
                progressBar.visibility = View.GONE
                Snackbar.make(lvRevistas, "Error de conexión", Snackbar.LENGTH_LONG).show()
            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }

    private fun parseResponse(response: JSONArray) {
        Log.d("JSON_RESPONSE", response.toString())
        revistasList.clear()
        if (response.length() == 0) {
            Snackbar.make(lvRevistas, "Sin resultados", Snackbar.LENGTH_LONG).show()
            return
        }

        for (i in 0 until response.length()) {
            val obj = response.getJSONObject(i)
            val rawDesc = obj.optString("description", "")
            val cleanDesc = Html.fromHtml(rawDesc, Html.FROM_HTML_MODE_COMPACT).toString().trim()

            val revista = Revistas(
                obj.optInt("journal_id", 0),
                obj.optString("name", "Sin título"),
                cleanDesc,
                "", // ISSN root key missing in root
                obj.optString("portada", "")
            )
            revistasList.add(revista)
        }
        adapter.notifyDataSetChanged()
    }
}
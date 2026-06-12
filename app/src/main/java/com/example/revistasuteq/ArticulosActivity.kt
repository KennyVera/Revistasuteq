package com.example.revistasuteq

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.revistasuteq.adapters.ArticuloAdapter
import com.example.revistasuteq.models.Volumens
import com.example.revistasuteq.network.MySingleton
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray

class ArticulosActivity : AppCompatActivity() {

    private lateinit var lvArticulos: ListView
    private lateinit var progressBar: ProgressBar
    private val articulosList = mutableListOf<Volumens>()
    private lateinit var adapter: ArticuloAdapter
    private var issueId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articulos)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        issueId = intent.getIntExtra("ISSUE_ID", -1)

        lvArticulos = findViewById(R.id.lvArticulos)
        progressBar = findViewById(R.id.progressBar)

        adapter = ArticuloAdapter(articulosList) { doi ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(doi))
            startActivity(browserIntent)
        }
        lvArticulos.adapter = adapter

        fetchArticulos()
    }

    private fun fetchArticulos() {
        progressBar.visibility = View.VISIBLE
        val url = "https://revistas.uteq.edu.ec/ws/pubs.php?i_id=$issueId"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                progressBar.visibility = View.GONE
                parseResponse(response)
            },
            { error ->
                progressBar.visibility = View.GONE
                Snackbar.make(lvArticulos, "Error de conexión", Snackbar.LENGTH_LONG).show()
            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }

    private fun parseResponse(response: JSONArray) {
        articulosList.clear()
        if (response.length() == 0) {
            Snackbar.make(lvArticulos, "Sin resultados", Snackbar.LENGTH_LONG).show()
            return
        }

        for (i in 0 until response.length()) {
            val obj = response.getJSONObject(i)
            
            // Parse authors array
            val authorsJson = obj.optJSONArray("authors")
            val authorNames = mutableListOf<String>()
            if (authorsJson != null) {
                for (j in 0 until authorsJson.length()) {
                    val authorObj = authorsJson.getJSONObject(j)
                    authorNames.add(authorObj.optString("nombres", "Desconocido"))
                }
            }
            val authorsDisplay = if (authorNames.isEmpty()) "Desconocido" else authorNames.joinToString(", ")

            val articulo = Volumens(
                obj.optInt("publication_id", obj.optInt("pub_id", 0)),
                obj.optInt("issue_id", 0),
                obj.optString("title", "Sin título"),
                authorsDisplay,
                obj.optString("doi", ""),
                obj.optString("abstract", "")
            )
            articulosList.add(articulo)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
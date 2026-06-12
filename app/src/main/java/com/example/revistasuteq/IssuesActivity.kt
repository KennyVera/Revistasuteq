package com.example.revistasuteq

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.revistasuteq.adapters.IssueAdapter
import com.example.revistasuteq.models.Issues
import com.example.revistasuteq.network.MySingleton
import com.google.android.material.snackbar.Snackbar
import org.json.JSONArray

class IssuesActivity : AppCompatActivity() {

    private lateinit var lvIssues: ListView
    private lateinit var progressBar: ProgressBar
    private val issuesList = mutableListOf<Issues>()
    private lateinit var adapter: IssueAdapter
    private var journalId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        journalId = intent.getIntExtra("JOURNAL_ID", -1)

        lvIssues = findViewById(R.id.lvIssues)
        progressBar = findViewById(R.id.progressBar)

        adapter = IssueAdapter(issuesList) { issue ->
            val intent = Intent(this, ArticulosActivity::class.java)
            intent.putExtra("ISSUE_ID", issue.i_id)
            startActivity(intent)
        }
        lvIssues.adapter = adapter

        fetchIssues()
    }

    private fun fetchIssues() {
        progressBar.visibility = View.VISIBLE
        val url = "https://revistas.uteq.edu.ec/ws/issues.php?j_id=$journalId"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                progressBar.visibility = View.GONE
                parseResponse(response)
            },
            { error ->
                progressBar.visibility = View.GONE
                Snackbar.make(lvIssues, "Error de conexión", Snackbar.LENGTH_LONG).show()
            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)
    }

    private fun parseResponse(response: JSONArray) {
        issuesList.clear()
        if (response.length() == 0) {
            Snackbar.make(lvIssues, "Sin resultados", Snackbar.LENGTH_LONG).show()
            return
        }

        for (i in 0 until response.length()) {
            val obj = response.getJSONObject(i)
            val issue = Issues(
                obj.optInt("issue_id", 0),
                obj.optInt("journal_id", 0),
                obj.optString("volume", ""),
                obj.optString("number", ""),
                obj.optString("year", ""),
                obj.optString("title", "Sin título"),
                obj.optString("cover", ""),
                obj.optString("date_published", "")
            )
            issuesList.add(issue)
        }
        // Sort by year DESC
        issuesList.sortByDescending { it.year }
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
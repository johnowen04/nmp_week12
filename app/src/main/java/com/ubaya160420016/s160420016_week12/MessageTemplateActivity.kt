package com.ubaya160420016.s160420016_week12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_message_template.*

class MessageTemplateActivity : AppCompatActivity() {
    companion object {
        val EXTRA_TEMPLATE = "TEMPLATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_template)

        val templates = arrayListOf<String>(
            "Let’s meet up!", "Have you worked on the project?",
            "Movie time?", "Busy, do not disturb",
            "Why you leave me?!", "Please pay me a visit. Urgent!",
            "Please call me back"
        )

        val layoutManager = LinearLayoutManager(this)
        recView.layoutManager = layoutManager
        recView.setHasFixedSize(true)
        recView.adapter = TemplateAdapter(templates, this)
    }
}
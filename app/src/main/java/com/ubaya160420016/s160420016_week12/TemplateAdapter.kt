package com.ubaya160420016.s160420016_week12

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.template_card.view.*

class TemplateAdapter(var templates: ArrayList<String>, var activity: Activity)
    : RecyclerView.Adapter<TemplateAdapter.TemplateViewHolder>() {

    class TemplateViewHolder(val view: View, var activity: Activity): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.template_card, parent, false)
        return TemplateViewHolder(view, activity)
    }

    override fun onBindViewHolder(holder: TemplateViewHolder, position: Int) {
        val template = templates[position]

        with(holder) {
            view.textTemplate.text = template
            view.btnPick.setOnClickListener {
                val intent = Intent()
                intent.putExtra(MessageTemplateActivity.EXTRA_TEMPLATE, template)
                activity.setResult(Activity.RESULT_OK, intent)
                activity.finish()
            }
        }
    }

    override fun getItemCount(): Int = templates.size
}
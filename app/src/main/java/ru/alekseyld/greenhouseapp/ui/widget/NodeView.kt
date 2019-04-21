package ru.alekseyld.greenhouseapp.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_node.view.*
import ru.alekseyld.greenhouseapp.R

class NodeView(c: Context, attributeSet: AttributeSet) : LinearLayout(c, attributeSet) {

    var onCheckedChange : ((isChecked : Boolean) -> Unit)? = null

    var valueText : String = ""
        set(value) {
            field = value
            valueTextView.text = value
        }

    init {
        inflate(context, R.layout.view_node, this)

        parseAttributeSet(attributeSet)
    }

    private fun parseAttributeSet(attributeSet: AttributeSet) {
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.NodeView, 0, 0)

        for (i in 0 until ta.indexCount) {
            val attribute = ta.getIndex(i)
            when (attribute) {
                R.styleable.NodeView_node_title -> {
                    title.text = ta.getString(attribute) ?: ""
                }
                R.styleable.NodeView_node_preview_text -> {
                    if (!isInEditMode) {
                        valueTextView.text = ta.getString(attribute) ?: ""
                    }
                }
                R.styleable.NodeView_node_background -> {
                    layout_back.background = ta.getDrawable(attribute)
                }
                R.styleable.NodeView_node_text_color -> {
                    val color = ta.getColor(attribute, 0)
                    title.setTextColor(color)
                    valueTextView.setTextColor(color)
                    icon.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY)
                }
                R.styleable.NodeView_node_mode -> {
                    selectMode(ta.getInt(attribute, 0))
                }
            }
        }
        ta.recycle()
    }

    private fun selectMode(mode: Int) {
        when (mode) {
            0 -> { //temp
                icon.setImageResource(R.drawable.ic_weather_temp)
            }
            1 -> { //hydro
                icon.setImageResource(R.drawable.ic_weather_drop)
            }
            2 -> { //sun
                icon.setImageResource(R.drawable.ic_weather_sun)
            }
            3 -> { //checkbox

            }
            4 -> { //switch
                switchWidget.visibility = View.VISIBLE
                switchWidget.setOnCheckedChangeListener { _, isChecked ->
                    run {
                        onCheckedChange?.invoke(isChecked)
                        valueText = if (isChecked) "Включен" else "Выключен"
                    }
                }
            }
        }
    }

}
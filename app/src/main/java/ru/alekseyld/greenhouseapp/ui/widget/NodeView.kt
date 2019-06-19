package ru.alekseyld.greenhouseapp.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_node.view.*
import ru.alekseyld.greenhouseapp.R
import ru.alekseyld.greenhouseapp.repository.IEspRepository

class NodeView(c: Context, attributeSet: AttributeSet) : LinearLayout(c, attributeSet) {

    var onCheckedChange : ((isChecked : Boolean) -> Unit)? = null

    var isLoading : Boolean = false
        set(value) {
            field = value
            progressBar.visibility = if (value) View.VISIBLE else View.GONE
        }

    private var valueText : String = ""
        set(value) {
            field = value
            valueTextView.text = value
        }

    private var isTurn : Boolean = false
        set(value) {
            field = value
            valueText = if (value) onText else offText

            if (switchWidget.isChecked != value) {
                switchWidget.isChecked = value
            }

            onbutton.text = if (value) "Закрыть" else "Открыть"
        }

    private var onText : String = "Включен"
    private var offText : String = "Выключен"

    private var onValue : IEspRepository.State = IEspRepository.State.On
    private var offValue : IEspRepository.State = IEspRepository.State.Off

    init {
        inflate(context, R.layout.view_node, this)

        parseAttributeSet(attributeSet)
    }

    fun getInverseState() : IEspRepository.State
        = if(isTurn) offValue else onValue


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

                if (isInEditMode) valueText = "25 °C"
            }
            1 -> { //hydro
                icon.setImageResource(R.drawable.ic_weather_drop)

                onText = "Сухо"
                offText = "Влажно"

                if (isInEditMode) isTurn = false
            }
            2 -> { //sun
                icon.setImageResource(R.drawable.ic_weather_sun)

                if (isInEditMode) valueText = "50 %"
            }
            3 -> { //checkbox
                icon.visibility = View.GONE

                onText = "Достигнут"
                offText = "Не достигнут"

                if (isInEditMode) isTurn = false
            }
            4 -> { //switch
                icon.visibility = View.GONE
                switchWidget.visibility = View.VISIBLE
                switchWidget.setOnCheckedChangeListener { _, isChecked ->
                    run {
                        onCheckedChange?.invoke(isChecked)
                        isTurn = isChecked
                    }
                }

                if (isInEditMode) isTurn = false
            }

            5 -> { //button
                icon.visibility = View.GONE
                onbutton.visibility = View.VISIBLE

                onText = "Открыто"
                offText = "Закрыто"

                onValue = IEspRepository.State.Open
                offValue = IEspRepository.State.Close

                onbutton.setOnClickListener {
                    onCheckedChange?.invoke(!isTurn)
                    isTurn = !isTurn
                }
            }
        }
    }

    private fun onUpdateParam() {
        isLoading = false
    }

    fun setStringParam(value: Any?, postfix: String = "") {
        value?.toString()?.let {
            this.valueText = "$it$postfix"
        }

        onUpdateParam()
    }

    fun setTurnParam(value: Boolean?) {
        value?.let {
            this.isTurn = it
        }

        onUpdateParam()
    }
}
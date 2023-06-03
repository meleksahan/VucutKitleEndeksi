package com.example.vucutkitleendeksi5

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.vucutkitleendeksi5.R

class MainActivity : AppCompatActivity(), TextWatcher {
    private var editText: EditText? = null
    private var boy_tv: TextView? = null
    private var durum_tv: TextView? = null
    private var ideal_tv: TextView? = null
    private var kilo_tv: TextView? = null
    private var seekBar: SeekBar? = null
    private var radioGroup: RadioGroup? = null
    private var erkekmi = true
    private var boy = 0.0
    private var kilo = 50.0

    private val radioGroupOlayIsleyicisi: RadioGroup.OnCheckedChangeListener =
        object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
                if (checkedId == R.id.bay) erkekmi = true
                else if (checkedId == R.id.bayan) erkekmi = false
                guncelle()
            }
        }

    private val seekBarOlayIsleyicisi: OnSeekBarChangeListener = object : OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            kilo = 30.0 + progress
            guncelle()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    private val editTextOlayIsleyicisi: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            boy = try {
                s.toString().toDouble() / 10.0
            } catch (e: NumberFormatException) {
                0.0
            }
            guncelle()
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editTextNumber)
        boy_tv = findViewById(R.id.boy_tv)
        durum_tv = findViewById(R.id.durum_tv)
        ideal_tv = findViewById(R.id.ideal_tv)
        kilo_tv = findViewById(R.id.kilo_tv)
        radioGroup = findViewById(R.id.radioGroup)
        seekBar = findViewById(R.id.seekBar)
        editText?.addTextChangedListener(editTextOlayIsleyicisi)
        seekBar?.setOnSeekBarChangeListener(seekBarOlayIsleyicisi)
        radioGroup?.setOnCheckedChangeListener(radioGroupOlayIsleyicisi)
        guncelle()
    }

    private fun guncelle() {
        kilo_tv?.text = "$kilo kg"
        boy_tv?.text = "$boy m"
        val ideal_kiloBay = (50 + 2.3 * (boy * 100 * 0.4 - 60)).toInt()
        val ideal_kiloBayan = (45.5 + 2.3 * (boy * 100 * 0.4 - 60)).toInt()
        val vki = kilo / (boy * boy)
        if (erkekmi) {
            ideal_tv?.text = ideal_kiloBay.toString()
            if (vki <= 20.7) {
                durum_tv?.setBackgroundResource(R.color.zayif)
                durum_tv?.setText(R.string.zayif)
            } else if (20.7 < vki && vki <= 26.4) {
                durum_tv?.setText(R.string.ideal)
                durum_tv?.setBackgroundResource(R.color.durum_ideal)
            } else if (26.4 < vki && vki <= 27.8) {
                durum_tv?.setText(R.string.normalden_fazla)
                durum_tv?.setBackgroundResource(R.color.durum_idealden_fazla)
            } else if (27.8 < vki && vki <= 31.1) {
                durum_tv?.setText(R.string.fazla_kilolu)
                durum_tv?.setBackgroundResource(R.color.durum_fazla_kilolu)
            } else if (31.1 < vki && vki <= 34.9) {
                durum_tv?.setText(R.string.obez)
                durum_tv?.setBackgroundResource(R.color.durum_obez)
            } else {
                durum_tv?.setText(R.string.doktora)
                durum_tv?.setBackgroundResource(R.color.durum_doktora)
            }
        } else {
            ideal_tv?.text = ideal_kiloBayan.toString()
            if (vki <= 19.1) {
                durum_tv?.setBackgroundResource(R.color.zayif)
                durum_tv?.setText(R.string.zayif)
            } else if (19.1 < vki && vki <= 25.8) {
                durum_tv?.setText(R.string.ideal)
                durum_tv?.setBackgroundResource(R.color.durum_ideal)
            } else if (25.8 < vki && vki <= 27.3) {
                durum_tv?.setText(R.string.normalden_fazla)
                durum_tv?.setBackgroundResource(R.color.durum_idealden_fazla)
            } else if (27.3 < vki && vki <= 32.3) {
                durum_tv?.setText(R.string.fazla_kilolu)
                durum_tv?.setBackgroundResource(R.color.durum_fazla_kilolu)
            } else if (32.3 < vki && vki <= 34.9) {
                durum_tv?.setText(R.string.obez)
                durum_tv?.setBackgroundResource(R.color.durum_obez)
            } else {
                durum_tv?.setText(R.string.doktora)
                durum_tv?.setBackgroundResource(R.color.durum_doktora)
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {}
}

package com.example.daychecker.activity

import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daychecker.R
import com.example.daychecker.adapter.CustomAdapter
import com.example.daychecker.model.Month
import com.github.clans.fab.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {

    lateinit var etDay: EditText
    lateinit var etYear: EditText
    lateinit var spMonth: Spinner
    lateinit var tvDayOfWeek: TextView
    lateinit var fabGetDate: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etDay = findViewById(R.id.etDay)
        etYear = findViewById(R.id.etYear)
        spMonth = findViewById(R.id.spMonths)
        tvDayOfWeek = findViewById(R.id.tvDayOfWeek)
        fabGetDate = findViewById(R.id.fab_get_day)
        spMonth.prompt = "Select a Month..."

        fabGetDate.setOnClickListener{
            if (validateForm()) {
                val day: Int = etDay.text.toString().toInt()
                val month: Month = spMonth.selectedItem as Month
                val year: String = etYear.text.toString()
                val m: String
                if (!month.month.equals(R.string.october.toString(), true) && !month.month.equals(R.string.november.toString(), true) &&
                        !month.month.equals(R.string.december.toString(), true)) {
                    m = "0" + month.iD
                } else
                    m = "" + month.iD
                val cal: Calendar = Calendar.getInstance()
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val d = "$day/$m/$year"
                try {
                    val date: Date = sdf.parse(d)
                    cal.time = date
                    val dayOfWeek: Int = cal.get(Calendar.DAY_OF_WEEK)
                    when (dayOfWeek) {
                        1 -> tvDayOfWeek.setText(R.string.sunday)
                        2 -> tvDayOfWeek.setText(R.string.monday)
                        3 -> tvDayOfWeek.setText(R.string.tuesday)
                        4 -> tvDayOfWeek.setText(R.string.wednesday)
                        5 -> tvDayOfWeek.setText(R.string.thursday)
                        6 -> tvDayOfWeek.setText(R.string.friday)
                        7 -> tvDayOfWeek.setText(R.string.saturday)
                        else -> tvDayOfWeek.text = ""
                    }
                } catch (e: Exception) {

                }
            } else
                Toast.makeText(this, "Please enter a valid day and year", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        populateMonths()
    }

    private fun validateForm(): Boolean {
        if (!etDay.text.toString().equals("")) {
            val day: Int = etDay.text.toString().toInt()
            return day in 1..31 && etYear.text.toString().length == 4
        } else
            return false
    }

    private fun populateMonths() {
        val months: ArrayList<Month> = ArrayList()
        months.add(Month(1, "January"))
        months.add(Month(2, "February"))
        months.add(Month(3, "March"))
        months.add(Month(4, "April"))
        months.add(Month(5, "May"))
        months.add(Month(6, "June"))
        months.add(Month(7, "July"))
        months.add(Month(8, "August"))
        months.add(Month(9, "September"))
        months.add(Month(10, "October"))
        months.add(Month(11, "November"))
        months.add(Month(12, "December"))
        val adapter = CustomAdapter(this, months)
        spMonth.adapter = adapter
    }
}
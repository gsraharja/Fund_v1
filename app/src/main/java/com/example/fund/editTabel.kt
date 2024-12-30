package com.example.fund

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class editTabel : AppCompatActivity() {

    private lateinit var dataUang: EditText
    private lateinit var tabungUang: RadioButton
    private lateinit var pengeluaranUang: RadioButton
    private lateinit var simpanUang: Button
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_tabel)

        // Inisialisasi view dari XML
        dataUang = findViewById(R.id.dataUang)
        tabungUang = findViewById(R.id.tabungUang)
        pengeluaranUang = findViewById(R.id.pengeluaranUang)
        simpanUang = findViewById(R.id.simpanUang)
        radioGroup = findViewById(R.id.radioGroup)

        // Batasi hanya satu radio button yang bisa dipilih
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.tabungUang) {
                pengeluaranUang.isChecked = false
            } else if (checkedId == R.id.pengeluaranUang) {
                tabungUang.isChecked = false
            }
        }

        // Set listener pada tombol Simpan
        simpanUang.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val inputUang = dataUang.text.toString()

        // Validasi input
        if (inputUang.isEmpty()) {
            dataUang.error = "Masukkan jumlah uang!"
            return
        }

        if (!tabungUang.isChecked && !pengeluaranUang.isChecked) {
            Toast.makeText(this, "Pilih tindakan Tabung atau Pengeluaran", Toast.LENGTH_SHORT).show()
            return
        }

        // Buat data yang akan dikirim ke Dashboard
        val action = if (tabungUang.isChecked) "Tabung" else "Pengeluaran"
        val color = if (tabungUang.isChecked) "Hijau" else "Merah"

        val intent = Intent(this, Dashboard::class.java)
        intent.putExtra("jumlahUang", inputUang)
        intent.putExtra("tindakan", action)
        intent.putExtra("warna", color)
        startActivity(intent)
    }
}

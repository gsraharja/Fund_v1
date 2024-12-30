package com.example.fund

import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class Dashboard : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private lateinit var editTabunganCard: CardView
    private var rowCount = 1 // Menghitung jumlah baris di tabel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Inisialisasi view dari layout
        tableLayout = findViewById(R.id.tableLayout)
        editTabunganCard = findViewById(R.id.editTabungan)

        // Navigasi ke editTabel saat CardView diklik
        editTabunganCard.setOnClickListener {
            val intent = Intent(this, editTabel::class.java)
            startActivity(intent)
        }

        // Ambil data dari Intent jika ada
        val jumlahUang = intent.getStringExtra("jumlahUang")
        val tindakan = intent.getStringExtra("tindakan")
        val warna = intent.getStringExtra("warna")

        // Tambahkan data ke tabel jika ada
        if (jumlahUang != null && tindakan != null && warna != null) {
            addNewRow(jumlahUang, tindakan, warna)
        }
    }

    private fun addNewRow(jumlahUang: String, tindakan: String, warna: String) {
        val newRow = TableRow(this)

        // Kolom No.
        val noText = TextView(this).apply {
            text = rowCount++.toString()
            setPadding(8, 8, 8, 8)
        }

        // Kolom Tanggal
        val tanggalText = TextView(this).apply {
            text = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
            setPadding(8, 8, 8, 8)
        }

        // Kolom Tabungan
        val tabunganText = TextView(this).apply {
            val displayText = if (tindakan == "Tabung") "+$jumlahUang" else "-$jumlahUang"
            text = displayText
            setPadding(8, 8, 8, 8)
            setTextColor(
                if (warna == "Hijau") resources.getColor(android.R.color.holo_green_dark, theme)
                else resources.getColor(android.R.color.holo_red_dark, theme)
            )
        }

        // Tambahkan kolom ke baris
        newRow.addView(noText)
        newRow.addView(tanggalText)
        newRow.addView(tabunganText)

        // Tambahkan baris ke tabel
        tableLayout.addView(newRow)
    }
}

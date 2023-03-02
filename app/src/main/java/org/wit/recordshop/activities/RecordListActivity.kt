package org.wit.recordshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.wit.recordshop.R
import org.wit.recordshop.main.MainApp

class RecordListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        app = application as MainApp
    }
}

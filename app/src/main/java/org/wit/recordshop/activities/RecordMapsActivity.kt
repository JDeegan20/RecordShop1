package org.wit.recordshop.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import androidx.navigation.ui.AppBarConfiguration
import org.wit.recordshop.databinding.ActivityRecordMapsBinding


class RecordMapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecordMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecordMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


    }

}

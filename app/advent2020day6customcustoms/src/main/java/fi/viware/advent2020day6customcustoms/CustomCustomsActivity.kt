package fi.viware.advent2020day6customcustoms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fi.viware.advent2020day6customcustoms.ui.main.CustomCustomsFragment

class CustomCustomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_customs)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CustomCustomsFragment.newInstance())
                .commitNow()
        }
    }
}
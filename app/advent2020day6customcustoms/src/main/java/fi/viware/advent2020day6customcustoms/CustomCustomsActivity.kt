package fi.viware.advent2020day6customcustoms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.internal.ContextUtils
import fi.viware.advent2020day6customcustoms.ui.main.CustomCustomsFragment
import java.io.File

private const val TAG = "CustomCustomsActivity"

class CustomCustomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_customs)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CustomCustomsFragment.newInstance())
                .commitNow()
        }

        // applicationContext is known in Activity
        //val file = File(applicationContext.filesDir,"answerlines.txt")
    }

}
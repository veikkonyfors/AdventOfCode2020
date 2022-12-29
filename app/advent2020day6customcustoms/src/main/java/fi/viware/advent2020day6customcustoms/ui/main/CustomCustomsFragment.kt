package fi.viware.advent2020day6customcustoms.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import fi.viware.advent2020day6customcustoms.AllAnswers
import fi.viware.advent2020day6customcustoms.R
import java.io.File

private const val TAG = "CustomCustomsFragment"

class CustomCustomsFragment : Fragment() {

    companion object {
        fun newInstance() = CustomCustomsFragment()
    }

    private lateinit var viewModel: CustomCustomsMainViewModel

    // Binding object instance with access to the views in the game_fragment.xml layout
    // private lateinit var binding: CustomCustomsFragmentBinding
    // Uudemmassatavassa pitäisi saada jotenkin buildattua toi CustomCustomsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup a click listener for the Submit and Skip buttons.
        // VN: joku uudempi tapa kait?
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener { onSkipWord() }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel

        val button:Button=requireView().findViewById(R.id.button2)
        button.setOnClickListener {
            viewModel = ViewModelProvider(this).get(CustomCustomsMainViewModel::class.java)
            requireView().findViewById<TextView>(R.id.textView2).setText(viewModel.countAnswers().toString())
            requireView().findViewById<TextView>(R.id.textView3).setText(viewModel.countUniqYesAnswers().toString())
        }
    }
}
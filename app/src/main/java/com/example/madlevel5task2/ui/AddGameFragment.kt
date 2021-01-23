package com.example.madlevel5task2.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.databinding.FragmentAddGameBinding
import com.example.madlevel5task2.model.Game
import com.example.madlevel5task2.viewmodel.GameViewModel
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate


class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    private val maxDay: Int = 31
    private val maxMonth: Int = 12
    private val minYear: Int = 4

    private lateinit var binding: FragmentAddGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddGame.setOnClickListener {
            onAddGame()
        }

    }

    private fun onAddGame() {
        val gameTitle = binding.etTitle.text.toString()
        val gamePlatform = binding.etPlatform.text.toString()
        val gameDay = binding.etDay.text.toString().toInt()
        val gameMonth = binding.etMonth.text.toString().toInt()
        val gameYear = binding.etYear.text.toString().toInt()

        if (gameTitle.isEmpty()) {
            Snackbar.make(binding.root, "Please fill in a title.", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (gamePlatform.isEmpty()) {
            Snackbar.make(binding.root, "Please fill in a platform.", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (gameDay > maxDay || gameMonth > maxMonth || gameYear < minYear) {
            Snackbar.make(binding.root, "Please fill in a valid date.", Snackbar.LENGTH_SHORT)
                .show()
            return
        }

        viewModel.insertGame(
            Game(
                gameTitle,
                gamePlatform,
                LocalDate.of(gameYear, gameMonth, gameDay)
            )
        )
        findNavController().popBackStack()
    }
}
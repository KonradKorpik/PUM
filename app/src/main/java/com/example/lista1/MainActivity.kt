package com.example.lista1

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Question(
    val title: String,
    val content: String,
    val answers: List<String>,
    val answer: String
)

class MainActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.button)
        val questionTextView = findViewById<TextView>(R.id.textView)
        val questionContentTextView = findViewById<TextView>(R.id.textView2)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val radioButton1 = findViewById<RadioButton>(R.id.radioButton)
        val radioButton2 = findViewById<RadioButton>(R.id.radioButton2)
        val radioButton3 = findViewById<RadioButton>(R.id.radioButton3)
        val radioButton4 = findViewById<RadioButton>(R.id.radioButton4)
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)

        questions = resources.getStringArray(R.array.questions).map { questionString ->
            val parts = questionString.split("|")
            Question(
                title = parts[0],
                content = parts[1],
                answers = parts.subList(2, 6),
                answer = parts[6]
            )
        }

        loadQuestion(0, questionTextView, questionContentTextView, radioButton1, radioButton2, radioButton3, radioButton4, progressBar)

        button.setOnClickListener {
            val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            if (selectedRadioButton != null) {
                val selectedAnswer = when (selectedRadioButton.id) {
                    R.id.radioButton -> "A"
                    R.id.radioButton2 -> "B"
                    R.id.radioButton3 -> "C"
                    R.id.radioButton4 -> "D"
                    else -> ""
                }
                if (selectedAnswer == questions[currentQuestionIndex].answer) {
                    score++
                }
            }

            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion(currentQuestionIndex, questionTextView, questionContentTextView, radioButton1, radioButton2, radioButton3, radioButton4, progressBar)
                radioGroup.clearCheck()
            } else {
                questionTextView.text = ""
                questionContentTextView.text = ""
                radioGroup.visibility = RadioGroup.GONE
                progressBar.visibility = ProgressBar.GONE
                scoreTextView.text = "Your score: $score / ${questions.size}"
                scoreTextView.visibility = TextView.VISIBLE
                button.text = "Restart"
                button.setOnClickListener {
                    restartQuiz(questionTextView, questionContentTextView, radioButton1, radioButton2, radioButton3, radioButton4, progressBar, scoreTextView, button, radioGroup)
                }
            }
        }
    }

    private fun loadQuestion(index: Int, questionTextView: TextView, questionContentTextView: TextView, radioButton1: RadioButton, radioButton2: RadioButton, radioButton3: RadioButton, radioButton4: RadioButton, progressBar: ProgressBar) {
        val currentQuestion = questions[index]
        questionTextView.text = currentQuestion.title
        questionContentTextView.text = currentQuestion.content
        radioButton1.text = currentQuestion.answers[0]
        radioButton2.text = currentQuestion.answers[1]
        radioButton3.text = currentQuestion.answers[2]
        radioButton4.text = currentQuestion.answers[3]
        progressBar.progress = (index + 1) * 10
    }

    private fun restartQuiz(questionTextView: TextView, questionContentTextView: TextView, radioButton1: RadioButton, radioButton2: RadioButton, radioButton3: RadioButton, radioButton4: RadioButton, progressBar: ProgressBar, scoreTextView: TextView, button: Button, radioGroup: RadioGroup) {
        currentQuestionIndex = 0
        score = 0
        loadQuestion(currentQuestionIndex, questionTextView, questionContentTextView, radioButton1, radioButton2, radioButton3, radioButton4, progressBar)
        radioGroup.clearCheck()
        radioGroup.visibility = RadioGroup.VISIBLE
        progressBar.visibility = ProgressBar.VISIBLE
        scoreTextView.visibility = TextView.GONE
        button.text = getString(R.string.next)
        button.setOnClickListener {
            val selectedRadioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            if (selectedRadioButton != null) {
                val selectedAnswer = when (selectedRadioButton.id) {
                    R.id.radioButton -> "A"
                    R.id.radioButton2 -> "B"
                    R.id.radioButton3 -> "C"
                    R.id.radioButton4 -> "D"
                    else -> ""
                }
                if (selectedAnswer == questions[currentQuestionIndex].answer) {
                    score++
                }
            }

            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion(currentQuestionIndex, questionTextView, questionContentTextView, radioButton1, radioButton2, radioButton3, radioButton4, progressBar)
                radioGroup.clearCheck()
            } else {
                questionTextView.text = ""
                questionContentTextView.text = ""
                radioGroup.visibility = RadioGroup.GONE
                progressBar.visibility = ProgressBar.GONE
                scoreTextView.text = "Twój wynik to: $score / ${questions.size}"
                scoreTextView.visibility = TextView.VISIBLE
                button.text = "Restart"
                button.setOnClickListener {
                    restartQuiz(questionTextView, questionContentTextView, radioButton1, radioButton2, radioButton3, radioButton4, progressBar, scoreTextView, button, radioGroup)
                }
            }
        }
    }
}

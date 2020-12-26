package com.example.calculatorapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.operator.Operator


class MainActivity : AppCompatActivity() {
    var one: TextView? = null
    var two: TextView? = null
    var three: TextView? = null
    var four: TextView? = null
    var five: TextView? = null
    var six: TextView? = null
    var seven: TextView? = null
    var eight: TextView? = null
    var nine: TextView? = null
    var zero: TextView? = null
    var ln: TextView? = null
    var plus: TextView? = null
    var minus: TextView? = null
    var multiply: TextView? = null
    var divide: TextView? = null
    var modulo: TextView? = null
    var equals: TextView? = null
    var leftBracket:TextView? =null
    var rightBracket:TextView? =null
    var changeSign: TextView? = null
    var e:TextView? = null
    var decimal: TextView? = null
    var ePower:TextView?=null
    var expression: TextView? = null
    var result: TextView? = null
    var ac: TextView? = null
    var backspace: ImageView? = null
    var sin:TextView?=null
    var cos:TextView?=null
    var tan:TextView?=null
    var pi:TextView? = null
    var Power : TextView? = null
    var log:TextView? = null
    var root:TextView? = null
    var abs:TextView? = null
    var fact:TextView? = null
    var cbrt:TextView? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        cbrt = findViewById(R.id.cbrt)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        multiply = findViewById(R.id.multiply)
        divide = findViewById(R.id.division)
        modulo = findViewById(R.id.modulo)
        equals = findViewById(R.id.equals)
        e = findViewById(R.id.e)
        changeSign = findViewById(R.id.change_sign)
        leftBracket = findViewById(R.id.leftBracket)
        rightBracket = findViewById(R.id.rightBracket)
        fact = findViewById(R.id.fact)
        decimal = findViewById(R.id.decimal)
        ePower = findViewById(R.id.ePower)
        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)

        ac = findViewById(R.id.clear)
        backspace = findViewById(R.id.backspace)
        sin = findViewById(R.id.sin)
        cos= findViewById(R.id.cos)
        tan= findViewById(R.id.tan)
        pi= findViewById(R.id.pi)
        Power= findViewById(R.id.Power)
        log= findViewById(R.id.log)
        root= findViewById(R.id.root)
        abs= findViewById(R.id.abs)
        ln = findViewById(R.id.ln)
        one?.setOnClickListener { appendText("1", true) }
        two?.setOnClickListener { appendText("2", true) }
        three?.setOnClickListener { appendText("3", true) }
        four?.setOnClickListener { appendText("4", true) }
        five?.setOnClickListener { appendText("5", true) }
        six?.setOnClickListener { appendText("6", true) }
        seven?.setOnClickListener { appendText("7", true) }
        eight?.setOnClickListener { appendText("8", true) }
        nine?.setOnClickListener { appendText("9", true) }
        zero?.setOnClickListener { appendText("0", true) }
        pi?.setOnClickListener { appendText("3.14159265", true) }
        e?.setOnClickListener { appendText("2.71828183", true) }
        leftBracket?.setOnClickListener { appendText("(", true) }
        rightBracket?.setOnClickListener { appendText(")", true) }
        plus?.setOnClickListener { appendText("+", false) }
        minus?.setOnClickListener { appendText("-", false) }
        multiply?.setOnClickListener { appendText("*", false) }
        divide?.setOnClickListener { appendText("/", false) }
        modulo?.setOnClickListener { appendText("%", false) }
        sin?.setOnClickListener { appendText("sin", false) }
        cos?.setOnClickListener { appendText("cos", false) }
        tan?.setOnClickListener { appendText("tan", false) }
        abs?.setOnClickListener { appendText("abs", false) }
        root?.setOnClickListener { appendText("sqrt", false) }
        cbrt?.setOnClickListener { appendText("cbrt", false) }
        log?.setOnClickListener { appendText("log10", false) }
        Power?.setOnClickListener { appendText("^", false) }
        var factorial: Operator = object : Operator("!", 1, true, PRECEDENCE_POWER + 1) {
            override fun apply(vararg args: Double): Double {
                val arg = args[0].toInt()
                require(arg.toDouble() == args[0]) { "Operand for factorial has to be an integer" }
                require(arg >= 0) { "The operand of the factorial can not be less than zero" }
                var result = 1.0
                for (i in 1..arg) {
                    result *= i.toDouble()
                }
                return result
            }
        }
        fact?.setOnClickListener { appendText("!",false) }
        decimal?.setOnClickListener { appendText(".", true) }
        ln?.setOnClickListener { appendText("log", false) }
        ePower?.setOnClickListener { appendText("exp", false) }
        changeSign?.setOnClickListener {
            result?.text = ""
            result?.hint = ""
            if (expression?.text?.length!! > 0 && expression?.text?.get(0) == '-') {
                expression?.text = expression?.text?.substring(1)
            } else {
                expression?.text = "-" + expression?.text
            }
        }

        equals?.setOnClickListener {
            try {
                val expr = ExpressionBuilder(expression?.text.toString()).operator(factorial).build()
                val answer = expr.evaluate()
                result?.text = answer.toString()
            } catch (e: Exception) {
                result?.text = "Wrong mathematical logic"
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        backspace?.setOnClickListener {
            result?.text = ""
            result?.hint = ""
            val value = expression?.text
            if (value?.isNotEmpty()!!) {
                expression?.text = value.substring(0, value.length - 1)
            }
        }

        ac?.setOnClickListener {
            expression?.text = ""
            result?.text = ""
            result?.hint = ""
        }
    }


    fun appendText(value: String, toBeCleared: Boolean) {

        if (result?.text != "") {
            expression?.text = ""
        }

        if (toBeCleared) {
            result?.text = ""
            expression?.append(value)
        } else {
            expression?.append(result?.text)
            expression?.append(value)
            result?.text = ""
        }

        result?.hint = expression?.text

    }
}
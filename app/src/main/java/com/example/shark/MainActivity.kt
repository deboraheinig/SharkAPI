package com.example.shark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: SharkAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val sharkApi = RetrofitHelper.getInstance().create(SharkService::class.java)

        layoutManager = LinearLayoutManager(this)

        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = layoutManager

        GlobalScope.launch {
            val result = sharkApi.getDeals()
            if (result != null) {
                runOnUiThread {
                findViewById<RecyclerView>(R.id.recyclerView).adapter = SharkAdapter(result)
                }
            }
        }
        //Função do Button
        findViewById<FloatingActionButton>(R.id.button).setOnClickListener {
            //Joguinhos em promoção -> Resultados para: (jogo digitado//text)
            findViewById<TextView>(R.id.resultados).visibility = View.VISIBLE
            val text = findViewById<TextInputEditText>(R.id.textEdit).text.toString()
            findViewById<TextView>(R.id.joguinhos).text = text

            findViewById<EditText>(R.id.textEdit).setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    //Perform Code
                        //Nova coroutine para a RecyclerView atualizada:
                    GlobalScope.launch {
                        //Recycler com itens especificos
                        val newResult = sharkApi.getDeals(text)
                        if (newResult != null) {
                            runOnUiThread {
                                findViewById<RecyclerView>(R.id.recyclerView).adapter = SharkAdapter(newResult)
                            }
                        }
                    }
                        return@OnKeyListener true
                }
                false
            })
        }
    }
}

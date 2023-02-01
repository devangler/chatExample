package com.ui.chattesting

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ui.chattesting.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class MainActivity : AppCompatActivity() {
    //
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AdaptorClass

    private var _originalList: ArrayList<User> = ArrayList()
    private val originalList: List<User> get() = _originalList.toList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {
                adapter = AdaptorClass(object : OnItemClickListener {
                    override fun onItemClick(user: User) {

                    }

                    override fun onDeleteItem(user: User) {
//
                    }
                })

            }
        }

        binding.imageView2.setOnClickListener {
            _originalList.add(
                User(
                    originalList.size + 1,
                    userText = "Asas",
                    "result[0]",
                    1
                )
            )

            binding.recyclerViewCard.adapter = adapter
            val newList = originalList.map { it.copy() } as ArrayList<User>
            //newList.reverse()
            adapter.submitList(newList) {
                binding.recyclerViewCard.smoothScrollToPosition(newList.size-1)
            }


        }
        binding.imageView3.setOnClickListener {
            _originalList.add(
                User(
                    originalList.size + 10,
                    userText = "ppppp",
                    "result[0]",
                    0
                )
            )


            binding.recyclerViewCard.adapter = adapter
            val newList = originalList.map { it.copy() } as ArrayList<User>
            //newList.reverse()
            adapter.submitList(newList) {
                binding.recyclerViewCard.smoothScrollToPosition(newList.size-1)
            }

        }

    }

    private fun promptSpeechInputLeft() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say some thing")
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000)
        try {
            startActivityForResult(intent, 1)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "speech_not_supported", Toast.LENGTH_SHORT).show()
        }
    }

    private fun promptSpeechInputRight() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say some thing")
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000)
        try {
            startActivityForResult(intent, 2)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(applicationContext, "speech_not_supported", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {

            if (resultCode == RESULT_OK && null != data) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                _originalList.add(
                    User(
                        1,
                        userText = result!![0].toString(),
                        "result[0]",
                        1
                    )
                )
                binding.recyclerViewCard.adapter = adapter
                val newList = originalList.map { it.copy() } as ArrayList<User>
                newList.reverse()
                adapter.submitList(newList) {
                    binding.recyclerViewCard.smoothScrollToPosition(newList.size-1)
                }
                if (result[0] != null) {
                    //  binding.textView5.add(result[0])

                }
            }

        } else {
            if (resultCode == RESULT_OK && null != data) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                _originalList.add(
                    User(
                        1,
                        userText = result!![0].toString(),
                        "result[0]",
                        0
                    )
                )


                binding.recyclerViewCard.adapter = adapter
                val newList = originalList.map { it.copy() } as ArrayList<User>
                newList.reverse()
                adapter.submitList(newList) {
                    binding.recyclerViewCard.smoothScrollToPosition(newList.size-1)
                }
                if (result[0] != null) {
                    //  binding.textView5.add(result[0])

                }
            }
        }
    }

}

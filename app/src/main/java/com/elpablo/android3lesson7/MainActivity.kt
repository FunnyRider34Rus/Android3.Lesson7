package com.elpablo.android3lesson7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.elpablo.android3lesson7.MainActivity.Companion.serverResponse
import com.elpablo.android3lesson7.model.APODDTO
import com.elpablo.android3lesson7.ui.theme.Android3Lesson7Theme
import com.elpablo.android3lesson7.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var serverResponse : APODDTO
        private lateinit var viewModel: MainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android3Lesson7Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    viewModel = ViewModelProvider(this)[MainViewModel::class.java]
                    val model = viewModel.sendServerRequest()

                    ShowCard(model)
                }
            }
        }
    }
}

@Composable
fun ShowCard(response: MainViewModel) {
    Column {
        AsyncImage(response.serverResponse?.hdurl, response.serverResponse?.title)
        Text(text = response.serverResponse?.title.toString())
        Text(text = response.serverResponse?.explanation.toString())
    }
}
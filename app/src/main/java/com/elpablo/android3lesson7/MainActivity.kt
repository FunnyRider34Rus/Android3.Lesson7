package com.elpablo.android3lesson7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.elpablo.android3lesson7.model.APODDTO
import com.elpablo.android3lesson7.ui.theme.Android3Lesson7Theme
import com.elpablo.android3lesson7.ui.theme.QuickSand
import com.elpablo.android3lesson7.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

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
fun ShowCard(response: APODDTO?) {
    Column {
        AsyncImage(response?.hdurl, response?.title)
        Text(
            text = response?.title.toString(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        )
        if (response != null) {
            Text(text =
            buildAnnotatedString {
                append(response.explanation)
                for (index in response.explanation.indices)
                    if (response.explanation[index].isUpperCase()) {
                        addStyle(
                            style = SpanStyle(
                                color = androidx.compose.ui.graphics.Color.Red,
                                fontSize = 16.sp,
                            ),
                            start = index,
                            end = index+1
                        )
                    }
            }, style = MaterialTheme.typography.bodySmall)
        }
    }
}
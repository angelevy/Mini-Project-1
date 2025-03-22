package com.angellevyne0045.miniproject1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.angellevyne0045.miniproject1.navigation.SetupNavGraph
import com.angellevyne0045.miniproject1.ui.theme.MiniProject1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProject1Theme {
                SetupNavGraph()
            }
        }
    }
}


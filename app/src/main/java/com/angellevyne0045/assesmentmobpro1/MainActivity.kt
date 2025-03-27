package com.angellevyne0045.assesmentmobpro1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.angellevyne0045.assesmentmobpro1.navigation.SetupNavGraph
import com.angellevyne0045.assesmentmobpro1.ui.theme.AssesmentMobpro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssesmentMobpro1Theme {
                SetupNavGraph()
            }
        }
    }
}
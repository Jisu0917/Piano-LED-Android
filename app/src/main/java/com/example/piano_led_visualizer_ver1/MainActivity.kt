package com.example.piano_led_visualizer_ver1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.piano_led_visualizer_ver1.ui.theme.PianoLEDVisualizer_ver1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PianoLEDVisualizer_ver1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        admin_id = 1,
                        user_id = "jisu",
                        name = "박지수",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }//end of onCreate
}

@Composable
fun Greeting(admin_id: Int, user_id: String, name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello :)\nadmin_id: $admin_id,\nuser_id: $user_id,\nname: $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PianoLEDVisualizer_ver1Theme {
        Greeting(2, "thv", "김태형")
    }
}
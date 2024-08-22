package com.example.piano_led_visualizer_ver1

import android.content.Intent
import android.os.Bundle
import androidx.compose.material3.Switch
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.piano_led_visualizer_ver1.ui.theme.PianoLEDVisualizer_ver1Theme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PianoLEDVisualizer_ver1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashboardView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun DashboardView(modifier: Modifier = Modifier.fillMaxSize()) {
        var isMenuOpen by remember { mutableStateOf(true) }
        var selectedItemIndex by remember { mutableStateOf(1) }

        // Define your arrays
        val menuArray = arrayOf("", "Home", "LED Settings", "Sequences", "Ports Settings", "LED Animations")
        val iconArray = arrayOf(
            R.drawable.ic_main,
            R.drawable.ic_home,
            R.drawable.ic_led1,
            R.drawable.ic_sequence,
            R.drawable.ic_port,
            R.drawable.ic_led2
        )
        val activityArray = arrayOf(
            "",
            "DashboardActivity",
            "LEDSettingsActivity",
            "SequencesActivity",
            "PortsSettingsActivity",
            "LEDAnimationsActivity"
        )

        // LocalConfiguration and LocalDensity for screen width
        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp.dp

        val density = LocalDensity.current.density
        val screenWidthPx = with(LocalDensity.current) { screenWidthDp.toPx() }
        val menuWidthPx = 0.55f * screenWidthPx
        val menuWidthDp = with(LocalDensity.current) { menuWidthPx.toDp() }

        // Switch의 상태를 저장하는 remember 상태 변수
        var isToggled: Boolean by remember { mutableStateOf(true) }

        Box(modifier = Modifier.fillMaxSize()) {
            // Main content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(
                        radiusX = if (isMenuOpen) 20.dp else 0.dp,
                        radiusY = if (isMenuOpen) 20.dp else 0.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(if (isMenuOpen) 8.dp else 0.dp))
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF27272A))
                        .padding(vertical = 40.dp, horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                                .pointerInput(Unit) {
                                    detectTapGestures(onTap = {
                                        isMenuOpen = !isMenuOpen
                                    })
                                },
                            horizontalArrangement = Arrangement.Absolute.Left,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_main),
                                contentDescription = "main icon",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .width(45.dp)
                                    .height(45.dp)
                            )
                            Spacer(modifier = Modifier.width(25.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_right),
                                contentDescription = "right icon",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(Color(0xff3f3f46), shape = RoundedCornerShape(20.dp))
                                .padding(4.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures(onTap = {
                                        isToggled = !isToggled
                                    })
                                }
                        ) {
                            // Track
                            Box(
                                modifier = Modifier
                                    .width(50.dp)
                                    .height(25.dp)
                                    .background(
                                        Color(0xff3f3f46),
                                        shape = RoundedCornerShape(20.dp)
                                    )
                            )
                            // Thumb with Icon
                            Box(
                                modifier = Modifier
                                    .width(23.dp)
                                    .height(23.dp)
                                    .background(
                                        if (isToggled) Color.Black else Color.White,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .align(if (isToggled) Alignment.CenterEnd else Alignment.CenterStart)
                                    .padding(vertical = 2.dp, horizontal = 2.dp)
                            ) {
                                // Add your custom icon here
                                Icon(
                                    painter = if (isToggled) painterResource(id = R.drawable.ic_night)
                                    else painterResource(id = R.drawable.ic_day),
                                    contentDescription = null,
                                    tint = if (isToggled) Color.White else Color.Black,
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(25.dp)
                            .background(Color(0xff3f3f46), shape = RoundedCornerShape(5.dp))
                            .padding(3.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_reload),
                                contentDescription = "reload icon",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .width(15.dp)
                                    .height(15.dp)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_drop),
                                contentDescription = "drop icon",
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(Color(0xff3f3f46), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_cpu),
                                    contentDescription = "cpu icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .width(25.dp)
                                        .height(25.dp)
                                )
                                Text(
                                    text = "43.3℃",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .width(65.dp)
                                        .height(28.dp)
                                        .background(
                                            Color(0xffef4444),
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(3.dp)
                                )
                            }
                            Text(
                                text = "4%",
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight(700),
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 10.dp)
                            )
                            Text(
                                text = "CPU Usage",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 0.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(Color(0xff3f3f46), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_ram),
                                    contentDescription = "ram icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .width(25.dp)
                                        .height(25.dp)
                                )
                                Text(
                                    text = "26.2%",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .width(65.dp)
                                        .height(28.dp)
                                        .background(
                                            Color(0xff10b981),
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(3.dp)
                                )
                            }
                            Text(
                                text = "55.03/430.09 MB",
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight(700),
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 10.dp)
                            )
                            Text(
                                text = "RAM Usage",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 0.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(Color(0xff3f3f46), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_disk),
                                    contentDescription = "disk icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .width(25.dp)
                                        .height(25.dp)
                                )
                                Text(
                                    text = "66.9%",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .width(65.dp)
                                        .height(28.dp)
                                        .background(
                                            Color(0xfff59e0b),
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(3.dp)
                                )
                            }
                            Text(
                                text = "2.13/3.34 GB",
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight(700),
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 10.dp)
                            )
                            Text(
                                text = "Disk Usage",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 0.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(Color(0xff3f3f46), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_bandwidth),
                                    contentDescription = "bandwidth icon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .width(25.dp)
                                        .height(25.dp)
                                )
                                Text(
                                    text = "189 Bytes/s",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(28.dp)
                                        .background(
                                            Color(0xff0ea5e9),
                                            shape = RoundedCornerShape(15.dp)
                                        )
                                        .padding(3.dp)
                                )
                            }
                            Text(
                                text = "306 Bytes/s",
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight(700),
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 10.dp)
                            )
                            Text(
                                text = "Bandwidth Usage",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 0.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(Color(0xff3f3f46), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 20.dp, vertical = 0.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = "Color Mode",
                                    color = Color.LightGray,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Left,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            start = 0.dp,
                                            end = 0.dp,
                                            top = 10.dp,
                                            bottom = 0.dp
                                        )
                                )
                                Text(
                                    text = "00.00",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .width(65.dp)
                                        .height(28.dp)
                                        .padding(3.dp)
                                        .graphicsLayer(alpha = 0f)  //visibility false
                                )
                            }
                            Text(
                                text = "55.03/430.09 MB",
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight(700),
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 10.dp)
                            )
                            Text(
                                text = "RAM Usage",
                                color = Color.LightGray,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Left,
                                modifier = Modifier
                                    .fillMaxWidth() // fillMaxWidth로 변경
                                    .padding(horizontal = 2.dp, vertical = 0.dp)
                            )
                        }
                    }
                }
            }

            // Navigation menu
            if (isMenuOpen) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(menuWidthDp) // Use calculated width here
                        .background(Color.Black)
                        .align(Alignment.CenterStart)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        menuArray.forEachIndexed { index, item ->
                            // Skip the first item as it does not have a Text
                            if (index == 0) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 20.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = iconArray[index]),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier
                                            .width(45.dp)
                                            .height(45.dp)
                                    )

                                    Icon(
                                        painter = painterResource(R.drawable.ic_x),
                                        contentDescription = null,
                                        tint = Color.LightGray,
                                        modifier = Modifier
                                            .width(15.dp)
                                            .height(15.dp)
                                            .pointerInput(Unit) {
                                                detectTapGestures(onTap = {
                                                    isMenuOpen = false
                                                })
                                            }
                                    )
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            if (selectedItemIndex == index) Color(0xff3f3f46) else Color.Transparent,
                                            shape = RoundedCornerShape(6.dp)
                                        )
                                        .padding(top = 12.dp, bottom = 12.dp, start = 10.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures(onTap = {
                                                selectedItemIndex = index
                                                // Handle the navigation
                                                val activityName = activityArray[index]
                                                if (activityName.isNotEmpty()) {
                                                    val intent = Intent(
                                                        this@DashboardActivity,
                                                        Class.forName("com.example.piano_led_visualizer_ver1.$activityName")
                                                    )
                                                    startActivity(intent)
                                                }
                                            })
                                        }
                                ) {
                                    Icon(
                                        painter = painterResource(id = iconArray[index]),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .width(25.dp)
                                            .height(25.dp)
                                            .padding(1.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = item,
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    }
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DashboardPreview() {
        PianoLEDVisualizer_ver1Theme {
            DashboardView()
        }
    }
}
package com.codelabs.composebasics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelabs.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }

}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember{ mutableStateOf(false)}
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(text = "Hello $name!", modifier = Modifier
        .padding(24.dp)
        .background(color = backgroundColor)
        .clickable(onClick = { isSelected = !isSelected })
    )
}

@Preview(showBackground = true)
@Composable
fun MyScreenContent(names: List<String> = List(1000){"Hello Android #$it"}) {
    val counterState = remember { mutableStateOf(0) }


    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names,Modifier.weight(1f))
        Counter(

            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}
@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting( name = name)
            Divider(color = Color.Black)

        }
    }
}
@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    Button(onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if ( count > 5) Color.Green else Color.White
        )
    ) {
        Text("I've been clicked $count times")
    }
}


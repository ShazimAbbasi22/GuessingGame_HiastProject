package com.example.testapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapp.ui.theme.TestAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GenerateRandomNumber()
                }
            }
        }
    }
}

@Composable
fun GenerateRandomNumber() {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var randomNumber by remember {
            mutableStateOf("")
        }
        var userNumber by remember {
            mutableStateOf("")
        }
        var submissionCount by remember { mutableIntStateOf(0) }
        val passwordVisible by remember { mutableStateOf(false) }
        val context = LocalContext.current

        Image(
            painter = painterResource(id = R.drawable.coin_toss),
            contentDescription = "logo",
            modifier = Modifier.padding(30.dp)
        )

        Text(
            text = "Guessing Game",
            modifier = Modifier,
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(80.dp))

        OutlinedTextField(
            value = randomNumber, onValueChange = {
                randomNumber = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = {
            randomNumber = (1..10).random().toString()
            Toast.makeText(context, "Number Generated", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Generate Number")
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = userNumber, onValueChange = {
                userNumber = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Button(onClick = {

            if (submissionCount < 3) {
                submissionCount++
                if (userNumber > randomNumber) {
                    Toast.makeText(context, "Too High", Toast.LENGTH_SHORT).show()
                } else if (userNumber < randomNumber) {
                    Toast.makeText(context, "Too Low", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show()
                    randomNumber = ""
                    userNumber = ""
                    submissionCount = 0
                }
            } else {
                Toast.makeText(context, "You have reach the limit", Toast.LENGTH_LONG).show()
                randomNumber = ""
                userNumber = ""
                submissionCount = 0


            }
        }) {
            Text(text = "Check Guess")
        }

        Button(onClick = {
            randomNumber = ""
            userNumber = ""
            submissionCount = 0
        }) {
            Text(text = "Reset")
        }


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAppTheme {
        GenerateRandomNumber()
    }
}
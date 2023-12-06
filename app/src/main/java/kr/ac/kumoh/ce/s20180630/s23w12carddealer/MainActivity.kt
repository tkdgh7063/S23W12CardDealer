package kr.ac.kumoh.ce.s20180630.s23w12carddealer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.ce.s20180630.s23w12carddealer.ui.theme.S23W12CardDealerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this)[CardViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            S23W12CardDealerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        /*CardImages()*/
                        CardSection(viewModel)
                        ShuffleButton(viewModel)
                    }
                }
            }
        }
    }
}

fun getCardName(c: Int): String {
    val shape = when (c/13) {
        0 -> "spades"
        1 -> "diamonds"
        2 -> "hearts"
        3 -> "clubs"
        else -> "error"
    }

    val number = when (c%13) {
        0 -> "ace"
        in 1..9 -> (c%13+1).toString()
        10 -> "jack"
        11 -> "queen"
        12 -> "king"
        else -> "error"
    }

    return "c_${number}_of_${shape}"
}

@Composable
fun ColumnScope.CardSection(viewModel: CardViewModel) {
    val cards by viewModel.cards.observeAsState()
    var cardResources = IntArray(5)

    for (i in cardResources.indices) {
        cardResources[i] = LocalContext.current.resources.getIdentifier(
            getCardName(cards?.get(i) ?: -1),
            "drawable",
            LocalContext.current.packageName
        )
    }

    CardImages(cardResources)
}

@Composable
fun ColumnScope.CardImages(res: IntArray) {
    if (LocalConfiguration.current.orientation
        == Configuration.ORIENTATION_LANDSCAPE) {
        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color(0, 100, 0))
        ) {
            Image(
                painter = painterResource(res[0]),
                contentDescription = "1st card",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[1]),
                contentDescription = "2nd card",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[2]),
                contentDescription = "3rd card",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[3]),
                contentDescription = "4th card",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
            Image(
                painter = painterResource(res[4]),
                contentDescription = "5th card",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(4.dp)
                    .weight(1f)
            )
        }
    }
    else {
        Column(
            modifier = Modifier
                .weight(1f)
                .background(Color(0, 100, 0))
        ) {
            Row(Modifier.weight(1f)) {
                Image(
                    painter = painterResource(res[0]),
                    contentDescription = "1st card",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
                Image(
                    painter = painterResource(res[1]),
                    contentDescription = "2nd card",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
                Image(
                    painter = painterResource(res[2]),
                    contentDescription = "3rd card",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
                Image(
                    painter = painterResource(res[3]),
                    contentDescription = "4th card",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
                Image(
                    painter = painterResource(res[4]),
                    contentDescription = "5th card",
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp)
                        .weight(1f)
                )
            }
        }
    }
}

@Composable
fun ShuffleButton(viewModel: CardViewModel) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0, 100, 0)),
        onClick = {viewModel.shuffle()}) {
        Text(stringResource(R.string.good_luck))
    }
}
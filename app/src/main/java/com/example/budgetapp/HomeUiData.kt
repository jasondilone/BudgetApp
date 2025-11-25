package com.example.budgetapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetapp.ui.theme.TealColor
import com.example.budgetapp.ui.theme.lightPurpleColor
import java.text.DecimalFormat

@Composable
fun HomeSpentAndBudget(spent: Double, budget: Double) {
    val formatter = DecimalFormat("#,###.00")
    val spentText = formatter.format(spent)
    val budgetText = formatter.format(budget)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // "Spent" box
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = lightPurpleColor,
            modifier = Modifier
                .width(180.dp)
                .height(90.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                "Spent",
                modifier = Modifier
                    .padding(all = 10.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White
            )
            Text(
                text = "$$spentText",
                modifier = Modifier
                    .padding(all = 30.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = autoFontSize(spentText),
                maxLines = 1
            )
        }

        // "Budget box
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = lightPurpleColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                "Budget",
                modifier = Modifier
                    .padding(all = 10.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White,
            )
            Text(
                text = "$$budgetText",
                modifier = Modifier
                    .padding(all = 30.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = autoFontSize(budgetText),
                maxLines = 1
            )
        }
    }
}

@Composable
fun HomeBudgetRing() {
    val spentPercentage: Float = 100 * (spent / budget).toFloat()
    val ringAngle: Float = 360 * (spent / budget).toFloat()
    val percentageNoDecimals = "%.0f".format(spentPercentage)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(200.dp)
        ) {
            Text(
                text = "$percentageNoDecimals%",
                color = Color.Black,
                fontSize = 40.sp
            )
            Canvas(modifier = Modifier.size(150.dp)) {

                // Outline of ring
                drawArc(
                    color = Color.Black,
                    startAngle = -270f,
                    sweepAngle = ringAngle,
                    useCenter = false,
                    style = Stroke(
                        width = 111.0f,
                        cap = StrokeCap.Round
                    )
                )

                // ring
                drawArc(
                    color = TealColor,
                    startAngle = -270f,
                    sweepAngle = ringAngle,
                    useCenter = false,
                    style = Stroke(
                        width = 100.0f,
                        cap = StrokeCap.Round
                    )
                )
            }
        }
    }
}

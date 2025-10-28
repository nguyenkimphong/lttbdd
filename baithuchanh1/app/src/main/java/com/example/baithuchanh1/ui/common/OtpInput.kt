package com.example.baithuchanh1.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.AnnotatedString



@Composable
fun OtpInput(
    value: String,
    onValueChange: (String) -> Unit,
    length: Int = 5
) {
    val focusRequesters = remember { List(length) { FocusRequester() } }
    var chars by remember { mutableStateOf(List(length) { "" }) }
    val focusManager = LocalFocusManager.current

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
        for (i in 0 until length) {
            BasicTextField(
                value = chars[i],
                onValueChange = { input ->
                    val ch = input.take(1).filter { it.isDigit() }
                    val newList = chars.toMutableList()
                    newList[i] = ch
                    chars = newList.toList()
                    val joined = chars.joinToString("").trim()
                    onValueChange(joined)
                    if (ch.isNotEmpty()) {
                        if (i < length - 1) focusRequesters[i + 1].requestFocus()
                        else focusManager.clearFocus()
                    }
                    if (ch.isEmpty() && i > 0) {
                        // when deleting, we might want to move focus back
                        focusRequesters[i].requestFocus()
                    }
                },
                modifier = Modifier
                    .size(56.dp)
                    .focusRequester(focusRequesters[i])
                    .border(1.dp, Color(0xFFDDDDDD), RoundedCornerShape(10.dp))
                    .padding(8.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (i == length - 1) ImeAction.Done else ImeAction.Next
                ),
                decorationBox = { inner ->
                    if (chars[i].isEmpty()) {
                        Text(text = "", modifier = Modifier)
                    }
                    inner()
                }
            )
        }
    }
}
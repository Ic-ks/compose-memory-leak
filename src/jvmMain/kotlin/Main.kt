// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

val randomValueFlow = flow { while (true) emit(Math.random() * 100) }
    .onEach { delay(10) }
    .map { it.toInt() }
    .flowOn(Dispatchers.Default)

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val randomValue by randomValueFlow.collectAsState(0L)
        Text(text = randomValue.toString(), fontSize = 20.sp)
    }
}

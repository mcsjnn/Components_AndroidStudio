package com.example.componentes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.componentes.ui.theme.ComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComponentesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopAppBarExample() },
        floatingActionButton = { FABExample() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            LazyRowExample()
            Spacer(modifier = Modifier.height(16.dp))
            GridExample()
            Spacer(modifier = Modifier.height(16.dp))
            AlertDialogExample()
        }
    }
}

@Composable
fun AlertDialogExample() {
    val openDialog = remember { mutableStateOf(false) }

    Button(
        onClick = { openDialog.value = true },
        colors = ButtonDefaults.buttonColors(Color.Blue)
    ) {
        Text("Pagar")
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text("Confirmar Pago") },
            text = { Text("¿Estás seguro de que deseas proceder con el pago?") },
            confirmButton = {
                Button(
                    onClick = { openDialog.value = false },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text("Confirmar", color = Color.White)
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialog.value = false },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text("Cancelar", color = Color.White)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExample() {
    CenterAlignedTopAppBar(
        title = { Text("Store Minaji", color = Color.White) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Blue)
    )
}

@Composable
fun FABExample() {
    FloatingActionButton(onClick = { /* Handle click */ }) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}

data class Option(val title: String)

@Composable
fun LazyRowExample() {
    val options = listOf(
        Option("Categoria 1"),
        Option("Categoria 2"),
        Option("Categoria 3")
    )

    LazyRow {
        items(options.size) { index ->
            Text(
                text = options[index].title,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Composable
fun GridExample() {
    val imageIds = listOf(
        R.drawable.imagen4,
        R.drawable.imagen5,
    )

    LazyVerticalGrid(columns = GridCells.Fixed(1)) {
        items(imageIds.size) { index ->
            ImageCard(imageId = imageIds[index], index = index)
        }
    }
}

@Composable
fun ImageCard(imageId: Int, index: Int) {
    Card(
        modifier = Modifier.padding(8.dp)
            .padding(4.dp)
            .width(100.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Imagen $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Opción $index")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComponentesTheme {
        MainScreen()
    }
}

package com.example.grocerylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItemData(
    var id : Int,
    var name : String,
    var quantity : Int,
    var isEditing : Boolean = false
) {}


@Composable
fun GroceryShopApp() {
    var sItems by remember { mutableStateOf(listOf<ShoppingItemData>()) }

    var showDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Items")
        }

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row (
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // SUBMIT BUTTON
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Submit")
                    }

                    // CANCEL BUTTON
                    Button(
                        onClick = { showDialog = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            },

            title = { Text(text = "Add Grocery Items") },
            text = {
                Column {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "Item Name")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text(text = "Item Quantity")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        )
    }
}








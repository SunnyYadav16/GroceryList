package com.example.grocerylist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    var itemName by remember {
        mutableStateOf("")
    }

    var itemQty by remember {
        mutableStateOf("")
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
           items(sItems) {
               currentItem ->
               if (currentItem.isEditing) {
                   GroceryItemEditor(item = currentItem, onEditComplete = {n, q -> {}})
               } else {
                   GroceryItemListView(
                       item = currentItem,
                       onEditClick = {
                   },
                   onDeleteClick = {
                       sItems = sItems - currentItem
                   })
               }
           }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    // SUBMIT BUTTON
                    Button(
                        onClick = {
                            val myNewItem = ShoppingItemData (
                                id = sItems.size + 1,
                                name = itemName,
                                quantity = itemQty.toInt()
                            )

                            sItems += myNewItem
                            itemQty = ""
                            itemName = ""
                            showDialog = false
                        }) {
                        Text(text = "Submit")
                    }

                    // CANCEL BUTTON
                    Button(
                        onClick = {
                            itemQty = ""
                            itemName = ""
                            showDialog = false
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            },
            title = { Text(text = "Add Grocery Items") },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = {
                            itemName = it
                        },
                        label = { Text(text = "Item Name")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    OutlinedTextField(
                        value = itemQty,
                        onValueChange = {
                            itemQty = it
                        },
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



@Composable
fun GroceryItemListView(
    item : ShoppingItemData,
    onEditClick : () -> Unit,
    onDeleteClick : () -> Unit
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                border = BorderStroke(2.dp, Color.Red),
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(text = "Item: ${item.name}", modifier = Modifier.padding(8.dp))
            Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
        }

        Row (modifier = Modifier.padding(15.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }

            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}


@Composable
fun GroceryItemEditor(
    item : ShoppingItemData,
    onEditComplete: (String, Int) -> Unit,
) {

    var editedName by remember {
        mutableStateOf(item.name)
    }

    var editedQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }

    var isEditing by remember {
        mutableStateOf(item.isEditing)
    }

    Row (
        modifier = Modifier.background(Color.Yellow).padding(4.dp)
    ) {
        Column () {
            OutlinedTextField(
                value = editedName,
                onValueChange = {
                    editedName = it
                },
                singleLine = true,
                modifier = Modifier.padding(8.dp)
            )

            OutlinedTextField(
                value = editedQuantity,
                onValueChange = {
                    editedQuantity = it
                },
                singleLine = true,
                modifier = Modifier.padding(8.dp)
            )

            Button(onClick = {
                isEditing = false
                onEditComplete(editedName, editedQuantity.toInt())
            }) {
                Text(text = "Save")
            }
        }

    }

}

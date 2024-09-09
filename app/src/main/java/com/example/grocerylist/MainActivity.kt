package com.example.grocerylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.grocerylist.ui.theme.GroceryListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GroceryListTheme {
                GroceryShopApp()
            }
        }
    }
}





// LAZY Column - only display some amount of data on the screen.
// Column - load everything at once on to the screen.



// Data class - special type of class used to hold the data
// 1. toString()
// 2. copy()
// 3. equals()


// LAZY COlumns
// Data class
// AlertDialogs bOX - Different use
// LISTS
// LAMBDA EXP



//grocery list / shopping list
//
//        button - add items
//
// -------------------------------------------
//        enter name of the item,
//        enter the quantity of the item
//        buttons - add/submit , cancel
// -------------------------------------------
//
//        View data in list format
//        ItemName Quantity (Button1 - edit, Button2 - delete)
//          Editable format - Name and Qty



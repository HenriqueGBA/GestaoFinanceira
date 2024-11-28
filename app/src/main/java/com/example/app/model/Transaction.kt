package com.example.app.model

data class Transaction(
    val title: String,
    val description: String,
    val amount: Double,
    val isPositive: Boolean
)

/*import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID autoincrementável
    val title: String,
    val description: String,
    val amount: Double,
    val isPositive: Boolean
)
[=
*/
package com.miu.housing.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//level: 1: normal-info, 2: medium-warning, 3: important-danger
@Entity
data class Faq(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var question: String,
    var answer: String,
    var level: Int?
    ): java.io.Serializable

package com.autught.chat.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(@PrimaryKey val userId:String) {
}
package com.autught.chat.vm

import androidx.lifecycle.ViewModel
import com.autught.chat.net.ApiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val model: ApiModel) : ViewModel() {
}
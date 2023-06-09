package com.autught.chat.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.autught.chat.remote.ApiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val model: ApiModel) : ViewModel() {

    fun fetch() {
        viewModelScope.launch {
            model.requestTest()
                .catch { /*处理异常*/ }
                .onStart {/*请求开始*/ }
                .onCompletion {/*请求结束*/ }
                .collect {/*处理结果*/ }
        }
    }
}
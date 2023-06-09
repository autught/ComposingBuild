package com.autught.chat

import android.os.Bundle
import androidx.activity.viewModels
import com.autught.chat.main.BaseActivity
import com.autught.chat.main.ILoading
import com.autught.chat.main.LoadingImp
import com.autught.chat.vm.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(),ILoading by LoadingImp {
    private val viewModel by viewModels<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            ChatTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ChatTheme {
//        Greeting("Android")
//    }
//}
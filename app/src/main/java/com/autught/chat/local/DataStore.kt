package com.autught.chat.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.autught.chat.main.AppConfig

//at the top level of your kotlin file
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(AppConfig.SP)

/**
 * example data
 * read:
 *      sync:
 *           val data = runBlocking { context.dataStore.data.map { it[logToken] }.first() }
 *        or
 *           override fun onCreate(savedInstanceState: Bundle?) {
 *               lifecycleScope.launch {
 *                   context.dataStore.data.first()
 *                   // You should also handle IOExceptions here.
 *               }
 *           }//先进行数据异步加载，再调用上面同步获取避免主线程ANR或卡顿（加快获取效率）
 *      async:
 *          lifecycleCoroutineScope.launch {
 *              val flow= context.dataStore.data.map { it[logToken] }
 *              flow.collect{ ... }
 *          }
 * write:
 *       lifecycleCoroutineScope.launch {
 *           context.dataStore.edit {
 *               it[logToken] = "to_set"
 *           }
 *       }
 */
val logToken = stringPreferencesKey("token")

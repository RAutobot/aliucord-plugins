package com.github.rautobot

import android.content.Context
import android.view.View

import com.aliucord.Constants
import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

import com.discord.stores.StoreMessages
import com.discord.stores.StoreMessagesHolder
import com.discord.stores.StoreStream
import com.discord.widgets.chat.list.actions.`WidgetChatListActions$configureUI$10`

import de.robv.android.xposed.XC_MethodHook

import java.io.BufferedWriter
import java.io.FileWriter

@AliucordPlugin(requiresRestart = false)
class MessageSaver : Plugin() {
    override fun start(context: Context) {
        val storeMessagesHolder = StoreMessages::class.java.getDeclaredField("holder").apply {
            isAccessible = true
        }.get(StoreStream.getMessages()) as StoreMessagesHolder

        patcher.patch(`WidgetChatListActions$configureUI$10`::class.java.getDeclaredMethod("onClick", View::class.java), object : XC_MethodHook() {
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                BufferedWriter(FileWriter("${Constants.BASE_PATH}/output.txt")).use {
                    for (msg in storeMessagesHolder.getMessagesForChannel(StoreStream.getChannelsSelected().id)!!.values) {
                        if (msg.content.isEmpty()) continue
 
                        it.write("${msg.id}${msg.messageReference?.let { ">${it.c()}" }} ${msg.author.username}:${msg.content}\n")
                    }
                }
            }
        })
    }

    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}

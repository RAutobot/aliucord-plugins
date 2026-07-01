package com.github.razertexz

import android.content.Context

import com.aliucord.annotations.AliucordPlugin
import com.aliucord.entities.Plugin

import com.discord.stores.StoreMessageReplies
import com.discord.utilities.rest.RestAPI

import de.robv.android.xposed.XC_MethodHook

@AliucordPlugin
class ValidReply : Plugin() {
    override fun start(context: Context) {
        patcher.patch(StoreMessageReplies::class.java.getDeclaredMethod("updateCache", Long::class.java, Long::class.java, StoreMessageReplies.MessageState::class.java), object : XC_MethodHook() {
            override fun afterHookedMethod(param: XC_MethodHook.MethodHookParam) {
                if (param.args[2] is StoreMessageReplies.MessageState.Unloaded) {
                    //RestAPI.api.getChannelMessagesAround(param.args[1] as Long, 1, param.args[0] as Long)
                }
            }
        })
    }

    override fun stop(context: Context) {
        patcher.unpatchAll()
    }
}
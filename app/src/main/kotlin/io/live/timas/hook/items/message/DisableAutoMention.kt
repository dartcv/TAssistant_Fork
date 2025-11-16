package io.live.timas.hook.items.message

import android.content.Context
import io.live.timas.annotations.RegisterToUI
import io.live.timas.annotations.UiCategory
import io.live.timas.hook.base.SwitchHook
import top.sacz.xphelper.dexkit.DexFinder
import top.sacz.xphelper.ext.toClass

@RegisterToUI
object DisableAutoMention : SwitchHook() {

    override val name = "关闭回复自动 @"

    override val category = UiCategory.MESSAGE

    override fun onHook(ctx: Context, loader: ClassLoader) {
        DexFinder.findMethod {
            searchPackages = arrayOf("com.tencent.mobileqq.aio.input.reply")
            returnType = Void.TYPE
            paramCount = 1
            parameters = arrayOf("com.tencent.mobileqq.aio.msg.AIOMsgItem".toClass())
        }.hookBefore {
            result = null
        }
    }
}
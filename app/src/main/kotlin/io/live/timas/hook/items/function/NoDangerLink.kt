package io.live.timas.hook.items.function

import android.content.Context
import android.os.Bundle
import io.live.timas.annotations.RegisterToUI
import io.live.timas.annotations.UiCategory
import io.live.timas.hook.base.SwitchHook
import top.sacz.xphelper.dexkit.DexFinder

@RegisterToUI
object NoDangerLink : SwitchHook() {

    override val name = "禁用拦截"

    override val category = UiCategory.FUNCTION

    override fun onHook(ctx: Context, loader: ClassLoader) {
        DexFinder.findMethod {
            searchPackages = arrayOf("com.tencent.mobileqq.webview")
            paramCount = 1
            parameters = arrayOf(Bundle::class.java)
        }.hookBefore {
            val bundle = args[0] as Bundle
            if (bundle.getInt("jumpResult", 0) != 0) {
                bundle.putInt("jumpResult", 0)
                bundle.putString("jumpUrl", "")
            }
        }
    }
}
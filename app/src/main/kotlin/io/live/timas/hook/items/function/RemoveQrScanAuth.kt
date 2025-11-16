package io.live.timas.hook.items.function

import android.content.Context
import io.live.timas.annotations.RegisterToUI
import io.live.timas.annotations.UiCategory
import io.live.timas.hook.base.SwitchHook
import top.sacz.xphelper.dexkit.DexFinder

@RegisterToUI
object RemoveQrScanAuth : SwitchHook() {

    override val name = "移除相册扫码检验"

    override val category = UiCategory.FUNCTION

    override fun onHook(ctx: Context, loader: ClassLoader) {
        val managerClass = loader.loadClass("com.tencent.open.agent.QrAgentLoginManager")
        DexFinder.findMethod {
            declaredClass = managerClass
            returnType = Void.TYPE
            parameters = arrayOf(Boolean::class.java)
        }.hookBefore {
            args[0] = false
        }
    }
}
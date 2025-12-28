package re.limus.timas.hook.items.message

import android.content.Context
import re.limus.timas.annotations.RegisterToUI
import re.limus.timas.annotations.UiCategory
import re.limus.timas.hook.base.SwitchHook

// dartcv
@RegisterToUI
object BlockLinkInfoCard: SwitchHook() {
    override val name: String
        get() = "屏蔽链接信息卡片"
    override val category = UiCategory.MESSAGE

    override fun onHook(ctx: Context, loader: ClassLoader) {
        loader.loadClass("com.tencent.qqnt.kernel.nativeinterface.LinkInfo").declaredConstructors.forEach { constructor ->
            constructor.hookBefore {
                if(args[0]!=null) result = null
            }
        }
    }

}
package com.example.kotlinproject.wxapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinproject.R
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.constants.ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler


class WXEntryActivity : AppCompatActivity() , IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wxentry)
    }

    override fun onReq(resp: BaseReq?) {
        if (resp?.type == COMMAND_LAUNCH_WX_MINIPROGRAM) {
            val launchMiniProResp = resp as WXLaunchMiniProgram.Resp
            //对应小程序组件 <button open-type="launchApp"> 中的 app-parameter 属性
            val extraData = launchMiniProResp.extMsg
        }
    }

    override fun onResp(p0: BaseResp?) {

    }
}

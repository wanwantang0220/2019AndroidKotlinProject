package com.example.kotlinproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinproject.R
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * App拉起小程序
 */
class LaunchWxActivity : AppCompatActivity() {


    //微信开放平台对应的app的appid
    private val appId = "wx406de325b49189f1"
    lateinit var api: IWXAPI
    lateinit var req: WXLaunchMiniProgram.Req


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_wx)

        api = WXAPIFactory.createWXAPI(this, appId)
        req = WXLaunchMiniProgram.Req()
        // 填小程序原始id
        req.userName = "gh_650ee87ad08f"
        // 可选打开 开发版，体验版和正式版
        req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE
        api.sendReq(req)

    }
}

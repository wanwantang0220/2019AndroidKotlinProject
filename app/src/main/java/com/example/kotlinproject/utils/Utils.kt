package com.example.kotlinproject.utils

import android.content.res.Resources

class Utils {

    /**
     * dp to px
     */
    public  fun dpToPixel(dp : Float):Float{
        var metrics = Resources.getSystem().displayMetrics
        return dp * metrics.density
    }


}
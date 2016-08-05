package com.barryzhang.gankkotlin.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import com.barryzhang.gankkotlin.App
import java.util.*

/**
 * https://github.com/barryhappy
 * Created by Barry on 16/8/5
 */
fun openUrlWithBrowser(act: Activity, url: String?) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    act.startActivity(intent)
}


fun share(content: String?): Boolean {

    try {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        val resInfo = App.instance.getPackageManager().queryIntentActivities(intent, 0)
        if (resInfo != null) {
            val targetedShareIntents = ArrayList<Intent>()
            for (info in resInfo) {
                val targeted = Intent(Intent.ACTION_SEND)
                targeted.type = "text/plain"
                val activityInfo = info.activityInfo
                targeted.putExtra(Intent.EXTRA_TEXT, content)
                targeted.`package` = activityInfo.packageName
                targetedShareIntents.add(targeted)
            }
            val chooserIntent = Intent.createChooser(targetedShareIntents.removeAt(0), "Select app to share")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toTypedArray<Parcelable>())
            try {
                chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                App.instance.startActivity(chooserIntent)
                return true
            } catch (ex: android.content.ActivityNotFoundException) {
                ex.printStackTrace()
            }

        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return false
}
package com.barryzhang.gankkotlin.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Animatable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.Nullable
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.common.references.CloseableReference

import com.facebook.common.util.UriUtil
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.controller.ControllerListener
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.Priority
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.image.ImageInfo
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.Executor

/**
 * 加载图片。
 * 并根据指定的宽度，按图片实际比例设置大小
 * @param draweeView
 * *
 * @param url
 * *
 * @param imageWidth
 */
fun loadImageWithWidth(draweeView: SimpleDraweeView, url: String?, imageWidth: Int) {
    val controllerListener = object : BaseControllerListener<ImageInfo>() {
        override fun onFinalImageSet(
                id: String?,
                imageInfo: ImageInfo?,
                anim: Animatable?) {
            if (imageInfo == null) {
                return
            }
            val lp = draweeView.layoutParams
            lp.width = imageWidth
            lp.height = imageInfo.height * imageWidth / imageInfo.width
            draweeView.layoutParams = lp
        }
    }
    val uri = Uri.parse(url)
    val controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(uri).build()
    draweeView.controller = controller
}
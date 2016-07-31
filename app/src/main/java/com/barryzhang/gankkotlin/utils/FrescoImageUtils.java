package com.barryzhang.gankkotlin.utils;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;


/**
 * 图片加载的类
 * Created by Barry on 15/12/17.
 */
public class FrescoImageUtils {

    /*
    * 参考： http://fresco-cn.org/docs/supported-uris.html#_
    *
    *  | 类型 | Scheme | 示例 |
    | ---------------- | ------- | ------------- |
    | 远程图片 | `http://,` `https://` | `HttpURLConnection` 或者参考 [使用其他网络加载方案](using-other-network-layers.html) |
    | 本地文件 | `file://` | `FileInputStream` |
    | Content provider | `content://` | `ContentResolver` |
    | asset目录下的资源 | `asset://` | `AssetManager` |
    | res目录下的资源 | `res://` | `Resources.openRawResource` |

        res 示例:

        ```
        Uri uri = Uri.parse("res://包名(实际可以是任何字符串甚至留空)/" + R.drawable.ic_launcher);
    ```
    */
    public static void loadImage(SimpleDraweeView draweeView, Uri uri){
        if(draweeView == null ){
            return ;
        }
        if(uri == null){
            return ;
        }
        draweeView.setImageURI(uri);
    }

    public static void loadImage(SimpleDraweeView draweeView, String url){
        if(draweeView == null ){
            return ;
        }
        if(TextUtils.isEmpty(url)){

            return ;
        }
        Uri uri = Uri.parse(url);
        draweeView.setImageURI(uri);
    }

    /**
     * 主页大图、酒店大图等
     * @param draweeView
     * @param url
     */
    public static void loadBigMainImage(SimpleDraweeView draweeView, String url){
        loadImage(draweeView, url);
    }

    /**
     * 主页bannner图、城市封面等宽度满屏的图片
     * @param draweeView
     * @param url
     */
    public static void loadBigBannerImage(SimpleDraweeView draweeView, String url){
        loadImage(draweeView,url);
    }

    /**
     * 发现页的主题图
     * @param draweeView
     * @param url
     */
    public static void loadThemeGridImage(SimpleDraweeView draweeView, String url){
        loadImage(draweeView,url);
    }

    /**
     * 品鉴师资料卡的头像
     * @param draweeView
     * @param url
     */
    public static void loadUserCardAvatar(SimpleDraweeView draweeView, String url){
        loadImage(draweeView,url);
    }


    /**
     * 搜索页的全屏背景图
     * @param draweeView
     * @param url
     */
    public static void loadSearchBigADImage(SimpleDraweeView draweeView, String url){
//        GenericDraweeHierarchy hierarchy = draweeView.getHierarchy();
//        hierarchy.setPlaceholderImage(R.drawable.temp_search_bg);
//        draweeView.setHierarchy(hierarchy);
        loadImage(draweeView, url);
    }

    /**]
     * 加载用户头像
     * @param draweeView
     * @param url
     */
    public static void loadAvatarImage(SimpleDraweeView draweeView, String url){
        loadImage(draweeView, url);
    }

    /**
     * 我的、个人中心的封面
     * @param draweeView
     * @param uri
     */
    public static void loadPersonalCoverImage(SimpleDraweeView draweeView, Uri uri){
        loadImage(draweeView, uri);
    }


    /**
     * 加载写点评story中的图片
     */
    public static void loadCommentStoryImage(SimpleDraweeView draweeView, String path , int width , int height){
        Uri uri = new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(path).build();
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(request)
                .build();
        draweeView.setController(controller);
        loadImage(draweeView,uri);
    }

    /**
     * 点评详情中的大图片
     * @param draweeView
     * @param url
     */
    public static void loadCommentBigImage(SimpleDraweeView draweeView, String url){
        loadImage(draweeView,url);
    }

    /**
     * 加载图片。
     * 并根据指定的宽度，按图片实际比例设置大小
     * @param draweeView
     * @param url
     * @param imageWidth
     */
    public static void loadImageWithWidth(final SimpleDraweeView draweeView, String url , final int imageWidth){
        ControllerListener<ImageInfo> controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(
                    String id,
                    @Nullable ImageInfo imageInfo,
                    @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                ViewGroup.LayoutParams lp = draweeView.getLayoutParams();
                lp.width = imageWidth;
                lp.height = imageInfo.getHeight() * imageWidth / imageInfo.getWidth();
                draweeView.setLayoutParams(lp);
            }
        };
        Uri uri = Uri.parse(url);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(controllerListener)
                .setUri(uri)
                .build();
        draweeView.setController(controller);
    }


}

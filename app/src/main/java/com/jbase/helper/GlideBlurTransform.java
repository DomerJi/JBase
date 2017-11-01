package com.jbase.helper;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import net.qiujuer.genius.blur.StackBlur;

public class GlideBlurTransform extends BitmapTransformation {
    public GlideBlurTransform(Context context) {
     super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
     return blurCrop(pool, toTransform);
    }

    private static Bitmap blurCrop(BitmapPool pool, Bitmap source) {

        if(source!=null) {
            return StackBlur.blur(source, 30, false);
        }else {
            return null;
        }
    }

    @Override
    public String getId() {
     return getClass().getName();
    }
}

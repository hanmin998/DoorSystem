package com.example.door.business;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageTools {
    public static Bitmap scaleImage(String photoPath, int width, int height) {
        // ����ͼƬ��С
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, opts);

        // ����ͼƬ���ű���
        int mWidth = opts.outWidth;
        int mHeight = opts.outHeight;
        int simple = 1;
        while (mWidth > width * simple || mHeight > height * simple) {
            simple = simple * 2;
        }

        // �������ű�����ȡͼƬ
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = simple;
        Bitmap mbitmap = BitmapFactory.decodeFile(photoPath, opts);

        return mbitmap;
    }
}
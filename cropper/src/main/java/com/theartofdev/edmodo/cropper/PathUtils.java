package com.theartofdev.edmodo.cropper;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.RectF;

import androidx.annotation.NonNull;

public class PathUtils {

    public static Path generateOvalPath(@NonNull Bitmap bitmap) {
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Path path = new Path();
        path.addOval(rect, Path.Direction.CW);
        return path;
    }


    public static Path generateTrianglePath(@NonNull Bitmap bitmap) {
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Path path = new Path();
        float haftWidth = (rect.right - rect.left) / 2f;
        path.moveTo(rect.left + haftWidth, rect.top); // Top
        path.lineTo(rect.left, rect.bottom); // Bottom left
        path.lineTo(rect.right, rect.bottom); // Bottom right
        path.lineTo(rect.left + haftWidth, rect.top); // Back to Top
        path.close();
        return path;
    }


    public static Path generateHeartPath(@NonNull Bitmap bitmap) {
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Path circlePath = new Path();
        circlePath.addOval(rect, Path.Direction.CW);
        return circlePath;
    }


    public static Path generateStarPath(@NonNull Bitmap bitmap) {
        RectF rect = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Path circlePath = new Path();
        circlePath.addOval(rect, Path.Direction.CW);
        return circlePath;
    }
}

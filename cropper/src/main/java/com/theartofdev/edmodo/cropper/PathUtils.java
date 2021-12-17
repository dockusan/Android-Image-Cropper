package com.theartofdev.edmodo.cropper;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Pair;

import androidx.annotation.NonNull;


public class PathUtils {

    public static Path generateOvalPath(@NonNull RectF rectF, Path path) {
        path.reset();
        path.addOval(rectF, Path.Direction.CW);
        return path;
    }

    public static Path generateTrianglePath(@NonNull RectF rectF, Path path) {
        path.reset();
        float haftWidth = (rectF.right - rectF.left) / 2f;
        path.moveTo(rectF.left + haftWidth, rectF.top); // Top
        path.lineTo(rectF.left, rectF.bottom); // Bottom left
        path.lineTo(rectF.right, rectF.bottom); // Bottom right
        path.lineTo(rectF.left + haftWidth, rectF.top); // Back to Top
        path.close();
        return path;
    }

    public static Path generateHeartPath(@NonNull RectF rectF, Path path) {
        path.reset();
        float width = rectF.right - rectF.left;
        float height = rectF.bottom - rectF.top;
        // Starting point
        path.moveTo(width / 2f, height / 5f);

        // Upper left path
        path.cubicTo(5 * width / 14f, 0,
                0, height / 15f,
                width / 28f, 2 * height / 5f);

        // Lower left path
        path.cubicTo(width / 14f, 2 * height / 3f,
                3 * width / 7f, 5 * height / 6f,
                width / 2f, height);

        // Lower right path
        path.cubicTo(4 * width / 7f, 5 * height / 6f,
                13 * width / 14f, 2 * height / 3f,
                27 * width / 28f, 2 * height / 5f);

        // Upper right path
        path.cubicTo(width, height / 15f,
                9 * width / 14f, 0,
                width / 2f, height / 5f);
        Matrix translateMatrix = new Matrix();
        translateMatrix.setTranslate(rectF.left, rectF.top);
        path.transform(translateMatrix);
        path.close();

        return path;
    }

    public static Path generateStarPath(@NonNull RectF rectF, Path path) {
        path.reset();
        float mid = rectF.right / 2 - rectF.left / 2;
        float topPadding = rectF.height() * 3.1f / 9f;
        float leftPadding = rectF.width() / 5f;
        PointF midTop = new PointF(rectF.left + mid, rectF.top);
        PointF leftMid = new PointF(rectF.left, rectF.top + topPadding);
        PointF rightMid = new PointF(rectF.right, rectF.top + topPadding);
        PointF leftBottom = new PointF(rectF.left + leftPadding, rectF.bottom);
        PointF rightBottom = new PointF(rectF.right - leftPadding, rectF.bottom);
        Pair<Float, Float> pairMTRB = lineFromPoints(midTop, rightBottom);
        Pair<Float, Float> pairMTLB = lineFromPoints(midTop, leftBottom);
        Pair<Float, Float> pairLMRB = lineFromPoints(leftMid, rightBottom);
        Pair<Float, Float> pairRMLB = lineFromPoints(rightMid, leftBottom);
        Pair<Float, Float> pairLMRM = lineFromPoints(leftMid, rightMid);
        PointF p1 = calculateIntersectionPoint(pairMTRB.first, pairMTRB.second, pairLMRM.first, pairLMRM.second);
        PointF p2 = calculateIntersectionPoint(pairMTRB.first, pairMTRB.second, pairRMLB.first, pairRMLB.second);
        PointF p3 = calculateIntersectionPoint(pairLMRB.first, pairLMRB.second, pairRMLB.first, pairRMLB.second);
        PointF p4 = calculateIntersectionPoint(pairLMRB.first, pairLMRB.second, pairMTLB.first, pairMTLB.second);
        PointF p5 = calculateIntersectionPoint(pairMTLB.first, pairMTLB.second, pairLMRM.first, pairLMRM.second);

        path.moveTo(midTop.x, midTop.y);
        path.lineTo(p1.x, p1.y);
        path.lineTo(rightMid.x, rightMid.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(rightBottom.x, rightBottom.y);
        path.lineTo(p3.x, p3.y);
        path.lineTo(leftBottom.x, leftBottom.y);
        path.lineTo(p4.x, p4.y);
        path.lineTo(leftMid.x, leftMid.y);
        path.lineTo(p5.x, p5.y);
        path.lineTo(midTop.x, midTop.y);
        path.close();
        return path;
    }

    // Function to find the line given two points
    static Pair<Float, Float> lineFromPoints(PointF p1, PointF p2) {
        float m = (p2.y - p1.y) / (p2.x - p1.x);
        float c = p2.y - (m * p2.x);
        return new Pair(m, c);
    }

    static PointF calculateIntersectionPoint(
            float m1,
            float b1,
            float m2,
            float b2) {

        if (m1 == m2) {
            return null;
        }

        float x = (b2 - b1) / (m1 - m2);
        float y = m1 * x + b1;

        PointF point = new PointF(x, y);
        return point;
    }


}

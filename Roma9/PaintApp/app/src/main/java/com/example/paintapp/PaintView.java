package com.example.paintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {

    private Paint paint;
    private Path path;

    public PaintView(Context context) {
        super(context);
        paint = new Paint();
        path = new Path();
        paint.setColor(Color.BLACK);  // Колір лінії за замовчуванням
        paint.setStrokeWidth(10);  // Товщина лінії за замовчуванням
        paint.setStyle(Paint.Style.STROKE);  // Тільки обведення, без заливки
        paint.setAntiAlias(true);  // Антиаліасинг для згладжування ліній
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);  // Малюємо шлях
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);  // Початок малювання
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);  // Малювання лінії
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();  // Оновлюємо екран для відображення нових малюнків
        return true;
    }

    // Метод для зміни товщини лінії
    public void setLineThickness(int thickness) {
        paint.setStrokeWidth(thickness);
        invalidate();
    }

    // Метод для зміни кольору лінії
    public void setLineColor(int color) {
        paint.setColor(color);
        invalidate();
    }
}

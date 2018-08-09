package com.weiwei.filmseatlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wwZhang on 2018/8/9.
 */

public class FileSeatChoiceView extends View {
    //电影票座位图
    private int bitmapSeatCheckedId;
    private int bitmapSeatSoldId;
    private int bitmapSeatAvaliableId;
    private Bitmap bitmapSeatChecked = null;
    private Bitmap bitmapSeatSold = null;
    private Bitmap bitmapSeatAvaliable = null;

    private int overViewColor;
    private int overViewSoldColor;
    private int overViewCheckColor;
    private int textColor;


    private Paint paintTitle;
    private Paint paintSeat;
    private Paint paintText;

    private boolean isDrawFileTitle = false;

    public FileSeatChoiceView(Context context) {
        super(context);
    }

    public FileSeatChoiceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FileSeatChoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(context, attrs);

    }


    private void initAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FileSeatChoiceView);
        overViewCheckColor = typedArray.getColor(R.styleable.FileSeatChoiceView_overview_checked_color, Color.GREEN);
        overViewColor = typedArray.getColor(R.styleable.FileSeatChoiceView_overview_available_color, Color.WHITE);
        overViewSoldColor = typedArray.getColor(R.styleable.FileSeatChoiceView_overview_sold_color, Color.RED);
        textColor = typedArray.getColor(R.styleable.FileSeatChoiceView_text_color, Color.WHITE);

        bitmapSeatCheckedId = typedArray.getResourceId(R.styleable.FileSeatChoiceView_seat_checked, R.drawable.seat_green);
        bitmapSeatSoldId = typedArray.getResourceId(R.styleable.FileSeatChoiceView_seat_sold, R.drawable.seat_sold);
        bitmapSeatAvaliableId = typedArray.getResourceId(R.styleable.FileSeatChoiceView_seat_available, R.drawable.seat_gray);
        bitmapSeatChecked = BitmapFactory.decodeResource(context.getResources(), bitmapSeatCheckedId);
        bitmapSeatAvaliable = BitmapFactory.decodeResource(context.getResources(), bitmapSeatAvaliableId);
        bitmapSeatSold = BitmapFactory.decodeResource(context.getResources(), bitmapSeatSoldId);
        paintSeat = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTitle = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setTextAlign(Paint.Align.LEFT);
        paintText.setTextSize(dip2px(context, 14));
    }

    @Override
    protected void onDraw(Canvas canvas) {
            drawFilmTitle(canvas);
    }

    private void drawFilmTitle(Canvas canvas) {
        float startX = 0;
        float startY = 0;
        float baseLine = 0;
        int lineSpace = dip2px(getContext(),10);
        float paddingTop = dip2px(getContext(),16);
        float paddingBottom = dip2px(getContext(),16);
        String str1 = "可选";
        String str2 = "已售";
        String str3 = "已选";
        int titileWidth = lineSpace*5+bitmapSeatSold.getWidth()*3+(int)(paintText.measureText(str1)*3);
        startX = (getWidth()-titileWidth)/2;
        float filmHeight = startY + paddingTop + paddingBottom + bitmapSeatAvaliable.getHeight();
        baseLine = (filmHeight+paintText.getFontMetrics().top-paintText.getFontMetrics().bottom) / 2 - paintText.getFontMetrics().top;
        canvas.drawBitmap(bitmapSeatAvaliable, startX, startY + paddingTop, paintSeat);
        startX += bitmapSeatAvaliable.getWidth()+lineSpace;
        canvas.drawText(str1, startX, startY + baseLine, paintText);
        startX += paintText.measureText(str1)+lineSpace;
        canvas.drawBitmap(bitmapSeatChecked, startX, startY + paddingTop, paintSeat);
        startX += bitmapSeatChecked.getWidth()+lineSpace;
        canvas.drawText(str3, startX, startY + baseLine, paintText);
        startX += paintText.measureText(str3)+lineSpace;
        canvas.drawBitmap(bitmapSeatSold, startX, startY + paddingTop, paintSeat);
        startX += bitmapSeatSold.getWidth()+lineSpace;
        canvas.drawText(str2, startX, startY + baseLine, paintText);
        isDrawFileTitle = true;
        canvas.drawLine(0, filmHeight, getWidth(), filmHeight, paintText);
    }



    private void drawFilmSeat(Canvas canvas) {

    }

    private void drawFilmOverView(Canvas canvas) {

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
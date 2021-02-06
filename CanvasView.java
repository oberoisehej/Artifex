package com.example.sehejoberoi.finalia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by sehejoberoi on 29/4/17.
 */

public class CanvasView extends View {

    private Path mPath;

    public static Paint mPaint;

    private Canvas mCanvas;

    private Bitmap mBitmap;

    private boolean drawn=true;

    public static ArrayList<Path> paths = new ArrayList<Path>();
    public static ArrayList<Integer> colours = new ArrayList<Integer>();
    public static ArrayList<Integer> thickness = new ArrayList<Integer>();

    public static Stack<Path> rPaths = new Stack<Path>();
    public static Stack<Integer> rColours = new Stack<Integer>();
    public static Stack<Integer> rThickness = new Stack<Integer>();

    public float mX, mY=0;
    public static final float TOLERANCE = 5;

    public static ArrayList<Float> xs = new ArrayList<Float>();
    public static ArrayList<Float> ys = new ArrayList<Float>();
    public static ArrayList<Integer> moves = new ArrayList<Integer>();
    public static ArrayList<Integer> colourSend = new ArrayList<Integer>();
    public static ArrayList<Integer> thicknessSend = new ArrayList<Integer>();



    Context context;

    public CanvasView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context=context;
        setupDrawing();
        this.setDrawingCacheEnabled(true);
    }

    public void setupDrawing(){
        //mPath=new Path();
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        colours.add(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        thickness.add(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    public void startTouch(float x, float y){
        mPath = new Path();
        paths.add(mPath);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        /*if(colours.size()>=1) {
            colours.remove(colours.size() - 1);
            colours.add(properties.colour);

            thickness.remove(thickness.size() - 1);
            thickness.add(properties.thick);
        }*/
        colours.add(properties.colour);
        thickness.add(properties.thick);
        mPath.moveTo(x, y);
        mX=x;
        mY=y;
    }

    public void moveTouch(float x, float y){
        float dx = Math.abs(mX-x);
        float dy = Math.abs(mY-y);
        if (dx>=TOLERANCE || dy>+TOLERANCE){
            mPath.quadTo(mX, mY, (x+mX)/2 , (y+mY)/2);
            mX=x;
            mY=y;
        }
    }
    public void cleanCanvas(){

        paths.clear();
        colours.clear();
        colours.add(properties.colour);
        thickness.clear();
        thickness.add(properties.thick);
        rPaths.clear();
        rColours.clear();
        rThickness.clear();
        xs.clear();
        ys.clear();
        moves.clear();
        colourSend.clear();
        thicknessSend.clear();
        colourSend.add(properties.colour);
        thicknessSend.add(properties.thick);
        properties.change=true;
        invalidate();
    }
    public void upTouch(){

        mPaint.setColor(properties.colour);
        mPath.lineTo(mX,mY);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            mPaint.setColor(colours.get(i)+1);
            mPaint.setStrokeWidth(thickness.get(i)+1);
            canvas.drawPath(paths.get(i), mPaint);
        }
    }


    public boolean onTouchEvent(MotionEvent event) {
        if (Board.getDrawing()) {
            if (properties.change) {
                if(paths.size()>0) {
                    moves.add(1);
                    xs.add(mX);
                    ys.add(mY);
                    colourSend.add(properties.colour);
                    thicknessSend.add(properties.thick);
                }

                /*mPath = new Path();
                paths.add(mPath);
                if(colours.size()>=1) {
                    colours.remove(colours.size() - 1);
                    colours.add(properties.colour);

                    thickness.remove(thickness.size() - 1);
                    thickness.add(properties.thick);

                }
                colours.add(properties.colour);
                thickness.add(properties.thick);*/
                moves.add(0);
                xs.add(event.getX());
                ys.add(event.getY());
                colourSend.add(properties.colour);
                thicknessSend.add(properties.thick);
                startTouch(event.getX(), event.getY());
                colours.remove(colours.size()-1);
                thickness.remove(thickness.size()-1);
                colours.add(properties.colour);
                thickness.add(properties.thick);
                System.out.println(colours.size()+" "+paths.size());

                properties.change = false;

            }
            drawn = true;

            rPaths.clear();
            rColours.clear();
            rThickness.clear();

            float x = event.getX();
            float y = event.getY();


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: //action int value 0
                    moves.add(0);
                    xs.add(x);
                    ys.add(y);
                    colourSend.add(properties.colour);
                    thicknessSend.add(properties.thick);
                    startTouch(x, y);
                    break;
                case MotionEvent.ACTION_MOVE: //action int value 2
                    moves.add(2);
                    xs.add(x);
                    ys.add(y);
                    colourSend.add(properties.colour);
                    thicknessSend.add(properties.thick);
                    moveTouch(x, y);
                    break;
                case MotionEvent.ACTION_UP: //action int value 1
                    moves.add(1);
                    xs.add(x);
                    ys.add(y);
                    colourSend.add(properties.colour);
                    thicknessSend.add(properties.thick);
                    upTouch();
                    break;
                default:
                    return false;
            }
        }
        invalidate();
        return true;
    }
    public void undo(){
        if (paths.size()>=2 && drawn) {
            paths.remove(paths.size() - 1);
            colours.remove(colours.size() - 1);
            thickness.remove(thickness.size() - 1);

            rPaths.add(paths.get(paths.size()-1));
            rColours.add(colours.get(colours.size()-1));
            rThickness.add(thickness.get(thickness.size()-1));

            paths.remove(paths.size() - 1);
            colours.remove(colours.size() - 1);
            thickness.remove(thickness.size() - 1);
            drawn=false;

        }else if (!drawn||paths.size()==1){
            rPaths.add(paths.get(paths.size()-1));
            rColours.add(colours.get(colours.size()-1));
            rThickness.add(thickness.get(thickness.size()-1));

            paths.remove(paths.size() - 1);
            colours.remove(colours.size() - 1);
            thickness.remove(thickness.size() - 1);
        }
        invalidate();
        properties.change=true;
    }
    public void redo(){
        paths.add(rPaths.pop());
        colours.add(rColours.pop());
        thickness.add(rThickness.pop());
        invalidate();
        properties.change = true;

    }


}

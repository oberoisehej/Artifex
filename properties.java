package com.example.sehejoberoi.finalia;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;


public class properties extends AppCompatActivity {

    private static SeekBar blueBar;
    private static SeekBar redBar;
    private static SeekBar greenBar;
    private static int blueRGB=0;
    private static int redRGB=0;
    private static int greenRGB=0;
    public static int colour=Color.argb(0xFF,0x00,0x00,0x00);
    public static int pColour=Color.BLACK;

    public static int eraserColour=Color.BLACK;
    public static int eraserThickness=10;

    Button newCol;
    Button lastCol;

    public static boolean change=false;
    public static boolean eraser=false;


    private static SeekBar opacity;
    private static SeekBar thickness;
    public static int opac=255;
    public static int thick=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        blueBar();
        redBar();
        greenBar();
        opacity();
        thickness();

        lastCol= (Button) findViewById(R.id.lastColour);
        lastCol.setBackgroundColor(pColour);
        newCol= (Button) findViewById(R.id.newColour);
        if (eraser) {
            newCol.setBackgroundColor(eraserColour);
        }else{
            newCol.setBackgroundColor(colour);
        }
        thickness.setProgress(thick);
        opacity.setProgress(opac);
        blueBar.setProgress(blueRGB);
        redBar.setProgress(redRGB);
        greenBar.setProgress(greenRGB);

        colour=Color.BLACK;

    }

    public void exitProp(View v){
        super.onBackPressed();
        change=true;
    }
    public void red(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0xFF, 0x00, 0x00);
        newCol.setBackgroundColor(colour);
    }
    public void black(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0x00, 0x00, 0x00);
        newCol.setBackgroundColor(colour);
    }
    public void pink(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0xFE, 0x00, 0xC3);
        newCol.setBackgroundColor(colour);
    }
    public void blueLight(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0x49, 0xD6, 0xFF);
        newCol.setBackgroundColor(colour);
    }
    public void greenLight(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0x00, 0xFF, 0x00);
        newCol.setBackgroundColor(colour);
    }
    public void white(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0xFF, 0xFF, 0x0FF);
        newCol.setBackgroundColor(colour);
    }
    public void yellow(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0xFE, 0xF1, 0x00);
        newCol.setBackgroundColor(colour);
    }
    public void grey(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0xA5, 0xA5, 0xA5);
        newCol.setBackgroundColor(colour);
    }
    public void purple(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0xAA, 0x00, 0xFF);
        newCol.setBackgroundColor(colour);
    }
    public void greenDark(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0x0A, 0x83, 0x24);
        newCol.setBackgroundColor(colour);

    }
    public void blueDark(View v) {
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0x00, 0x00, 0xFF);
        newCol.setBackgroundColor(colour);
    }
    public void orange(View v) {
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, 0xFE, 0x77, 0x00);
        newCol.setBackgroundColor(colour);
    }
    public void newColour(View v){
        lastCol.setBackgroundColor(colour);
        pColour=colour;
        colour=Color.argb(opac, redRGB, greenRGB, blueRGB);
        newCol.setBackgroundColor(colour);
    }
    public void lastColour(View v){
        lastCol.setBackgroundColor(colour);
        colour=pColour;
        newCol.setBackgroundColor(colour);
    }
    public void blueBar(){
        blueBar=(SeekBar)findViewById(R.id.blueBar);
        blueBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        blueRGB=progress;
                        newCol.setBackgroundColor(Color.argb(opac, redRGB, greenRGB, blueRGB));
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }
    public void redBar(){
        redBar=(SeekBar)findViewById(R.id.redBar);
        redBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        redRGB=progress;
                        newCol.setBackgroundColor(Color.argb(opac, redRGB, greenRGB, blueRGB));
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar){
                    }
                }
        );
    }
    public void greenBar(){
        greenBar=(SeekBar)findViewById(R.id.greenBar);
        greenBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        greenRGB=progress;
                        newCol.setBackgroundColor(Color.argb(opac, redRGB, greenRGB, blueRGB));
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void opacity(){
        opacity=(SeekBar)findViewById(R.id.opacityBar);
        opacity.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        opac=progress;
                        colour=Color.argb(opac, Color.red(colour),Color.green(colour), Color.blue(colour));
                        newCol.setBackgroundColor(colour);
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }
    public void thickness(){
        thickness=(SeekBar)findViewById(R.id.thincknessBar);
        thickness.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        thick=1+progress;
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }
    public void pen(View v){
        if (eraser) {
            colour = eraserColour;
            thick=eraserThickness;
        }
        eraser=false;
    }
    public void eraser(View v){
        if (!eraser) {
            eraserColour = colour;
            eraserThickness = thick;
            colour = Color.WHITE;
            thick = 50;
            eraser = true;
        }
    }

}

package com.uab.lis.rugby.ui.activity;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.uab.lis.rugby.R;

/**
 * Created by Adria on 08/05/2014.
 */
public class SelectPositionPlayers extends Activity {
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_position_players);

        int[] fil = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int[] col = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        int color = 0;
        for(int f : fil){
            color = color == android.R.color.holo_green_light ? android.R.color.holo_green_dark : android.R.color.holo_green_light;
            for(int c : col){
                color = color == android.R.color.holo_green_light ? android.R.color.holo_green_dark : android.R.color.holo_green_light;
                Log.e("error","A"+f+"A"+c);
                int id = getResources().getIdentifier("A"+f+"A"+c,"id","com.uab.lis.rugby");
                View view = findViewById(id);
                view.setTag(new int[]{f,c});
                view.setBackgroundColor(getResources().getColor(color));
                view.setOnDragListener(new MyDragListener());
            }
        }

        int numJugadors = 15;
        LinearLayout lista = (LinearLayout) findViewById(R.id.lista);
        for(int i = 0; i != numJugadors; i++) {
            ImageView image = new ImageView(this);
            image.setOnTouchListener(new MyTouchListener());
            image.setImageResource(R.drawable.icon);
            lista.addView(image);
        }
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    private class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    view.setTag(owner.getTag());
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }
}
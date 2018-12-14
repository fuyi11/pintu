package com.phsxy.sawpuzzle.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.phsxy.sawpuzzle.R;
import com.phsxy.sawpuzzle.adapter.PuzzleListAdapter;
import com.phsxy.sawpuzzle.adapter.PuzzleListAdapter2;
import com.phsxy.sawpuzzle.bean.Pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author: CC_0625
 * date: 2018/12/12
 * dec:
 **/
public class PuzzleActivity2 extends AppCompatActivity {
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.iv_puzzle1)
    ImageView iv_puzzle1;
    @BindView(R.id.iv_puzzle2)
    ImageView iv_puzzle2;
    @BindView(R.id.iv_puzzle3)
    ImageView iv_puzzle3;
    @BindView(R.id.iv_puzzle4)
    ImageView iv_puzzle4;
    @BindView(R.id.iv_puzzle5)
    ImageView iv_puzzle5;
    @BindView(R.id.iv_puzzle6)
    ImageView iv_puzzle6;
    @BindView(R.id.iv_puzzle7)
    ImageView iv_puzzle7;
    @BindView(R.id.iv_puzzle8)
    ImageView iv_puzzle8;
    @BindView(R.id.iv_puzzle9)
    ImageView iv_puzzle9;

    private List<Pieces> piecesList;
    private List<Pieces> piecesList2;
    List<Integer> randomList;

    private int countGrid = 0;
    int[] resourceImages2 = new int[]{
            R.mipmap.img_puzzle1,
            R.mipmap.img_puzzle2,
            R.mipmap.img_puzzle3,
            R.mipmap.img_puzzle4,
            R.mipmap.img_puzzle5,
            R.mipmap.img_puzzle6,
            R.mipmap.img_puzzle7,
            R.mipmap.img_puzzle8,
            R.mipmap.img_puzzle9};

    int[] resourceImages = new int[]{R.mipmap.img_puzzle_bg1,
            R.mipmap.img_puzzle_bg2,
            R.mipmap.img_puzzle_bg3,
            R.mipmap.img_puzzle_bg4,
            R.mipmap.img_puzzle_bg5,
            R.mipmap.img_puzzle_bg6,
            R.mipmap.img_puzzle_bg7,
            R.mipmap.img_puzzle_bg8,
            R.mipmap.img_puzzle_bg9};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_activity2);
        ButterKnife.bind(PuzzleActivity2.this);

        mRecyclerView.setOnDragListener(new MyDragListener(null));
        scrollView.setOnDragListener(new MyDragListener(null));
        piecesList = new ArrayList<Pieces>();
        piecesList2 = new ArrayList<Pieces>();
        randomList = new ArrayList<Integer>();
        initData();

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        PuzzleListAdapter2 puzzleListAdapter = new PuzzleListAdapter2(this, piecesList);
        mRecyclerView.setAdapter(puzzleListAdapter);
        puzzleListAdapter.notifyDataSetChanged();
    }


    private void initData() {
//        Arrays.sort(resourceImages2);
//        Random random = new Random();
//        for (int i = 0; i < resourceImages2.length; i++) {
//            int p = random.nextInt(resourceImages2.length);
//            if (!randomList.contains(i)) {
//                int tmp = resourceImages2[i];
//                resourceImages2[i] = resourceImages2[p];
//                resourceImages2[p] = tmp;
//            }
//        }
//        for (int i = 0; i < resourceImages2.length; i++) {
//            Random random = new Random();
//            if (!randomList.contains(i)) {
//                randomList.add(random.nextInt(resourceImages2.length));
//            }
////            recursive(random.nextInt(8), i);
//        }
        for (int i = 0; i < resourceImages2.length; i++) {
            Pieces piecesModel = new Pieces();
            piecesModel.setpX(i);
            piecesModel.setpY(i);
            piecesModel.setPosition(countGrid);
            piecesModel.setOriginalResource(resourceImages2[countGrid]);
            piecesList.add(piecesModel);
            countGrid++;
        }

        iv_puzzle1.setTag("" + 0);
        iv_puzzle1.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle1));

        iv_puzzle2.setTag("" + 1);
        iv_puzzle2.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle2));

        iv_puzzle3.setTag("" + 2);
        iv_puzzle3.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle3));

        iv_puzzle4.setTag("" + 3);
        iv_puzzle4.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle4));

        iv_puzzle5.setTag("" + 4);
        iv_puzzle5.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle5));

        iv_puzzle6.setTag("" + 5);
        iv_puzzle6.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle6));

        iv_puzzle7.setTag("" + 6);
        iv_puzzle7.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle7));

        iv_puzzle8.setTag("" + 7);
        iv_puzzle8.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle8));

        iv_puzzle9.setTag("" + 8);
        iv_puzzle9.setOnDragListener(new PuzzleActivity2.MyDragListener(iv_puzzle9));
    }

    public class MyDragListener implements View.OnDragListener {

        final ImageView imageView;

        public MyDragListener(final ImageView imageView) {
            this.imageView = imageView;
        }


        @Override
        public boolean onDrag(View views, DragEvent event) {

            //  处理每个预期的事件
            switch (event.getAction()) {

                //开始拖放操作的信号。
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                // 拖动点已经进入视图的边界框
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundResource(R.drawable.target_shape);    //改变景色
                    break;

                //用户已经将拖动阴影移到了视图的边界框之外
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundResource(R.drawable.normal_shape);    //change the shape of the view back to normal
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                //拖曳阴影已经释放，拖曳点在视图的边框内
                case DragEvent.ACTION_DROP:
                    if (views == imageView) {
                        View view = (View) event.getLocalState();
                        String tag = views.getTag().toString();
                        ViewGroup owner = (ViewGroup) views.getParent();
                        ViewParent parent = views.getParent();
                        if (tag.equals("0")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }

                        } else if (tag.equals("1")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }

                        } else if (tag.equals("2")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }
                        } else if (tag.equals("3")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }
                        } else if (tag.equals("4")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }
                        } else if (tag.equals("5")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }
                        } else if (tag.equals("6")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }

                        } else if (tag.equals("7")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }
                        } else if (tag.equals("8")) {
                            Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
                            String selectedViewTag = view.getTag().toString();
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();
                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) views;
                                imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                            } else {
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            view.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "你不能把图像放在这里", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "未拖到相应位置", Toast.LENGTH_LONG).show();
                    }
                    break;

                //拖放操作已经结束
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundResource(R.drawable.normal_shape);	//回到正常状态
                default:
                    break;
            }

            return true;
        }
    }

}

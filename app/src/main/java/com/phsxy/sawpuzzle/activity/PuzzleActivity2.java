package com.phsxy.sawpuzzle.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.phsxy.sawpuzzle.R;
import com.phsxy.sawpuzzle.adapter.PuzzleListAdapter2;
import com.phsxy.sawpuzzle.bean.Pieces;

import java.util.ArrayList;
import java.util.List;

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
    RecyclerView     mRecyclerView;

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

    private int countGrid  = 0;
    private int mCorrectMa = -1, mCorrectMa1 = -1, mCorrectMa2 = -1, mCorrectMa3 = -1, mCorrectMa4 = -1, mCorrectMa5 = -1, mCorrectMa6 = -1,
            mCorrectMa7    = -1, mCorrectMa8 = -1;
    private boolean Sd = false;
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
            piecesModel.setNote(i);
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

        private final ImageView imageView;

        public MyDragListener(final ImageView imageView) {
            this.imageView = imageView;
        }


        @Override
        public boolean onDrag(View views, DragEvent event) {
            //  处理每个预期的事件
            switch (event.getAction()) {

                //开始拖放操作的信号。   开始拖拽
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing     什么都不做

                    break;
                // 拖动点已经进入视图的边界框     拖拽的view进入监听的view时
                case DragEvent.ACTION_DRAG_ENTERED: //拖拽进入目标区域
                    //v.setBackgroundResource(R.drawable.target_shape);    //改变景色
                    break;

                case DragEvent.ACTION_DRAG_EXITED: //拖拽到目标区域外
                    //                    views.setBackgroundResource(R.color.colorAccent);    //将视图的形状更改为正常
                    break;
                //拖拽完成之后松开手指
                case DragEvent.ACTION_DROP:
                    if (views == imageView) {
                        View view = (View) event.getLocalState();
                        String tag = views.getTag().toString();

                        //                        Log.e("66666", "tag:" + tag);
                        if (tag.equals("0")) {
                            DragView(view, views, tag);
                        } else if (tag.equals("1")) {
                            DragView(view, views, tag);

                        } else if (tag.equals("2")) {

                            DragView(view, views, tag);

                        } else if (tag.equals("3")) {

                            DragView(view, views, tag);

                        } else if (tag.equals("4")) {
                            DragView(view, views, tag);

                        } else if (tag.equals("5")) {
                            DragView(view, views, tag);
                        } else if (tag.equals("6")) {

                            DragView(view, views, tag);

                        } else if (tag.equals("7")) {
                            DragView(view, views, tag);

                        } else if (tag.equals("8")) {
                            DragView(view, views, tag);
                        }

                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "未拖到相应位置", Toast.LENGTH_LONG).show();
                    }
                    break;

                case DragEvent.ACTION_DRAG_LOCATION:

                    break;

                //拖放操作已经结束   结束拖拽
                case DragEvent.ACTION_DRAG_ENDED:

                    View view = (View) event.getLocalState();
                    DragView2(view);
                    break;
                default:
                    break;
            }

            return true;
        }
    }



    private void DragView(View view, View views, String tag) {
        String selectedViewTag = view.getTag().toString();
        Pieces piecesModel = piecesList.get(Integer.parseInt(views.getTag().toString()));
        String xy = piecesModel.getpX() + "," + piecesModel.getpY();

        if (xy.equals(selectedViewTag)) {
            ImageView imageView = (ImageView) views;
            imageView.setImageResource(piecesList.get(Integer.parseInt(views.getTag().toString())).getOriginalResource());
            view.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "正确的拼图", Toast.LENGTH_LONG).show();
            if (tag.equals("0")) {
                initNum();
                mCorrectMa = 0;
            } else if (tag.equals("1")) {
                initNum();
                mCorrectMa1 = 1;

            } else if (tag.equals("2")) {
                initNum();
                mCorrectMa2 = 2;

            } else if (tag.equals("3")) {
                initNum();
                mCorrectMa3 = 3;

            } else if (tag.equals("4")) {
                initNum();
                mCorrectMa4 = 4;

            } else if (tag.equals("5")) {
                initNum();
                mCorrectMa5 = 5;
            } else if (tag.equals("6")) {
                initNum();
                mCorrectMa6 = 6;

            } else if (tag.equals("7")) {
                initNum();
                mCorrectMa7 = 7;

            } else if (tag.equals("8")) {
                initNum();
                mCorrectMa8 = 8;
            }
        } else {
            view.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
            initNum();
        }


    }


    private void initNum() {
        mCorrectMa = -1;
        mCorrectMa1 = -1;
        mCorrectMa2 = -1;
        mCorrectMa3 = -1;
        mCorrectMa4 = -1;
        mCorrectMa5 = -1;
        mCorrectMa6 = -1;
        mCorrectMa7 = -1;
        mCorrectMa8 = -1;
    }
    private void DragView2(View view) {
        String tag = view.getTag().toString();

        if (tag.contentEquals("0,0") && mCorrectMa == 0) {
            view.setVisibility(View.INVISIBLE);
            //                    initNum();

        } else if (tag.contentEquals("1,1")&& mCorrectMa1 == 1) {
            view.setVisibility(View.INVISIBLE);
            //                    initNum();

        } else if (tag.contentEquals("2,2")&& mCorrectMa2 == 2) {
            view.setVisibility(View.INVISIBLE);
            //                    initNum();

        } else if (tag.contentEquals("3,3")&& mCorrectMa3 == 3) {
            view.setVisibility(View.INVISIBLE);
            //                    initNum();
        } else if (tag.contentEquals("4,4")&& mCorrectMa4 == 4) {
            view.setVisibility(View.INVISIBLE);
            //                    initNum();

        } else if (tag.contentEquals("5,5")&& mCorrectMa5 == 5) {
            view.setVisibility(View.INVISIBLE);
            //            initNum();
        } else if (tag.contentEquals("6,6")&& mCorrectMa6 == 6) {
            view.setVisibility(View.INVISIBLE);
            //            initNum();
        } else if (tag.contentEquals("7,7")&& mCorrectMa7 == 7) {
            view.setVisibility(View.INVISIBLE);
            //            initNum();
        } else if (tag.contentEquals("8,8")&& mCorrectMa8 == 8) {
            view.setVisibility(View.INVISIBLE);
            //            initNum();
        } else {
            Log.e("66666", "走了:");
            view.setVisibility(View.VISIBLE);
            //            initNum();
        }

    }

}

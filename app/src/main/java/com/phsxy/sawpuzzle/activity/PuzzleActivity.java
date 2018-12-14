package com.phsxy.sawpuzzle.activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.phsxy.sawpuzzle.MainActivity;
import com.phsxy.sawpuzzle.R;
import com.phsxy.sawpuzzle.adapter.PuzzleListAdapter;
import com.phsxy.sawpuzzle.bean.Pieces;

import java.util.ArrayList;
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
public class PuzzleActivity extends AppCompatActivity {
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    PuzzleListAdapter puzzleListAdapter;

    List<Pieces> piecesModelListMain = new ArrayList<Pieces>();

    HashMap<String, Pieces> piecesModelHashMap = new HashMap<String, Pieces>();
    List<Pieces> removedPiecesModelList = new ArrayList<Pieces>();

    int countGrid = 0;

    //Set this based on resourceImages
    int WIDTH = 144;

    int[] resourceImages = new int[]{ R.mipmap.img_puzzle1,
            R.mipmap.img_puzzle2,
            R.mipmap.img_puzzle3,
            R.mipmap.img_puzzle4,
            R.mipmap.img_puzzle5,
            R.mipmap.img_puzzle6,
            R.mipmap.img_puzzle7,
            R.mipmap.img_puzzle8,
            R.mipmap.img_puzzle9  };

    List<Integer> randomList = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_activity);
        ButterKnife.bind(PuzzleActivity.this);

        mRecyclerView.setOnDragListener(new MyDragListener(null));
        scrollView.setOnDragListener(new MyDragListener(null));
        relativeLayout.setOnDragListener(new MyDragListener(null));

        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            randomList.add(random.nextInt(8));
            recursive(random.nextInt(8), i);
        }



        RelativeLayout.LayoutParams params;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(getPixel(WIDTH * j), getPixel(WIDTH * i), 0, 0);
                final ImageView mImageView = new ImageView(this);
                mImageView.setId(generateViewId());
                mImageView.setTag("" + countGrid);
                if (randomList.contains(countGrid)) {
                    // 设置一些虚拟样本图像
                    mImageView.setImageResource(R.drawable.sampleimage);
//                    mImageView.setImageResource(R.color.transparent);
                } else {
                    mImageView.setImageResource(resourceImages[countGrid]);
                }
                mImageView.setOnDragListener(new MyDragListener(mImageView));
                mImageView.setLayoutParams(params);
                relativeLayout.addView(mImageView);

                Pieces piecesModel = new Pieces();
                piecesModel.setpX(i);
                piecesModel.setpY(j);
                piecesModel.setPosition(countGrid);
                piecesModel.setOriginalResource(resourceImages[countGrid]);
                piecesModelListMain.add(piecesModel);
                piecesModelHashMap.put(i + "," + j, piecesModel);
                piecesModel = null;
                countGrid++;

            }
        }


        for (int i = 0; i < randomList.size(); i++) {
            removedPiecesModelList.add(piecesModelListMain.get(randomList.get(i)));
        }

        LinearLayoutManager manager = new LinearLayoutManager(PuzzleActivity.this, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        puzzleListAdapter = new PuzzleListAdapter(this, removedPiecesModelList);
        mRecyclerView.setAdapter(puzzleListAdapter);
        puzzleListAdapter.notifyDataSetChanged();


    }

    public void recursive(int value, int i) {
        Random random = new Random();

        if (randomList.contains(value)) {
            value = random.nextInt(8);
            recursive(value, i);
        } else {
            randomList.set(i, value);
        }

    }

    public int getPixel(int dp) {
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
        return px;
    }

    public int generateViewId() {
        final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }


    static public class MyClickListener implements View.OnLongClickListener {

        // called when the item is long-clicked
        @Override
        public boolean onLongClick(View view) {
            // TODO Auto-generated method stub

            // create it from the object's tag 从对象的标记创建它
            ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());

            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            view.startDrag(data, //要拖动的数据
                    shadowBuilder, //拖影
                    view, //关于拖放操作的本地数据
                    0   //不需要标志
            );

            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }


    public class MyDragListener implements View.OnDragListener {

        final ImageView imageView;

        public MyDragListener(final ImageView imageView) {
            this.imageView = imageView;
        }


        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundResource(R.drawable.target_shape);    //change the shape of the view
                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundResource(R.drawable.normal_shape);    //change the shape of the view back to normal
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    //v is the dynamic grid imageView, we accept the drag item
                    //view is listView imageView the dragged item
                    if (v == imageView) {
                        View view = (View) event.getLocalState();

                        ViewGroup owner = (ViewGroup) v.getParent();
                        if (owner == relativeLayout) {
                            String selectedViewTag = view.getTag().toString();

                            Pieces piecesModel = piecesModelListMain.get(Integer.parseInt(v.getTag().toString()));
                            String xy = piecesModel.getpX() + "," + piecesModel.getpY();

                            if (xy.equals(selectedViewTag)) {
                                ImageView imageView = (ImageView) v;
                                imageView.setImageResource(piecesModelListMain.get(Integer.parseInt(v.getTag().toString())).getOriginalResource());
                                piecesModel = null;
                                Toast.makeText(getApplicationContext(), "正确的拼图", Toast.LENGTH_LONG).show();
                            } else {
                                piecesModel = null;
                                view.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "不是正确的拼图", Toast.LENGTH_LONG).show();
                                break;
                            }
                        } else {
                            View view1 = (View) event.getLocalState();
                            view1.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "你不能把图像放在这里", Toast.LENGTH_LONG).show();
                            break;
                        }
                    } else if (v == scrollView) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "你不能把图像放在这里", Toast.LENGTH_LONG).show();
                        break;
                    } else if (v == mRecyclerView) {
                        View view1 = (View) event.getLocalState();
                        view1.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "你不能把图像放在这里", Toast.LENGTH_LONG).show();
                        break;
                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "你不能把图像放在这里", Toast.LENGTH_LONG).show();
                        break;
                    }
                    break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundResource(R.drawable.normal_shape);	//go back to normal shape

                default:
                    break;
            }

            return true;
        }
    }

}

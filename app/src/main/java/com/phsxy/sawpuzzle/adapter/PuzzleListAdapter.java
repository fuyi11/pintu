package com.phsxy.sawpuzzle.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.phsxy.sawpuzzle.R;
import com.phsxy.sawpuzzle.activity.PuzzleActivity;
import com.phsxy.sawpuzzle.bean.Pieces;

import java.util.List;

/**
 * author: CC_0625
 * date: 2018/12/12
 * dec:
 **/
public class PuzzleListAdapter extends RecyclerView.Adapter<PuzzleListAdapter.MyViewHolder> {
    private Context mContext;
    private   List<Pieces> piecesModelList;


    public PuzzleListAdapter(Context mContext,   List<Pieces> piecesModelList) {
        this.mContext = mContext;
        this.piecesModelList = piecesModelList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.puzzle_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Pieces result = piecesModelList.get(position);
        holder.imageView.setImageResource(piecesModelList.get(position).getOriginalResource());
        holder.imageView.setTag("" + piecesModelList.get(position).getpX() + "," + piecesModelList.get(position).getpY());

        holder.imageView.setOnLongClickListener(new PuzzleActivity.MyClickListener());
    }

    @Override
    public int getItemCount() {
        return piecesModelList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }








//    Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), resourceImages[2]);
//    mWidth3 = bitmap3.getWidth();
//        bitmap3.recycle();
//
//    Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), resourceImages[3]);
//    mWidth4 = bitmap4.getWidth();
//        bitmap4.recycle();
//
//    Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), resourceImages[4]);
//    mWidth5 = bitmap5.getWidth();
//        bitmap5.recycle();
//
//    Bitmap bitmap6= BitmapFactory.decodeResource(getResources(), resourceImages[5]);
//    mWidth6 = bitmap6.getWidth();
//        bitmap6.recycle();
//
//    Bitmap bitmap7 = BitmapFactory.decodeResource(getResources(), resourceImages[6]);
//    mWidth7 = bitmap7.getWidth();
//        bitmap7.recycle();
//
//    Bitmap bitmap8 = BitmapFactory.decodeResource(getResources(), resourceImages[7]);
//    mWidth8 = bitmap8.getWidth();
//        bitmap8.recycle();
//
//    Bitmap bitmap9 = BitmapFactory.decodeResource(getResources(), resourceImages[8]);
//    mWidth8 = bitmap9.getWidth();
//        bitmap9.recycle();


    //                Log.e("length:",resourceImages .length +"");
//                if(resourceImages .length == 1 ){
//                    params.setMargins(mWidth1 * j, mWidth1 * i, 0, 0);
//                }else if(resourceImages .length == 2){
//                    params.setMargins(mWidth2 * j, mWidth2 * i, 0, 0);
//                }else if(resourceImages .length == 3) {
//                    params.setMargins(mWidth3 * j, mWidth3 * i, 0, 0);
//                }else if(resourceImages .length == 4) {
//                    params.setMargins(mWidth4 * j, mWidth4 * i, 0, 0);
//                }else if(resourceImages .length == 5){
//                    params.setMargins(mWidth5 * j, mWidth5 * i, 0, 0);
//                }else if(resourceImages .length == 6){
//                params.setMargins(mWidth6 * j, mWidth6 * i, 0, 0);
//                }else if(resourceImages .length == 7){
//                    params.setMargins(mWidth7 * j, mWidth7 * i, 0, 0);
//                }else if(resourceImages .length == 8){
//                    params.setMargins(mWidth8 * j, mWidth8 * i, 0, 0);
//                }else if(resourceImages .length == 9){
//                    params.setMargins(mWidth9 * j, mWidth9 * i, 0, 0);
//                }
}

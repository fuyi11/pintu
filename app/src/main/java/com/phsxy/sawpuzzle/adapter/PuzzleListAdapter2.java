package com.phsxy.sawpuzzle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.phsxy.sawpuzzle.MyClickListener;
import com.phsxy.sawpuzzle.R;
import com.phsxy.sawpuzzle.bean.Pieces;

import java.util.List;

/**
 * author: CC_0625
 * date: 2018/12/12
 * dec:
 **/
public class PuzzleListAdapter2 extends RecyclerView.Adapter<PuzzleListAdapter2.MyViewHolder> {
    private Context mContext;
    private List<Pieces> piecesModelList;


    public PuzzleListAdapter2(Context mContext,   List<Pieces> piecesModelList) {
        this.mContext = mContext;
        this.piecesModelList = piecesModelList;

    }


    @Override
    public PuzzleListAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.puzzle_item, parent, false);
        return new PuzzleListAdapter2.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PuzzleListAdapter2.MyViewHolder holder, final int position) {

        Pieces result = piecesModelList.get(position);
        holder.imageView.setImageResource(piecesModelList.get(position).getOriginalResource());
        holder.imageView.setTag("" + piecesModelList.get(position).getpX() + "," + piecesModelList.get(position).getpY());
//        Log.e("6666666tag", piecesModelList.get(position).getpX() + "," + piecesModelList.get(position).getpY()) ;
        int note = result.getNote();
        holder.imageView.setOnLongClickListener(new MyClickListener());
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
}

package com.phsxy.sawpuzzle;

import android.content.ClipData;
import android.content.ClipDescription;
import android.view.View;

/**
 * author: CC_0625
 * date: 2018/12/13
 * dec:
 **/
public class MyClickListener implements View.OnLongClickListener {

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

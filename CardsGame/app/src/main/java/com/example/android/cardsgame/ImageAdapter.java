package com.example.android.cardsgame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by harsh on 11/26/14.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Game    CardsGame;
    private int CurrentPlayerIdx;

    public void SetPlayerIdx(int Idx)
    {
        CurrentPlayerIdx = Idx;
    }

    public ImageAdapter(Context c) {
        mContext = c;
        CardsGame = HomeScreen.CardGame;
    }

    public int getCount() {
        return CardsGame.getPlayer(CurrentPlayerIdx).CardsInHand.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(CardsGame.getPlayer(CurrentPlayerIdx).CardsInHand.get(position).GetResId());
        return imageView;
    }


//    private Integer[] mThumbIds = {
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back,
//            R.drawable.card_back
//    };
}

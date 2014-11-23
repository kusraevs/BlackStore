package com.example.blackstore.goods;

import com.example.blackstore.R;
import com.example.blackstore.goods.model.Good;
import com.example.blackstore.goods.model.GoodsInterface;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsListAdapter extends BaseAdapter{
    private LayoutInflater lInflater;
    private ImageLoader mLoader = ImageLoader.getInstance();
    private GoodsInterface goods;
	private DisplayImageOptions options;
    public GoodsListAdapter(Context context, GoodsInterface goods) {
        this.goods = goods;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.loading_afisha_image)
		.showImageOnFail(R.drawable.loading_afisha_image)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.build();
    }
    @Override
    public int getCount() {
        return goods.size();
    }

    @Override
    public Object getItem(int position) {
        return goods.getGood(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    	ViewHolder viewHolder;
    	if (convertView == null){
            convertView = lInflater.inflate(R.layout.good_grid_item, parent, false);
            viewHolder =  new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_good_name);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_good_price);
            viewHolder.ivGood = (ImageView) convertView.findViewById(R.id.iv_good);
            convertView.setTag(viewHolder);
    	} else
    		viewHolder = (ViewHolder) convertView.getTag();
        Good good = (Good) getItem(position);
       
		String imageLink = good.getImageUrl();
		mLoader.displayImage(imageLink, viewHolder.ivGood, options);
		
        viewHolder.tvName.setText(good.getName());
        viewHolder.tvPrice.setText(Integer.toString(good.getPrice()) + " руб.");
        return convertView;
    }
    
    private static class ViewHolder{
    	public TextView tvName;
    	public TextView tvPrice;
    	public ImageView ivGood;
    }
}

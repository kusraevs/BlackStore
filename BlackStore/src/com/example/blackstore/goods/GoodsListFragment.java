package com.example.blackstore.goods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.blackstore.MainActivity;
import com.example.blackstore.R;
import com.example.blackstore.goodDetail.GoodDetailActivity;
import com.example.blackstore.goods.loader.GoodsLoader;
import com.example.blackstore.goods.model.GoodsFromJSON;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GoodsListFragment extends Fragment {
	private static final int VISIBLE_THRESHOLD = 20;
	private static final int GOODS_LOADER_ID = 0;
	private GridViewWithHeaderAndFooter goodsGridView;
	private View goodsFooterView;
	private GoodsListAdapter goodsListAdapter;
	private Loader<String> goodsLoader;
	private GoodsFromJSON goods = new GoodsFromJSON();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_goods_grid, container, false);
		goods = new GoodsFromJSON();
		goodsGridView = (GridViewWithHeaderAndFooter) rootView.findViewById(R.id.goods_grid);
		
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		goodsFooterView = layoutInflater.inflate(R.layout.good_grid_footer, null);
		goodsFooterView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		goodsGridView.post(new Runnable() {
		    @Override
		    public void run() {
		    	goodsGridView.tryToScrollToBottomSmoothly(100);
		    }
		});
		goodsGridView.addFooterView(goodsFooterView);;
		goodsListAdapter = new GoodsListAdapter(getActivity(), goods);
		goodsGridView.setAdapter(goodsListAdapter);
		goodsGridView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
				startActivity(intent);
			}
		});
		goodsGridView.setOnScrollListener(new EndlessScrollListener(0){
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				goodsFooterView.setVisibility(View.VISIBLE);
				restartLoader(totalItemsCount);
			}
			
		});
		restartLoader(0);
		initImageLoader(getActivity());
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(0);
	}
	
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
						context).build();
		ImageLoader.getInstance().init(config);
		// Initialize ImageLoader with configuration.
	}
	
	public void restartLoader(int totalItemsCount){
		Bundle bundle = new Bundle();
		bundle.putInt(GoodsLoader.ARGS_SKIP, totalItemsCount);
		bundle.putInt(GoodsLoader.ARGS_LIMIT, VISIBLE_THRESHOLD);
		goodsLoader = getLoaderManager().restartLoader(GOODS_LOADER_ID, bundle, new LoaderCallbacks<String>(){
			@Override
			public Loader<String> onCreateLoader(int id, Bundle args) {
				Loader<String> loader = new GoodsLoader(getActivity(), args);
				return loader;
			}

			@Override
			public void onLoadFinished(Loader<String> loader, String goodsJSONArrayStr) {
				goodsFooterView.setVisibility(View.GONE);
				if (goodsJSONArrayStr == null)
					return;
				goods.appendGoodsFromJSON(goodsJSONArrayStr);
				goodsListAdapter.notifyDataSetChanged();
			}

			@Override
			public void onLoaderReset(Loader<String> loader) {
				// TODO Auto-generated method stub
			}
		});
		goodsLoader.forceLoad();
	}
}

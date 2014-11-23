package com.example.blackstore.goodDetail;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blackstore.R;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.FailReason;

public class GoodDetailActivity extends ActionBarActivity {
	private static final String STATE_POSITION = "STATE_POSITION";
	private DisplayImageOptions options;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_good_detail);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		int pagerPosition = 0;
		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}

		options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(true).cacheOnDisc(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		pager = (ViewPager) findViewById(R.id.pager); 
		pager.setAdapter(new ImagePagerAdapter(new GoodDetail().getImagesUrl()));
		pager.setCurrentItem(pagerPosition);
		
		Button button = (Button) findViewById(R.id.first);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pager.setCurrentItem(0);
			}
		});
		button = (Button) findViewById(R.id.last);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pager.setCurrentItem(3);
			}
		});
		GoodDetailInterface goodDetail = new GoodDetail();
		TextView tvGoodBrand = (TextView) findViewById(R.id.tv_good_brand);
		TextView tvGoodName = (TextView) findViewById(R.id.tv_good_name);
		TextView tvGoodDescr = (TextView) findViewById(R.id.tv_good_descr);
		tvGoodBrand.setText(goodDetail.getBrand());
		tvGoodName.setText(goodDetail.getName());
		tvGoodDescr.setText(goodDetail.getDescription());
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends PagerAdapter {

		private String[] images;
		private LayoutInflater inflater;
		private ImageLoader imageLoader;

		public ImagePagerAdapter(String[] images) {
			this.images = images;
			inflater = getLayoutInflater();
			imageLoader = ImageLoader.getInstance();
			//imageLoader.clearDiskCache();
			//imageLoader.clearMemoryCache();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return images.length;
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = inflater.inflate(
					R.layout.good_detail_image_item, view, false);
			ImageView imageView = (ImageView) imageLayout
					.findViewById(R.id.image);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.pbLoading);

			imageLoader.displayImage(images[position], imageView, options,
					new SimpleImageLoadingListener() {
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							spinner.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							String message = null;
							switch (failReason.getType()) {
							case IO_ERROR:
								message = "Input/Output error";
								break;
							case DECODING_ERROR:
								message = "Image can't be decoded";
								break;
							case NETWORK_DENIED:
								message = "Downloads are denied";
								break;
							case OUT_OF_MEMORY:
								message = "Out Of Memory error";
								break;
							case UNKNOWN:
								message = "Unknown error";
								break;
							}
							Toast.makeText(GoodDetailActivity.this, message,
									Toast.LENGTH_SHORT).show();
							spinner.setVisibility(View.GONE);
						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							spinner.setVisibility(View.GONE);
						}
					});

			((ViewPager) view).addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}
	}
}

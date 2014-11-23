package com.example.blackstore.goods.model;

public class Goods implements GoodsInterface {

	private static final String[] IMAGES_URLS = {"http://webdesigning.website/wp-content/uploads/2014/10/icon-brand-normal.png",
		"http://webdesigning.website/wp-content/uploads/2014/10/icon-brand-normal.png",
		"http://webdesigning.website/wp-content/uploads/2014/10/icon-brand-normal.png",
		"http://webdesigning.website/wp-content/uploads/2014/10/icon-brand-normal.png",
		"http://weloveicons.s3.amazonaws.com/icons/100907_itunes1.png",
		"http://weloveicons.s3.amazonaws.com/icons/100929_applications.png",
		"http://www.idyllicmusic.com/index_files/get_apple-iphone.png",
		"http://www.frenchrevolutionfood.com/wp-content/uploads/2009/04/Twitter-Bird.png",
		"http://3.bp.blogspot.com/-ka5MiRGJ_S4/TdD9OoF6bmI/AAAAAAAAE8k/7ydKtptUtSg/s1600/Google_Sky%2BMaps_Android.png",
		"http://www.desiredsoft.com/images/icon_webhosting.png",
		"http://goodereader.com/apps/wp-content/uploads/downloads/thumbnails/2012/01/hi-256-0-99dda8c730196ab93c67f0659d5b8489abdeb977.png",
		"http://1.bp.blogspot.com/-mlaJ4p_3rBU/TdD9OWxN8II/AAAAAAAAE8U/xyynWwr3_4Q/s1600/antivitus_free.png",
		"http://cdn3.iconfinder.com/data/icons/transformers/computer.png",
		"http://cdn.geekwire.com/wp-content/uploads/2011/04/firefox.png?7794fe",
		"https://ssl.gstatic.com/android/market/com.rovio.angrybirdsseasons/hi-256-9-347dae230614238a639d21508ae492302340b2ba",
		"http://androidblaze.com/wp-content/uploads/2011/12/tablet-pc-256x256.jpg",
		"http://www.theblaze.com/wp-content/uploads/2011/08/Apple.png",
		"http://1.bp.blogspot.com/-y-HQwQ4Kuu0/TdD9_iKIY7I/AAAAAAAAE88/3G4xiclDZD0/s1600/Twitter_Android.png",
		"http://3.bp.blogspot.com/-nAf4IMJGpc8/TdD9OGNUHHI/AAAAAAAAE8E/VM9yU_lIgZ4/s1600/Adobe%2BReader_Android.png",
		"http://cdn.geekwire.com/wp-content/uploads/2011/05/oovoo-android.png?7794fe",
		"http://icons.iconarchive.com/icons/kocco/ndroid/128/android-market-2-icon.png",
		"http://thecustomizewindows.com/wp-content/uploads/2011/11/Nicest-Android-Live-Wallpapers.png",
		"http://c.wrzuta.pl/wm16596/a32f1a47002ab3a949afeb4f",
		"http://macprovid.vo.llnwd.net/o43/hub/media/1090/6882/01_headline_Muse.jpg"};
	@Override
	public Good getGood(int i) {
		return new Good("Название", 100, IMAGES_URLS[i]);
	}

	@Override
	public int size() {
		return IMAGES_URLS.length;
	}

}

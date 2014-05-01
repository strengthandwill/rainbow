package pohkahkong.game.rainbow.service;

import java.util.ArrayList;
import java.util.List;

import pohkahkong.game.rainbow.bean.bitmapinfo.BitmapInfo;
import pohkahkong.game.rainbow.bean.bitmapinfo.GetBitmapInfo;
import pohkahkong.game.rainbow.bean.bitmapinfo.LoadBitmapInfo;
import pohkahkong.game.rainbow.callback.OnCompleteListener;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class ImageService {
	private static final String TAG = "ImageService";
	
	private static ImageService instance = null;
	private Resources resources;
	private List<Bitmap> allBitmaps = new ArrayList<Bitmap>();
	private List<BitmapWorkerTask> bitmapWorkerTasks = new ArrayList<BitmapWorkerTask>();

	private ImageService() {
	}

	public static ImageService getInstance() {
		if (instance==null) {
			instance = new ImageService();
		}
		return instance;
	}
	
	public void init(Context context) {
		resources = context.getResources(); 
	}
	
	public void loadBitmaps(LoadBitmapInfo[] loadBitmapInfos, final OnCompleteListener onCompleteListener) {	
		runBitmapWorkerTask(loadBitmapInfos, onCompleteListener);
	}
	
	public void getBitmaps(GetBitmapInfo[] getBitmapInfos, final OnCompleteListener onCompleteListener) {	
		runBitmapWorkerTask(getBitmapInfos, onCompleteListener);
	}
	
	public void release() {
		Log.i(TAG, "release");
		recycleBitmaps();
		cancelBitmapWorkerTasks();
	}
	
	private void runBitmapWorkerTask(BitmapInfo[] bitmapInfos, final OnCompleteListener onCompleteListener) {		
		BitmapWorkerTask bitmapWorkerTask = new BitmapWorkerTask(onCompleteListener);
		bitmapWorkerTasks.add(bitmapWorkerTask);
		bitmapWorkerTask.execute(bitmapInfos);		
	}
	
	public Bitmap loadBitmap(int resId, int width, int height) {
		Bitmap bitmap = decodeSampledBitmapFromResource(resId, width, height);
		bitmap = scaledBitmap(bitmap, width, height);
		return bitmap;
	}
	
	private Bitmap getBitmap(Bitmap bitmap, int x, int y, int width, int height) {
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, x, y, width, height);
		allBitmaps.add(bitmap2);
		return bitmap2;
	}
	
	private void recycleBitmaps() {
		for (int i=0; i<allBitmaps.size(); i++) {
			allBitmaps.get(i).recycle();			
		}
		allBitmaps.clear();
	}
	
	private void cancelBitmapWorkerTasks() {
		for (int i=0; i<bitmapWorkerTasks.size(); i++) {
			bitmapWorkerTasks.get(i).cancel(true);
		}
		bitmapWorkerTasks.clear();
	}
	
	private Bitmap decodeSampledBitmapFromResource(int resId, int width, int height) {
	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(resources, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, width, height);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(resources, resId, options);
	}
	
	private int calculateInSampleSize(BitmapFactory.Options options, int width, int height) {
		// Raw height and width of image
		final int bitmapHeight = options.outHeight;
		final int bitmapWidth = options.outWidth;
		int inSampleSize = 1;

		if (bitmapHeight > height || bitmapWidth > width) {

			// Calculate ratios of height and width to requested height and width
			final int heightRatio = Math.round((float) bitmapHeight / (float) height);
			final int widthRatio = Math.round((float) bitmapWidth / (float) width);

			// Choose the smallest ratio as inSampleSize value, this will guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	
	public Bitmap scaledBitmap(Bitmap bitmap, int width, int height) {
		Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, width, height, false);
		bitmap.recycle();
		return bitmap2;
	}
	
	private class BitmapWorkerTask extends AsyncTask<BitmapInfo, Void, Bitmap[]> {
		private OnCompleteListener onCompleteListener;
		
		public BitmapWorkerTask(OnCompleteListener onCompleteListener) {
			this.onCompleteListener = onCompleteListener;
		}
		
		// Decode image in background.
		@Override
		protected Bitmap[] doInBackground(BitmapInfo... params) {
			List<Bitmap> bitmaps = new ArrayList<Bitmap>();
			for (int i=0; i<params.length; i++) {
				Bitmap bitmap = null;
				if (params[i] instanceof LoadBitmapInfo) {
					LoadBitmapInfo bitmapInfo = (LoadBitmapInfo) params[i];
					bitmap = loadBitmap(bitmapInfo.getId(), bitmapInfo.getWidth(), bitmapInfo.getHeight());
				} else if (params[i] instanceof GetBitmapInfo) {					
					GetBitmapInfo bitmapInfo = (GetBitmapInfo) params[i];
					bitmap = getBitmap(bitmapInfo.getBitmap(),
							bitmapInfo.getX(), bitmapInfo.getY(),
							bitmapInfo.getWidth(), bitmapInfo.getHeight());
				}
				if (bitmap!=null) {
					bitmaps.add(bitmap);
					allBitmaps.add(bitmap);
				}
			} 
			return bitmaps.toArray(new Bitmap[bitmaps.size()]);
		}

	    // Once complete, see if ImageView is still around and set bitmap.
	    @Override
	    protected void onPostExecute(Bitmap[] bitmaps) {	    	
	    	if (bitmaps==null) {
	    		return;
	    	}
	    	onCompleteListener.onComplete(bitmaps);
	    }
	}
}

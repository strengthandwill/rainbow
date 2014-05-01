package pohkahkong.game.rainbow.menu.imagepager;

import pohkahkong.game.rainbow.R;
import pohkahkong.game.rainbow.callback.OnCompleteListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 
 * @author Poh Kah Kong
 *
 */
public class ImageFragment extends Fragment {
	private static final String IMAGE_NUM = "resId";
	
	private ImagePagerImageService imageService = ImagePagerImageService.getInstance();
	
	private ImageView imageView;
	private int imageNum;
	private Bitmap bitmap = null;
	
	public static ImageFragment newInstance(int imageNum) {
		ImageFragment imageFragment = new ImageFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(IMAGE_NUM, imageNum);
		imageFragment.setArguments(bundle);
		return imageFragment;
	}
	
	public ImageFragment() { }
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageNum = getArguments().getInt(IMAGE_NUM);
	}
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.imagepager_screen, container, false);
		imageView = (ImageView) view.findViewById(R.id.storyIV);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		imageService.loadBitmap(imageNum, new OnCompleteListener() {
			public void onComplete(Object[] objects) {
				bitmap = (Bitmap) objects[0];
				imageView.setImageBitmap(bitmap);			
			}			
		});
	}
	
	@Override
	public void onDestroy() {
		if (bitmap!=null) {
			bitmap.recycle();
		}
		super.onDestroy();
	}
}

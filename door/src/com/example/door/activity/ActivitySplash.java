package com.example.door.activity;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.style.LineHeightSpan.WithDensity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.door.R;
import com.example.door.activity.base.ActivityBase;

public class ActivitySplash extends ActivityBase {
	private ViewPager viewPager;
	private HorizontalScrollView scrollView;
	private ImageView imageView;
	private GuidePagerAdapter adapter;

	@Override
	protected int getLayoutID() {

		return R.layout.layout_spl;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		Handler handler=new Handler();
		if (isFirst()) {
			openActivity(ActivitySpl.class);
			
			
			finish();
			return;
		}
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
		imageView = (ImageView) findViewById(R.id.imageView1);
		adapter = new GuidePagerAdapter(this);
		adapter.addPager("��������һ");
		adapter.addPager("����������");
		adapter.addPager("����������");
		adapter.addPager("��ʼ����");
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

	}

	private class GuidePagerAdapter extends PagerAdapter {
		private Context context;
		private ArrayList<ViewHold> viewHold;


		public GuidePagerAdapter(Context context) {
			this.context = context;
			viewHold = new ArrayList<ViewHold>();
			// TODO Auto-generated constructor stub
		}

		public void addPager(String string) {
			// TODO Auto-generated method stub
			ViewHold hold = new ViewHold();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			hold.root = inflater.inflate(R.layout.pager_item, null);
			hold.button = (Button) hold.root.findViewById(R.id.button1);
			hold.button.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					openActivity(ActivitySpl.class);
					finish();
				}
			});
			hold.guideText = (TextView) hold.root.findViewById(R.id.textView1);
			hold.guideText.setText(string);
			viewHold.add(hold);

		}

		/**
		 * ҳ�滬��ʱ���ã����ٻ���ҳ��
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(viewHold.get(position).root);
		}

		/**
		 * ����ҳ�������
		 */
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return viewHold.size();
		}

		/**
		 * ����ʱ��ӻ���ҳ��
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(viewHold.get(position).root);
			return viewHold.get(position).root;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	/**
	 * �洢ViewPager��ÿһҳ�Ŀؼ�
	 * 
	 * @author Administrator
	 * 
	 */
	private class ViewHold {
		View root;
		TextView guideText;
		Button button;
	}

	public class MyPageChangeListener implements OnPageChangeListener {

		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			// viewPager�ܿ��
			float widthOfPager = viewPager.getWidth() * adapter.getCount();
			// imageView�Ŀ��
			float widthOfImage = imageView.getWidth();
			// Ҫ�ƶ��ľ���
			float moveWithPager = widthOfPager - viewPager.getWidth();
			float moveWithIamge = widthOfImage - viewPager.getWidth();
			// �ƶ�����
			float ratio = moveWithIamge / moveWithPager;
			// ��ǰҳ���ƶ�����
			float currentPositionPager = arg0 * viewPager.getWidth() + arg2;
			// scrollViewҪ�ƶ��ľ���
			scrollView.scrollTo((int) (currentPositionPager * ratio),
					scrollView.getScrollY());

		}

		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub

		}

	}

}

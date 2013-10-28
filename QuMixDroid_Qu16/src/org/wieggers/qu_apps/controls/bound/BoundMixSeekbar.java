package org.wieggers.qu_apps.controls.bound;

import org.wieggers.qu_apps.qu16.IMixValueListener;
import org.wieggers.qu_apps.qu16.Qu16_MixValue;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class BoundMixSeekbar extends SeekBar implements IBoundControl, IMixValueListener, android.widget.SeekBar.OnSeekBarChangeListener { 

	protected Qu16_MixValue mBoundMixValue;

	public BoundMixSeekbar(Context context) {

		super(context);
		init();
	}

	public BoundMixSeekbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public BoundMixSeekbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {		
		setOnSeekBarChangeListener(this);
	}

	@Override
	public void valueChanged(Qu16_MixValue sender, Object origin, final byte value) {
		this.post(new Runnable() {
			
			@Override
			public void run() {
				onProgressChanged(BoundMixSeekbar.this, (int) value, false);				
			}
		});		
	}

	@Override
	public void Connect(Qu16_MixValue mixValue) {
		if (mBoundMixValue != null) {
			mBoundMixValue.removeListener(this);
		}
		
		mBoundMixValue = mixValue;
		if (mBoundMixValue != null) {
			mBoundMixValue.addListener(this);
			valueChanged(mixValue, null, mixValue.getValue());
			setVisibility(VISIBLE);
		} else {
			setVisibility(INVISIBLE);
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (fromUser) {
			if (mBoundMixValue != null) {
				mBoundMixValue.setValue(this, (byte) progress );
			}
		}		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
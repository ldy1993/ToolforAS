package com.ldy.view.CustomWidget.ProcessIcon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ldy.utils.UnitUtils;


public class ProcessIcon extends ImageView {
	private float head, tail;
	private Paint arcPaint;
	private Paint hPaint;
	
	public ProcessIcon(Context context, AttributeSet attrs) {
		super(context, attrs);
		arcPaint = new Paint();
		arcPaint.setColor(Color.rgb(0x00, 0x5A, 0xA4));
		arcPaint.setStrokeWidth((float) UnitUtils.dp2px(2.5));
		arcPaint.setStyle(Style.STROKE);
		arcPaint.setAntiAlias(true);
		hPaint = new Paint();
		hPaint.setColor(Color.rgb(0x00, 0x5A, 0xA4));
		hPaint.setStyle(Style.FILL);
		hPaint.setAntiAlias(true);
	}
	
	public void setOffset(float offset) {
		if(offset > 1 || offset < 0) {
			offset = offset % 1;
		}
		head = (float) (offset * 1440);
		tail = (float) (offset * 720);
		postInvalidate();
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		float sweep = head - tail;
		if(sweep > 360) {
			sweep = 720 - sweep;
		}
		float start = tail;
		if(start > 360) {
			start = start - sweep;
		}
		double chx,chy, ctx,cty;
		double CX = canvas.getWidth() / 2;
		double CY = canvas.getHeight() / 2;
		double RADIUS_X = canvas.getWidth() / 2 - arcPaint.getStrokeWidth();
		double RADIUS_Y = canvas.getHeight() / 2 - arcPaint.getStrokeWidth();
		chx = CX + Math.cos((start - 90) * Math.PI / 180) * RADIUS_X;
		chy = CY + Math.sin((start - 90) * Math.PI / 180) * RADIUS_Y;
		ctx = CX + Math.cos((start - 90 + sweep) * Math.PI / 180) * RADIUS_X;
		cty = CY + Math.sin((start - 90 + sweep) * Math.PI / 180) * RADIUS_Y;
		canvas.drawCircle((float)chx, (float)chy, arcPaint.getStrokeWidth() / 2, hPaint);
		canvas.drawCircle((float)ctx, (float)cty, arcPaint.getStrokeWidth() / 2, hPaint);
		canvas.drawArc(arcPaint.getStrokeWidth(), arcPaint.getStrokeWidth(), canvas.getWidth() - arcPaint.getStrokeWidth(), canvas.getHeight() - arcPaint.getStrokeWidth(), start - 90, sweep, false, arcPaint);
	}
	
}

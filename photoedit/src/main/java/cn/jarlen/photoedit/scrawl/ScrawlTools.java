/*
 *          Copyright (C) 2018 jarlen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package cn.jarlen.photoedit.scrawl;

import android.content.Context;
import android.graphics.Bitmap;

import cn.jarlen.photoedit.utils.FileUtils;

/**
 * @author jarlen
 */
public class ScrawlTools
{
	private DrawingBoardView drawView;
	private Context context;
	
	private int mBrushColor;
	

	
	public ScrawlTools(Context context, DrawingBoardView drawView,
			Bitmap bitmap)
	{
		this.drawView = drawView;
		this.context = context;
		drawView.setBackgroundBitmap(bitmap);
	}

	/**
	 * Create Paintbrush * * @param drawText * Painting Type * * @param paintBitmap * Paintbrush Picture * * @param color * Paintbrush color
	 * 
	 */
	public void creatDrawPainter(DrawAttribute.DrawStatus drawStatus,
			Bitmap paintBitmap, int color)
	{
		drawView.setBrushBitmap(drawStatus, paintBitmap, color);
	}

	
	/**
	 * Paintbrush creation * * @param drawState * Painting type * * @param paintBrush * Brush type * (including brush image, brush color, brush thickness, brush thickness type)
	 * 
	 */
	public void creatDrawPainter(DrawAttribute.DrawStatus drawStatus,
			PaintBrush paintBrush)
	{
		int color = paintBrush.getPaintColor();
		int size = paintBrush.getPaintSize();
		int num = paintBrush.getPaintSizeTypeNo();
		Bitmap bitmap = paintBrush.getPaintBitmap();

		Bitmap paintBitmap = FileUtils.ResizeBitmap(bitmap, num - (size - 1));
		drawView.setBrushBitmap(drawStatus, paintBitmap, color);
	}
	
	
	public void creatStampPainter(DrawAttribute.DrawStatus drawStatus,int[] res,int color)
	{
		drawView.setStampBitmaps(drawStatus, res, color);
	}
	

	/**
	 * Get a picture of the painting
	 * @return
	 */
	public Bitmap getBitmap()
	{
		return drawView.getDrawBitmap();
	}

	public int getBrushColor()
	{
		return mBrushColor;
	}

	/**
	 * Set Brush Color * * @param BrushColor * Brush Color (Useless)
	 * 
	 */
	
	public void setBrushColor(int mBrushColor)
	{
		this.mBrushColor = mBrushColor;
	}
}

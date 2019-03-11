package pwrdf.cam;

import java.awt.Color;
import java.awt.Image;

public class CameraRoom
{
	private int width;
	private int height;
	private int xPos;
	private int yPos;
	private int viewW;
	private int viewH;
	private int viewX;
	private int viewY;
	private int zoomLevel;
	private int selectX;
	private int selectY;
	private final double[] zoomLevels = {
			0.25, 0.4, 0.5, 0.75, 0.8, 1, 1.25, 1.5, 2, 2.5, 3.25, 5
	};
	private Color bgCol;
	public CameraRoom(int width, int height)
	{
		setWidth(width);
		setHeight(height);
		setViewW(200);
		setViewH(200);
		setXPos(width/2);
		setXPos(height/2);
		setZoomLevel(6);
		setBgCol(Color.BLACK);
		centreCamera();
	}
	public CameraRoom(int width, int height, int xPos, int yPos, int viewW, int viewH)
	{
		setWidth(width);
		setHeight(height);
		setViewW(viewW);
		setViewH(viewH);
		setXPos(xPos);
		setYPos(yPos);
		setSelectX(viewW/2);
		setSelectY(viewH/2);
		setZoomLevel(6);
		setBgCol(Color.BLACK);
		centreCamera();
	}
	public void moveSelect(int xOffset, int yOffset)
	{
		int newX = getSelectX() + xOffset;
		int newY = getSelectY() + yOffset;
		int moveX = 0;
		int moveY = 0;
		if(newX >= 16 && newX+16 <= getViewW()) {
			setSelectX(newX);
		} else if(newX < 16) {
			setSelectX(16);
			moveX = xOffset;
		} else {
			setSelectX(getViewW()-16);
			moveX = xOffset;
		}
		if(newY >= 16 && newY+16 <= getViewH()) {
			setSelectY(newY);
		} else if(newY < 16) {
			setSelectY(16);
			moveY = yOffset;
		} else {
			setSelectY(getViewH()-16);
			moveY = yOffset;
		}
		if(moveX != 0 || moveY != 0) {
			moveCamera(moveX, moveY);
		}
	}
	public void move(int xOffset, int yOffset)
	{
		int newX = getXPos() + xOffset;
		int newY = getYPos() + yOffset;
		if(newX >= 0 && newX < getWidth()) {
			setXPos(newX);
		} else if(newX < 0) {
			setXPos(0);
		} else {
			setXPos(getWidth()-1);
		}
		if(newY >= 0 && newY < getHeight()) {
			setYPos(newY);
		} else if(newY < 0) {
			setYPos(0);
		} else {
			setYPos(getHeight()-1);
		}
		centreCamera();
	}
	public void moveCamera(int xOffset, int yOffset)
	{
		int newX = getViewX() + xOffset;
		int newY = getViewY() + yOffset;
		if(newX >= 0 && newX+getViewW() < width) {
			setViewX(newX);
		} else if(newX < 0) {
			setViewX(0);
		} else {
			setViewX(width-getViewW());
		}
		if(newY >= 0 && newY+getViewH() < height) {
			setViewY(newY);
		} else if(newY < 0) {
			setViewY(0);
		} else {
			setViewY(height-getViewH());
		}
	}
	public void centreCamera()
	{
		int minW = Math.max(0, getXPos()- getZoomedW()/2);
		int maxW = Math.min(getWidth() - 1 - getZoomedW(), getXPos()- getZoomedW()/2);
		int minH = Math.max(0, getYPos()- getZoomedH()/2);
		int maxH = Math.min(getHeight() - 1 - getZoomedH(), getYPos() - getZoomedH()/2);
		if(minW == 0) {
			setViewX(minW);
		} else if(maxW >= 0){
			setViewX(maxW);
		}
		if(minH == 0) {
			setViewY(minH);
		} else if(maxH >= 0){
			setViewY(maxH);
		}
	}
	public void zoom(int change)
	{
		if(zoomLevel+change/10 >= 0 && zoomLevel+change/10 < zoomLevels.length) {
			if(getViewW()*zoomLevels[zoomLevel] < getWidth() && getViewH()*zoomLevels[zoomLevel] < getHeight()) {
				zoomLevel += change/10;
				centreCamera();
			}
		}
	}
	public void moveTo(int xPos, int yPos)
	{
		if(xPos >= 0 && xPos < getWidth()) {
			setXPos(xPos);
		} else if(xPos < 0) {
			setXPos(0);
		} else {
			setXPos(width-1);
		}
		if(yPos >= 0 && yPos < getHeight()) {
			setYPos(yPos);
		} else if(yPos < 0) {
			setYPos(0);
		} else {
			setYPos(height-1);
		}
		centreCamera();
	}
	
	
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int width)
	{
		this.width = width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int height)
	{
		this.height = height;
	}
	public int getXPos()
	{
		return xPos;
	}
	public void setXPos(int xPos)
	{
		this.xPos = xPos;
	}
	public int getYPos()
	{
		return yPos;
	}
	public void setYPos(int yPos)
	{
		this.yPos = yPos;
	}
	public int getViewW()
	{
		return viewW;
	}
	public void setViewW(int viewW)
	{
		this.viewW = viewW;
	}
	public int getViewH()
	{
		return viewH;
	}
	public void setViewH(int viewH)
	{
		this.viewH = viewH;
	}
	public int getViewX()
	{
		return viewX;
	}
	public void setViewX(int viewX)
	{
		if(viewX >= 0 && viewX+viewW <= width)
			this.viewX = viewX;
	}
	public int getViewY()
	{
		return viewY;
	}
	public void setViewY(int viewY)
	{
		if(viewY >= 0 && viewY+viewH <= height)
			this.viewY = viewY;
	}
	public Color getBgCol()
	{
		return bgCol;
	}
	public void setBgCol(Color bgCol)
	{
		this.bgCol = bgCol;
	}
	public Image getView()
	{
		return null;
	}
	public int getZoomLevel()
	{
		return zoomLevel;
	}
	public void setZoomLevel(int zoomLvl)
	{
		this.zoomLevel = zoomLvl;
	}
	public double getZoom()
	{
		return zoomLevels[zoomLevel];
	}
	public int getZoomedW()
	{
		return /*(int) (*/viewW/*zoomLevels[zoomLevel])*/;
	}
	public int getZoomedH()
	{
		return /*(int) (*/viewH/*zoomLevels[zoomLevel])*/;
	}
	public int getSelectX()
	{
		return selectX;
	}
	public void setSelectX(int selectX)
	{
		this.selectX = selectX;
	}
	public int getSelectY()
	{
		return selectY;
	}
	public void setSelectY(int selectY)
	{
		this.selectY = selectY;
	}
}

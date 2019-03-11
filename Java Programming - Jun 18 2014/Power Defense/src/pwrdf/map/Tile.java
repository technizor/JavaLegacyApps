package pwrdf.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import pwrdf.res.ImageRes;

public class Tile
{
	public static final int EMPTYID = 0;
	public static final int BLOCKEDID = 1;
	public static final int PATHBEGID = 2;
	public static final int PATHENDID = 3;
	public static final Tile emptyTile = new Tile(EMPTYID, true, true);
	public static final Tile blockedTile = new Tile(BLOCKEDID, false, false);
	public static final Tile pathEndA = new Tile(PATHBEGID, false, true);
	public static final Tile pathEndB = new Tile(PATHENDID, false, true);
	public static final Tile[] tileSet = {emptyTile, blockedTile, pathEndA, pathEndB};
	public static final int TILESIZE = 32;
	public static final BufferedImage tileImage = ImageRes.getImage(ImageRes.tileSetFile);
	
	private int id;
	private int metaData1;
	private boolean placeable;
	private boolean path;
	public Tile(int presetTileId)
	{
		this.id = presetTileId;
		this.placeable = tileSet[this.id].isPlaceable();
		this.path = tileSet[this.id].isPath();
		this.metaData1 = 0;
	}
	public Tile()
	{
		this.id = 0;
		this.placeable = false;
		this.path = false;
		this.metaData1 = 0;
	}
	public Tile(int id, boolean placeable, boolean path)
	{
		this.id = id;
		this.placeable = placeable;
		this.path = path;
		this.metaData1 = 0;
	}
	public BufferedImage drawImage(Color mapCol, Tile[][] square)
	{
		BufferedImage buf = new BufferedImage(Tile.TILESIZE, Tile.TILESIZE, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = buf.createGraphics();
		g2D.setColor(mapCol);
		g2D.fillRect(0, 0, buf.getWidth(), buf.getHeight());
		switch(id) {
		case PATHENDID:
			g2D.drawImage(tileImage.getSubimage(TILESIZE*metaData1+TILESIZE*4, 0, TILESIZE, TILESIZE), 0, 0, null);
			metaData1 = (metaData1+11)%12;
			break;
		case PATHBEGID:
			g2D.drawImage(tileImage.getSubimage(TILESIZE*metaData1+TILESIZE*4, 0, TILESIZE, TILESIZE), 0, 0, null);
			metaData1 = (metaData1+1)%12;
			break;
		case BLOCKEDID:
			g2D.drawImage(getBlock(square), 0, 0, null);
			break;
		default:
			g2D.drawImage(tileImage.getSubimage(TILESIZE*3, 0, TILESIZE, TILESIZE), 0, 0, null);
		}
		return buf;
	}
	private Image getBlock(Tile[][] square)
	{
		if(square[0][1].id != Tile.BLOCKEDID && square[2][1].id != Tile.BLOCKEDID
				&& square[1][0].id != Tile.BLOCKEDID && square[1][2].id != Tile.BLOCKEDID) {
			return tileImage.getSubimage(TILESIZE, TILESIZE, TILESIZE, TILESIZE);
		} else if(square[0][1].id == Tile.BLOCKEDID && square[2][1].id == Tile.BLOCKEDID
				&& square[1][0].id == Tile.BLOCKEDID && square[1][2].id == Tile.BLOCKEDID
				&& square[0][0].id == Tile.BLOCKEDID && square[2][2].id == Tile.BLOCKEDID
				&& square[2][0].id == Tile.BLOCKEDID && square[0][2].id == Tile.BLOCKEDID) {
			return tileImage.getSubimage(2*TILESIZE, 6*TILESIZE, TILESIZE, TILESIZE);
		} else if(square[0][1].id == Tile.BLOCKEDID && square[2][1].id == Tile.BLOCKEDID
				&& square[1][0].id == Tile.BLOCKEDID && square[1][2].id == Tile.BLOCKEDID
				&& square[0][0].id != Tile.BLOCKEDID && square[2][2].id != Tile.BLOCKEDID
				&& square[2][0].id != Tile.BLOCKEDID && square[0][2].id != Tile.BLOCKEDID) {
			return tileImage.getSubimage(TILESIZE, 6*TILESIZE, TILESIZE, TILESIZE);
		} else if(square[0][1].id == Tile.BLOCKEDID && square[2][1].id != Tile.BLOCKEDID
				&& square[1][0].id == Tile.BLOCKEDID && square[1][2].id != Tile.BLOCKEDID
				&& square[0][0].id == Tile.BLOCKEDID) {
			return tileImage.getSubimage(TILESIZE, 4*TILESIZE, TILESIZE, TILESIZE);
		} else if(square[0][1].id == Tile.BLOCKEDID && square[2][1].id != Tile.BLOCKEDID
				&& square[1][0].id == Tile.BLOCKEDID && square[1][2].id != Tile.BLOCKEDID
				&& square[0][0].id != Tile.BLOCKEDID) {
			return tileImage.getSubimage(2*TILESIZE, 2*TILESIZE, TILESIZE, TILESIZE);
		}
		return tileImage.getSubimage(TILESIZE, TILESIZE, TILESIZE, TILESIZE);
	}
	public int getTileId()
	{
		return id;
	}
	public boolean isPlaceable()
	{
		return placeable;
	}
	public boolean isPath()
	{
		return path;
	}
	public int getMetaData1()
	{
		return metaData1;
	}
}

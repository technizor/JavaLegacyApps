package boltstorm.debug;

import boltstorm.map.Map;
import boltstorm.map.Tile;

public class TestingMap extends Map
{

	public TestingMap(int width, int height) {
		super(width, height);
		for(int i = 0; i < height/TILESCALE; i++) {
			getTileset()[3][i] = new Tile(1, 1, 1, true);
			getTileset()[27][i] = new Tile(1, 1, 1, true);
		}
	}

}
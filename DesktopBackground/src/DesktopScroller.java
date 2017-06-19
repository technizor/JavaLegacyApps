import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DesktopScroller extends JFrame {
	private static final long serialVersionUID = 1886760376951598669L;
	ArrayList<BufferedImage> backgrounds;
	ArrayList<Integer> backgroundOffsets;
	Dimension windowSize;
	boolean vertical;
	int offset;
	int maxOffset;
	int scrollSpeed;
	int n;
	static final String[] imgExt = { ".PNG", ".JPG", ".JPEG", ".BMP", ".TIF",
			".TIFF" };

	public DesktopScroller(int scrollSpeed, boolean vertical) {
		this.scrollSpeed = scrollSpeed;
		this.vertical = vertical;
		windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(windowSize);

		loadImages();

		setUndecorated(true);
		setResizable(false);
		setSize(windowSize);
		setLocationRelativeTo(null);
		setFocusableWindowState(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		repaint();
		toBack();
		Thread t = new Thread() {
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					scroll();
				}
			}
		};
		t.start();
	}

	private void loadImages() {
		backgrounds = new ArrayList<BufferedImage>();
		backgroundOffsets = new ArrayList<Integer>();
		File[] files = null;
		try {
			files = new File(new File(".").getCanonicalPath()).listFiles();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		if (files == null)
			System.exit(1);
		maxOffset = 0;
		offset = 0;
		for (File f : files) {
			try {
				BufferedImage tmp = ImageIO.read(f);

				if (tmp != null) {
					BufferedImage buf;

					if (vertical)
						buf = new BufferedImage(
								(int) (windowSize.getWidth()),
								(int) (tmp.getHeight() * windowSize.getWidth() / tmp
										.getWidth()),
								BufferedImage.TYPE_INT_ARGB);
					else
						buf = new BufferedImage((int) (tmp.getWidth()
								* windowSize.getHeight() / tmp.getHeight()),
								(int) (windowSize.getHeight()),
								BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2D = (Graphics2D) buf.getGraphics();

					g2D.drawImage(tmp, 0, 0, buf.getWidth(), buf.getHeight(),
							null);
					backgrounds.add(buf);
					backgroundOffsets.add(new Integer(maxOffset));
					if (vertical)
						maxOffset += buf.getHeight();
					else
						maxOffset += buf.getWidth();
				}
			} catch (IOException e) {
				System.out.println("Could not read file: " + f.getName());
			} catch (NullPointerException npe) {
				System.out.println("Unknown Error for file: " + f.getName());
			}
		}
		n = backgrounds.size();
		if (n == 0)
			System.exit(1);
	}

	public void scroll() {
		offset = (offset + scrollSpeed) % maxOffset;
	}

	public void paint(Graphics g) {
		BufferedImage buf = new BufferedImage((int) (windowSize.getWidth()),
				(int) (windowSize.getHeight()), BufferedImage.TYPE_INT_ARGB);
		Graphics g2D = buf.getGraphics();
		g2D.fillRect(0,0,(int)(windowSize.getWidth()), (int)(windowSize.getHeight()));
		if (maxOffset - offset < maxOffset - backgroundOffsets.get(n - 1)) {
			int a1 = backgroundOffsets.get(n - 1);
			BufferedImage b1 = backgrounds.get(n - 1);
			int a2 = backgroundOffsets.get(0);
			BufferedImage b2 = backgrounds.get(0);
			if (vertical) {
				g2D.drawImage(b1, 0, a1 - offset, null);
				g2D.drawImage(b2, 0, a2 - offset, null);
			} else {
				g2D.drawImage(b1, a1 - offset, 0, null);
				g2D.drawImage(b2, a2 - offset, 0, null);
			}
		} else {
			for (int i = 0; i < n; i++) {
				int a = backgroundOffsets.get(i);
				BufferedImage b = backgrounds.get(i);
				if (vertical) {
					int c = b.getHeight() + a;
					if (offset >= a || (int)(offset+windowSize.getHeight()) > c)
						g2D.drawImage(b, 0, a - offset, null);
				} else {
					int c = b.getWidth();
					if (offset >= a || (int)(offset+windowSize.getWidth()) > c)
						g2D.drawImage(b, a - offset, 0, null);
				}
			}
		}
		g.drawImage(buf, 0, 0, null);
	}

	public static void main(final String[] args) {
		new DesktopScroller(100, false);
	}
}

package kit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 将Windows中的聚焦图片复制到其他目录下。
 * 并转换成图片格式的文件。
 */
public class AssetToJpg {
	public static void main(String[] args) throws IOException {
		AssetToJpg assetToJpg = new AssetToJpg();
		assetToJpg.move();
	}
	
	public void move() {
		File originAsset = new File("C:\\Users\\liuyongjian\\AppData\\Local\\Packages\\"
				+ "Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets");
		
		File file = new File("D:\\work\\Assets");
		for (File f : file.listFiles()) {
			f.delete();
		}
		
		for (File f : originAsset.listFiles()) {
			try {
				BufferedImage bi = ImageIO.read(f);
				if (f.length() > (200 * 1024) && bi.getHeight() < bi.getWidth()) {
					File destFile = new File("D:\\work\\Assets\\" + f.getName() + ".jpg");
					f.renameTo(destFile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

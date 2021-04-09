/*
 @author taísa
 */
package testes;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Aula09_OperAritmetica extends JFrame {

	private BufferedImage bImage = null;

	public static void main(String[] args) {

		int opcao = 0;
		Scanner sc = new Scanner(System.in);
		String op = "+";
		while (opcao < 1 || opcao > 4) {

			System.out.println("Escolha a operação:");
			System.out.println("1 - Soma");
			System.out.println("2 - Subtração");
			System.out.println("3 - Multiplicação");
			System.out.println("4 - Divisão");
			opcao = sc.nextInt();

			if (opcao < 1 || opcao > 4) {
				System.out.println("\nOpção invalida\n");
			}
		}
		sc.close();
		//Usei o JFileChooser, ele abre uma janela para selecionar imagem, ai tem que minimizar o codigo para ver a janela
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "png");
		JFileChooser choose = new JFileChooser();
		choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
		choose.setAcceptAllFileFilterUsed(false);
		choose.addChoosableFileFilter(filter);

		choose.showOpenDialog(null);
		String img1 = "";
		img1 = choose.getSelectedFile().getAbsolutePath();
		System.out.println(img1);

		choose.showOpenDialog(null);
		String img2 = "";
		img2 = choose.getSelectedFile().getAbsolutePath();
		System.out.println(img2);

		Aula09_OperAritmetica oper = new Aula09_OperAritmetica(img1, op, img2, opcao);
		oper.setVisible(true);
	}

	public Aula09_OperAritmetica(String img1, String op, String img2, int opcao) {

		BufferedImage bfImg1 = null, bfImg2 = null, bfDest = null;
		JLabel img1L, img2L, img3L;
		int w2, h2;
		
		File arq1 = new File(img1);
		try {
			bfImg1 = ImageIO.read(arq1);

			bfImg1 = conv2BImg(bfImg1, bfImg1.getWidth(null), bfImg1.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
		} catch (IOException e) {
			System.out.println("Problema ao abrir a imagem 1");
			System.exit(0);
		}

		File arq2 = new File(img2);
		try {
			bfImg2 = ImageIO.read(arq2);

			bfImg2 = conv2BImg(bfImg2, bfImg2.getWidth(null), bfImg2.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
		} catch (IOException e) {
			System.out.println("Problema ao abrir a imagem 2");
			System.exit(0);
		}

		if (bfImg1.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			System.out.println("A imagem 1 não é TYPE_BYTE_GRAY");
			System.exit(0);
		}
		if (bfImg2.getType() != BufferedImage.TYPE_BYTE_GRAY) {
			System.out.println("A imagem 2 não é TYPE_BYTE_GRAY");
			System.exit(0);
		}

		if (bfImg1.getWidth() > bfImg2.getWidth() || bfImg1.getHeight() > bfImg2.getHeight()) {
			System.out.println("A imagem 1 é maior que a imagem 2");
			System.exit(0);
		}
		
		try {
			 bImage = conv2BImg(bfImg1, bfImg1.getWidth(null), bfImg1.getHeight(null),
					BufferedImage.TYPE_BYTE_GRAY);
		} catch (Exception e) {
			System.out.println("Problema em converter a imagem");
			System.exit(0);
		}
		try {
			bImage = conv2BImg(bfImg1, bfImg1.getWidth(null),
					bfImg1.getHeight(null),
					BufferedImage.TYPE_BYTE_GRAY);
			bfImg1 = bImage;
		} catch (Exception e) {
			System.out.println("Problema na conversÃ£o da imagem");
			System.exit(0);
		}
			
		try {
			bImage = conv2BImg(bfImg2, bfImg2.getWidth(null),
					bfImg1.getHeight(null),
					BufferedImage.TYPE_BYTE_GRAY);
			bfImg2 = bImage;
		} catch (Exception e) {
			System.out.println("Problema na conversÃ£o da imagem");
			System.exit(0);
		}
		

		switch (opcao) {
		case 1:
			bfDest = OperacaoSoma(bfImg1, bfImg2);

			w2 = bfImg2.getWidth();
			h2 = bfImg2.getHeight();
			getContentPane().setLayout(new GridLayout(1, 3));
			img1L = new JLabel(new ImageIcon(bfImg1));
			img2L = new JLabel(new ImageIcon(bfImg2));
			img3L = new JLabel(new ImageIcon(bfDest));

			getContentPane().add(new JScrollPane(img1L));
			getContentPane().add(new JScrollPane(img2L));
			getContentPane().add(new JScrollPane(img3L));
			setSize(4 * w2, h2);
			break;
			
		case 2:
			bfDest = OperacaoSubtrai(bfImg1, bfImg2);

			w2 = bfImg2.getWidth();
			h2 = bfImg2.getHeight();
			getContentPane().setLayout(new GridLayout(1, 3));
			img1L = new JLabel(new ImageIcon(bfImg1));
			img2L = new JLabel(new ImageIcon(bfImg2));
			img3L = new JLabel(new ImageIcon(bfDest));

			getContentPane().add(new JScrollPane(img1L));
			getContentPane().add(new JScrollPane(img2L));
			getContentPane().add(new JScrollPane(img3L));
			setSize(4 * w2, h2);
			break;

		case 3:
			bfDest = OperacaoMultiplica(bfImg1, bfImg2);

			w2 = bfImg2.getWidth();
			h2 = bfImg2.getHeight();

			getContentPane().setLayout(new GridLayout(1, 3));
			img1L = new JLabel(new ImageIcon(bfImg1));
			img2L = new JLabel(new ImageIcon(bfImg2));
			img3L = new JLabel(new ImageIcon(bfDest));

			getContentPane().add(new JScrollPane(img1L));
			getContentPane().add(new JScrollPane(img2L));
			getContentPane().add(new JScrollPane(img3L));
			setSize(4 * w2, h2);
			break;

		case 4:
			bfDest = OperacaoDivide(bfImg1, bfImg2);

			w2 = bfImg2.getWidth();
			h2 = bfImg2.getHeight();

			getContentPane().setLayout(new GridLayout(1, 3));
			img1L = new JLabel(new ImageIcon(bfImg1));
			img2L = new JLabel(new ImageIcon(bfImg2));
			img3L = new JLabel(new ImageIcon(bfDest));

			getContentPane().add(new JScrollPane(img1L));
			getContentPane().add(new JScrollPane(img2L));
			getContentPane().add(new JScrollPane(img3L));
			setSize(4 * w2, h2);
			break;

		}
	}
	private BufferedImage OperacaoSoma(BufferedImage bfImg1, BufferedImage bfImg2) {

		int w, h, px1, px2, result = 0, tipo;

		w = bfImg1.getWidth();
		h = bfImg1.getHeight();
		tipo = BufferedImage.TYPE_BYTE_GRAY;
		BufferedImage dest = new BufferedImage(w, h, tipo);

		Raster img1Raster = bfImg1.getRaster();
		Raster img2Raster = bfImg2.getRaster();
		WritableRaster destWR = dest.getRaster();

		for (int y = 0; y < h; y++)
			for (int x = 0; x < w; x++) {
				px1 = img1Raster.getSample(x, y, 0);
				px2 = img2Raster.getSample(x, y, 0);

				result = px1 + px2;
				if (result > 255) {
					result = 255;
				}
				destWR.setSample(x, y, 0, result);
			}
		return dest;
	}

	private BufferedImage OperacaoSubtrai(BufferedImage bfImg1, BufferedImage bfImg2) {

		int w, h, px1, px2, result = 0, tipo;

		w = bfImg1.getWidth();
		h = bfImg1.getHeight();
		tipo = BufferedImage.TYPE_BYTE_GRAY;
		BufferedImage dest = new BufferedImage(w, h, tipo);

		Raster img1Raster = bfImg1.getRaster();
		Raster img2Raster = bfImg2.getRaster();
		WritableRaster destWR = dest.getRaster();

		for (int y = 0; y < h; y++)
			for (int x = 0; x < w; x++) {
				px1 = img1Raster.getSample(x, y, 0);
				px2 = img2Raster.getSample(x, y, 0);

				result = px1 - px2;
				if (result < 0) {
					result = 0;
				}
				destWR.setSample(x, y, 0, result);
			}
		return dest;
	}

	private BufferedImage OperacaoMultiplica(BufferedImage bfImg1, BufferedImage bfImg2) {

		int w, h, px1, px2, result = 0, tipo;

		w = bfImg1.getWidth();
		h = bfImg1.getHeight();
		tipo = BufferedImage.TYPE_BYTE_GRAY;
		BufferedImage dest = new BufferedImage(w, h, tipo);

		Raster img1Raster = bfImg1.getRaster();
		Raster img2Raster = bfImg2.getRaster();
		WritableRaster destWR = dest.getRaster();

		for (int y = 0; y < h; y++)
			for (int x = 0; x < w; x++) {
				px1 = img1Raster.getSample(x, y, 0);
				px2 = img2Raster.getSample(x, y, 0);
				
				result = px1 * px2;
				if (result > 255) {
					result = 255;
				}

				destWR.setSample(x, y, 0, result);
			}
		return dest;
	}

	private BufferedImage OperacaoDivide(BufferedImage bfImg1, BufferedImage bfImg2) {

		int w, h, px1, px2, result = 0, tipo;

		w = bfImg1.getWidth();
		h = bfImg1.getHeight();
		tipo = BufferedImage.TYPE_BYTE_GRAY;
		BufferedImage dest = new BufferedImage(w, h, tipo);

		Raster img1Raster = bfImg1.getRaster();
		Raster img2Raster = bfImg2.getRaster();
		WritableRaster destWR = dest.getRaster();

		for (int y = 0; y < h; y++)
			for (int x = 0; x < w; x++) {
				px1 = img1Raster.getSample(x, y, 0);
				px2 = img2Raster.getSample(x, y, 0);

				result = (px1) / (px2 + 1);

				if (result >= 1 / 255 && result <= 1) {
					result = result * 127 + 1;
				} else {
					result = 128 + (result / 2);
				}
				if (result <= 1) {
					result = (127 * (result + 1));
				} else {
					result = (128 + (result / 2));
				}

				destWR.setSample(x, y, 0, result);
			}
		return dest;
	}

	private static BufferedImage conv2BImg(Image img, int w, int h, int type) {
		BufferedImage bI = new BufferedImage(w, h, type);

		Graphics2D g2D = bI.createGraphics();
		g2D.drawImage(img, 0, 0, null);

		return bI;
	}

}

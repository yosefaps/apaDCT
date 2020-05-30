package sample;

import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.plugin.javascript.navig.Image;
//import sun.security.mscapi.KeyStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Controller extends JFrame{
    JButton button ;
    JLabel label;
    JMenuBar menubar;
    JFileChooser chooser;
    @FXML
    Button btnKeluar, btnBrowsing, btnProcess;
    @FXML
    ImageView myImageView, myImageView1;
    @FXML
    Scene scene;
    @FXML
    Stage stage;
    @FXML
    RootPaneContainer root;
    public static int i ,j, k, l ;
    public static double pi = 3.142857;

//    public void initialize(){
//        btnProcess.setDisable(true);
//    }

    public void handleBtnBrowsing() {
     //add image 1(optional)
     FileChooser filechooser = new FileChooser();

     FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
     FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
     FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
     FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
     FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.JPEG)", "*.JPEG");
     FileChooser.ExtensionFilter extFilterjpeg = new FileChooser.ExtensionFilter("jpeg files (*.jpeg)", "*.jpeg");
     filechooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng, extFilterJPEG, extFilterjpeg);

     File file = filechooser.showOpenDialog(null);
     try {
         BufferedImage bufferedImage = ImageIO.read(file);
         WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
         myImageView.setImage(image);
     } catch (IOException ex) {
         Logger.getLogger(JavaFixPixel.class.getName()).log(Level.SEVERE, null, ex);
     }

}

 @FXML public void handleBtnProcess (){

        //ambil myImageView
        BufferedImage img = SwingFXUtils.fromFXImage(myImageView.getImage(), null);

        //image for grayscalimg
     BufferedImage grayscaleImage = new BufferedImage(img.getWidth(),
                 img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        double[][] dct = new double[img.getHeight()][img.getWidth()];
        double ci, cj, dct1, sum;
     //convert matrix dan grayscall
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                //get rgb color on each pixel
                Color c = new Color(img.getRGB(j, i));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int a = c.getAlpha();
//             System.out.print(r + "." + g + "." + b + " ");
//           grayscalling
                int gr = (r + g + b) / 3;

                //DCT
                 if (i == 0)
                     ci = 1 / Math.sqrt(img.getHeight());
                 else
                     ci = Math.sqrt(2) / Math.sqrt(img.getHeight());
                 if (j == 0)
                     cj = 1 / Math.sqrt(img.getWidth());
                 else
                     cj = Math.sqrt(2) / Math.sqrt(img.getWidth());
                 sum = 0;
                 for (int k = 0; k < img.getHeight(); k++) {
                     for (int l = 0; l < img.getWidth(); l++) {
                         dct1 = gr *
                                 Math.cos((2 * k + 1) * i * pi / (2 * img.getHeight())) *
                                 Math.cos((2 * l + 1) * j * pi / (2 * img.getWidth()));
                         sum = sum + dct1;
                     }
                 }

                dct[i][j] = ci * cj * sum;

//            create image 
//              Color gColor = new Color((int)dct[i][j]<<16 |(int)dct[i][j]<<8 |(int)dct[i][j]|a <<24);
//              grayscaleImage.setRGB(i, j, gColor.getRGB());
////                System.out.printf("%d \n", gr);


            }
//         System.out.println();

        }

    for (int i = 0; i < img.getHeight(); i++) {
        for (int j = 0; j < img.getWidth(); j++) {
            System.out.printf("%f \t", dct[i][j]);
        }
        System.out.println();
    }
     //save or masukin ke myImageView1
//      WritableImage image2 = SwingFXUtils.toFXImage(img, null);
//             myImageView1.setImage(image2);
//         System.out.println("end");
     
     
     
// try{
//      ImageIO.write(grayscaleImage, "jpg", new File(
//                     "C:\\Users\\irene dan yosef\\Documents\\UMN Yosef\\Skripsi\\New folder\\pic\\ZZ3.jpg"));
//  } catch (IOException e){
// //        //1000 auto-generated catch block
//         e.printStackTrace();
//     }



//try{
//        System.out.print(grayscaleImage);
//        System.out.println();
//        for (int i = 0; i < img.getWidth(); i++) {
//            for (int j = 0; j < img.getHeight(); j++) {
//                System.out.printf("%f \t", dct[i][j]);
//                img.setRGB(i, j, dct[i][j]);
//                int img2 = (int)dct[i][j]<<16 | (int)dct[i][j] << 8 | (int)dct[i][j];
//                grayscaleImage.setRGB(i, j, img2);
//            }
//            System.out.println();
//            ImageIO.write(grayscaleImage, "jpg", new File(
//                    "C:\\Users\\irene dan yosef\\Documents\\UMN Yosef\\Skripsi\\New folder\\pic\\ZZ.jpg"));
//            System.out.println("end");
//        }
//} catch (IOException e){
//    //1000 auto-generated catch block
//    e.printStackTrace();
//}
//try{
//     int xLenght = dct.length;
//     int yLength = dct[0].length;
//     BufferedImage b = new BufferedImage(xLenght, yLength, 3);
//
//     for(int x = 0; x < xLenght; x++) {
//         for(int y = 0; y < yLength; y++) {
//             int img2 = (int)dct[x][y]<<16 | (int)dct[x][y] << 8 | (int)dct[x][y];
//             b.setRGB(x, y, img2);
//         }
//     }
//     ImageIO.write(b, "jpg", new File(
//             "C:\\Users\\irene dan yosef\\Documents\\UMN Yosef\\Skripsi\\New folder\\pic\\bbv2.jpg"));
//              System.out.println("end");
//} catch (IOException e){
//        //1000 auto-generated catch block
//        e.printStackTrace();
//    }

//     //cobvert 1-d
//     int width = dct.length;
//     int height = dct[0].length;
//     int i=0;
//     int[]pixarray = new int[width*height];
//     for(int y= 0; y<height; y++){
//         for (int x=0; x<width; x++){
//             pixarray[i++] = (int) dct[x][y];
//         }
//     }
//     //convert 1-d ke image
//     MemoryImageSource source = new MemoryImageSource(width, height, pixarray, 0, width);
//     ImageIO imageto =myImageView1.createImage(source);
//     ImageIcon img2 - new ImageIcon(imageto);
//     myImageView.setImage(img2);



//    try{
//            ImageIO.write(grayscaleImage, "jpg", new File(
//             "C:\\Users\\irene dan yosef\\Documents\\UMN Yosef\\Skripsi\\New folder\\pic\\bbv.jpg"));
//         } catch (IOException e){
//        //1000 auto-generated catch block
//        e.printStackTrace();
//    }


//            write to file
//            String Hasil = "C:\\\\Users\\\\irene dan yosef\\\\Documents\\\\UMN Yosef\\\\Skripsi\\\\New folder\\\\pic\\\\C2.jpg";
//            ImageIO.Write(Hasil, dct[i][j]);
//

 }

// public void keyReleasedProperty(){
//     boolean isDisabled = myImageView.setVisible();
//     btnProcess.setDisable(isDisabled);
// }


//    public void closeForm(){
//        Stage stage = (Stage) btnKeluar.getScene().getWindow();
//        stage.close();
//    }

    @FXML public void handleBtnKeluar(){
        Stage stage = (Stage) btnKeluar.getScene().getWindow();
        stage.close();
    }


}

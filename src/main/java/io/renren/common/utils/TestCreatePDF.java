package io.renren.common.utils;

import com.google.zxing.WriterException;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestCreatePDF {

    @Autowired
    private static IdWorker idWorker;

    public static void main(String[] args) {

        String content= "1234567890987654321";
        // 生成的文件路径

        //String targetPath = "E:\\Git\\express-fast\\src\\main\\resources\\static\\template\\"+newFileName+".pdf";
        String targetPath = "src/main/resources/static/template/setWatermark.pdf";

        String targetPath2 = "src/main/resources/static/template/setWatermark2.pdf";

        int[]  size=new int[]{430,430};

        BufferedImage bufferedImage=null;
        try {
            bufferedImage=new QRCodeFactory().CreatQrImage1(content, "jpg", "", "",size);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        //页面大小
        Rectangle rect = new Rectangle(PageSize.A8);
        //页面背景色
        rect.setBackgroundColor(BaseColor.WHITE);
        //Step 1—Create a Document.
        Document document = new Document(rect);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(targetPath));
            //Step 3—Open the Document.
            document.open();

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            Font font = new Font(bfChinese, 9);
            document.open();

            // 根据域的大小缩放图片
            Image image= getImage(bufferedImage,99);
            image.setAlignment(Image.LEFT | Image.TEXTWRAP);
            image.setBorder(Image.BOX);
            image.setBorderWidth(5);
            image.setBorderColor(BaseColor.WHITE);
            image.scaleToFit(148, 148);
            image.setAbsolutePosition(1, 60);
            image.setRotationDegrees(0);//旋转
            document.add(image);

            Paragraph paragraph1 = new Paragraph();
            //居中显示
            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph1.add("");


            Paragraph paragraph2 = new Paragraph();
            //居中显示
            paragraph2.setAlignment(Element.ALIGN_CENTER);
            paragraph2.setFont(font);
            paragraph2.add("快递单后四位:1234");

            //段落2与段落1的间距加大100个单位
            paragraph2.setSpacingBefore(120);

            document.add(paragraph1);
            document.add(paragraph2);




            document.close();

//            PdfReader reader = new PdfReader(targetPath);
//            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath2));
//
//            // 获取操作的页面
//            PdfContentByte under = stamper.getOverContent(1);
//
//            //文字水印
//            under.beginText();
//            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,
//                    BaseFont.EMBEDDED);
//            under.setFontAndSize(bf, 18);
//            under.setTextMatrix(30, 30);
//            under.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);
//            under.endText();
//
//
//            // 根据域的大小缩放图片
//            Image image= getImage(bufferedImage,99);
//            image.setAlignment(Image.LEFT | Image.TEXTWRAP);
//            image.setBorder(Image.BOX);
//            image.setBorderWidth(5);
//            image.setBorderColor(BaseColor.WHITE);
//            image.scaleToFit(100, 100);
//            image.setAbsolutePosition(0, 0);
//            image.setRotationDegrees(0);//旋转
//            under.addImage(image);
//            //stamper.setFormFlattening(true);
//            stamper.close();
//            reader.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {



        }
    }

    public static Image getImage(BufferedImage bufferedImage, float percent) throws Exception{
        Image itextImage=null;
        itextImage=Image.getInstance(bufferedImage,new Color(255,255,255));
        itextImage.setWidthPercentage(0.1f);
        itextImage.setSpacingAfter(0.1f);
        itextImage.scalePercent(percent);
        itextImage.setBorder(Rectangle.NO_BORDER);
        return itextImage;
    }

}

package com.idxk.mobileoa.utils.common.android;

import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.idxk.mobileoa.R;

import java.io.File;

/**
 * 公用工具
 */
public class FileIntent {


    //android获取一个用于打开HTML文件的intent

    public static Intent getHtmlFileIntent( String param )

    {

        Uri uri = Uri.parse(param ).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param ).build();

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;

    }



    //android获取一个用于打开图片文件的intent

    public static Intent getImageFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "image/*");
        return intent;

    }



    //android获取一个用于打开PDF文件的intent

    public static Intent getPdfFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/pdf");

        return intent;

    }



    //android获取一个用于打开文本文件的intent

    public static Intent getTextFileIntent(  String paramString, boolean paramBoolean)

    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean)
        {

            Uri uri1 = Uri.parse(paramString );
            intent.setDataAndType(uri1, "text/plain");

        }
        while (true)

        {
            Uri uri2 = Uri.fromFile(new File(paramString) );
            intent.setDataAndType(uri2, "text/plain");
            return intent;




        }

    }











    //android获取一个用于打开音频文件的intent

    public static Intent getAudioFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "audio/*");

        return intent;

    }







    //android获取一个用于打开视频文件的intent

    public static Intent getVideoFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("oneshot", 0);

        intent.putExtra("configchange", 0);

        Uri uri = Uri.fromFile(new File(param ));

        intent.setDataAndType(uri, "video/*");

        return intent;

    }





    //android获取一个用于打开CHM文件的intent

    public static Intent getChmFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(new File(param ));

        intent.setDataAndType(uri, "application/x-chm");

        return intent;

    }









    //android获取一个用于打开Word文件的intent

    public static Intent getWordFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(new File(param ));

        intent.setDataAndType(uri, "application/msword");

        return intent;

    }


    /**
     *
     * @param path 路径
     * @param excete 类别
     * @return
     */
    public static Intent getOfficeIntent(String path,String excete){
        if (excete.toLowerCase().equals("doc")||excete.toLowerCase().equals("docx")){
            return getWordFileIntent(path);
        }

        if (excete.toLowerCase().equals("html")){
            return getHtmlFileIntent(path);
        }
        if (excete.toLowerCase().equals("jpg")||excete.toLowerCase().equals("png")||excete.toLowerCase().equals("bmp")){
            return getImageFileIntent(path);
        }
        if (excete.toLowerCase().equals("pdf")){
            return getPdfFileIntent(path);
        }
        if (excete.toLowerCase().equals("ppt")||excete.toLowerCase().equals("pptx")){
            return getPptFileIntent(path);
        }
        if (excete.toLowerCase().equals("xls")){
            return getExcelFileIntent(path);
        }
        return null;

    }



    //android获取一个用于打开Excel文件的intent

    public static Intent getExcelFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(new File(param ));

        intent.setDataAndType(uri, "application/vnd.ms-excel");

        return intent;

    }





    //android获取一个用于打开PPT文件的intent

    public static Intent getPptFileIntent( String param )

    {

        Intent intent = new Intent("android.intent.action.VIEW");

        intent.addCategory("android.intent.category.DEFAULT");

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Uri uri = Uri.fromFile(new File(param ));

        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");

        return intent;

    }

    public static void setOfficalImageByExcete(ImageView path, String excete) {
        if (excete.toLowerCase().equals("doc") || excete.toLowerCase().equals("docx")) {
            path.setImageResource(R.drawable.f_doc);
            return;
        }
        if (excete.toLowerCase().equals("approval")) {
            path.setImageResource(R.drawable.f_approval);
            return;
        }
        if (excete.toLowerCase().equals("jpg")) {
            path.setImageResource(R.drawable.f_jpg);
            return;
        }

        if (excete.toLowerCase().equals("png")) {
            path.setImageResource(R.drawable.f_png);
            return;
        }
        if (excete.toLowerCase().equals("bmp")) {
            path.setImageResource(R.drawable.f_bmp);
            return;
        }
        if (excete.toLowerCase().equals("pdf")) {
            path.setImageResource(R.drawable.f_pdf);
            return;
        }
        if (excete.toLowerCase().equals("ppt") || excete.toLowerCase().equals("pptx")) {
            path.setImageResource(R.drawable.f_ppt);
            return;
        }
        if (excete.toLowerCase().equals("xls") || excete.toLowerCase().equals("xlsx")) {
            path.setImageResource(R.drawable.f_xls);
            return;
        }
        path.setImageResource(R.drawable.f_defalut);
    }

}

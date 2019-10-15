package com.ravinada.cryptocompare.utils;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ravinada.cryptocompare.BuildConfig;
import com.ravinada.cryptocompare.CryptoCompareApp;
import com.ravinada.cryptocompare.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Utils {

    public static int likeCounter = 0;

    static Dialog loaderDialog;

    // NETWORK ------------------------------------------------------

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showToast(final String message) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(CryptoCompareApp.getInstance(), message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL , 0, pxToDp(CryptoCompareApp.getInstance(), 70));
                toast.show();
            }
        });
    }

    public static int pxToDp(Context context, float px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public static void hideDialog(){
       if (loaderDialog != null && loaderDialog.isShowing()){
           loaderDialog.dismiss();
       }
    }

    public static void showDialog(Context context) {
        loaderDialog = new Dialog(context, R.style.myDialog);
        Window window = loaderDialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setBackgroundDrawableResource(R.drawable.dialog_basic_transparent);
        loaderDialog.setContentView(R.layout.dialog_loader);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        loaderDialog.setCancelable(false);
        final ProgressBar loader = loaderDialog.findViewById(R.id.loader);

        loaderDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                loader.setVisibility(View.VISIBLE);
            }
        });

        loaderDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                loader.setVisibility(View.GONE);
            }
        });

        loaderDialog.show();
    }

    public static int getKv(String packageId){
        if (packageId.equalsIgnoreCase(BuildConfig.APPLICATION_ID)) {
            return 68471658;
        }
        return 0;
    }

    public static void downloadFile(String uRl,Context context) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/Personality");
        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(uRl);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                //.setAllowedOverRoaming(false).setTitle("Demo")
                .setDescription("Downloading Image.")
                .setDestinationInExternalPublicDir("/Personality_Development",System.currentTimeMillis()+".jpg");


        mgr.enqueue(request);

        boolean success = (new File(Environment.getExternalStorageDirectory()+"/Personality_Development")).mkdir();
        if (!success)
        {
        }

        try
        {
            URL url = new URL(uRl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            String data1 = String.valueOf(String.format(Environment.getExternalStorageDirectory()+"/Personality/%d.jpg",System.currentTimeMillis()));

            FileOutputStream stream = new FileOutputStream(data1);

            ByteArrayOutputStream outstream = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 85, outstream);
            byte[] byteArray = outstream.toByteArray();

            stream.write(byteArray);
            stream.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static String compressImage(String imageUri, Context mContext) {

        String filePath = getRealPathFromURI(imageUri, mContext);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public static String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath(), "Fllawi/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/"
                + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    private static String getRealPathFromURI(String contentURI, Context mContext) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor;

        cursor = mContext.getContentResolver().query(contentUri, null,
                null, null, null);

        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor
                    .getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public static RequestBody imageToBody(String text) {
        RequestBody requestBody;
        if (text != null && text.length() > 0) {
            MediaType MEDIA_TYPE = MediaType.parse("image/*");
            File file = new File(text);
            requestBody = RequestBody.create(MEDIA_TYPE, file);
        } else {
            requestBody = null;
        }
        return requestBody;
    }

}

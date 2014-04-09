package com.example.krumping;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

public class UploadActivity extends Activity {

    // Request code for selecting the vido to be uploaded.
    private static final int SELECT_VIDEO = 1;
    private static final String TAG = Utils.getTag(UploadActivity.class);

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_VIDEO) {
                Uri selectedVideoUri = data.getData();
                Log.i(TAG, "Video URI: " + selectedVideoUri);
                String selectedPath = getPath(selectedVideoUri);
                Log.i(TAG, "SELECT_VIDEO Path : " + selectedPath);
                // uploadVideo(selectedPath);
            }
        }
    }

    /**
     * Returns the file path for the content URI.
     * 
     * @param uri
     *            - content URI to get metadata
     * @return file path of the content
     */
    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Video.Media.DATA, MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        cursor.moveToFirst();
        String filePath = cursor.getString(cursor
            .getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
        int fileSize = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
        long duration = cursor
            .getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
        Log.i(TAG, "File size: " + fileSize + " Path: " + filePath + " Duration: " + duration);
        return filePath;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_layout);
    }

    /**
     * Method invoked when the user chooses to upload a video.
     * 
     * @param view
     */
    public void uploadVideo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, SELECT_VIDEO);
    }
}

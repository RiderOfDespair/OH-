package com.example.brendan.oh;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;



import java.util.HashMap;
import java.util.Map;


public class MyActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                    Map contactMap = new HashMap();

                    Uri queryUri = ContactsContract.Contacts.CONTENT_URI;

                    String[] projection = new String[] {
                            ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME,
                            ContactsContract.Contacts.STARRED};

                    String selection = ContactsContract.Contacts.STARRED + "='1'";

                Cursor cursor = getContentResolver().query(queryUri, projection, selection, null, ContactsContract.Contacts.DISPLAY_NAME);


                while (cursor.moveToNext()) {
                        String contactID = cursor.getString(cursor
                                .getColumnIndex(ContactsContract.Contacts._ID));

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        Uri uri = Uri.withAppendedPath(
                                ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactID));
                        intent.setData(uri);
                        String intentUriString = intent.toUri(0);

                        String title = (cursor.getString(
                                cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));

                        contactMap.put(title,intentUriString);
                    }

                    cursor.close();
                    startActivity(new Intent(MyActivity.this, MainActivity2.class));
                }

        });






    }


}

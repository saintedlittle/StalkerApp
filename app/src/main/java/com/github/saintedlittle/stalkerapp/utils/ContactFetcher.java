package com.github.saintedlittle.stalkerapp.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.core.app.ActivityCompat;

import com.github.saintedlittle.stalkerapp.data.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactFetcher {

    private final Context context;

    public ContactFetcher(Context context) {
        this.context = context;
    }

    public List<ContactData> fetchContacts() {
        List<ContactData> contacts = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            contacts = readContacts();
        }

        return contacts;
    }

    private List<ContactData> readContacts() {
        List<ContactData> contacts = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_ALTERNATIVE));
                @SuppressLint("Range") String profilePicture = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                String birthday = retrieveBirthday(contactId);

                ContactData contact = new ContactData();
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setProfilePicture(profilePicture);
                contact.setBirthday(birthday);

                // Fetch phone numbers
                List<String> phoneNumbers = retrievePhoneNumbers(contactId);
                contact.setPhoneNumber(phoneNumbers);

                // Fetch email addresses
                List<String> emailAddresses = retrieveEmailAddresses(contactId);
                contact.setEmail(emailAddresses);

                contacts.add(contact);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return contacts;
    }

    @SuppressLint("Range")
    private String retrieveBirthday(String contactId) {
        String birthday = "";

        Cursor birthdayCursor = context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?",
                new String[]{contactId, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE},
                null);

        if (birthdayCursor != null && birthdayCursor.moveToFirst()) {
            birthday = birthdayCursor.getString(birthdayCursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE));
            birthdayCursor.close();
        }

        return birthday;
    }

    private List<String> retrievePhoneNumbers(String contactId) {
        List<String> phoneNumbers = new ArrayList<>();

        Cursor phoneCursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{contactId},
                null);

        if (phoneCursor != null && phoneCursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneNumbers.add(phoneNumber);
            } while (phoneCursor.moveToNext());
            phoneCursor.close();
        }

        return phoneNumbers;
    }

    private List<String> retrieveEmailAddresses(String contactId) {
        List<String> emailAddresses = new ArrayList<>();

        Cursor emailCursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                new String[]{contactId},
                null);

        if (emailCursor != null && emailCursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String emailAddress = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                emailAddresses.add(emailAddress);
            } while (emailCursor.moveToNext());
            emailCursor.close();
        }

        return emailAddresses;
    }
}


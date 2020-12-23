package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dbdemo.data.MyDbHandler;
import com.example.dbdemo.model.Coontact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyDbHandler db =new MyDbHandler(MainActivity.this);
/*//creating a contact for db
        Coontact vishal = new Coontact();
        vishal.setPhoneNumber("1122334455");
        vishal.setName("vishal");
// Adding Contact To Db
        db.addContact(vishal);

        Coontact kushal = new Coontact();
        kushal.setPhoneNumber("1234567890");
        kushal.setName("kushal");
// Adding Contact To Db
        db.addContact(kushal);


        Coontact kaka = new Coontact();
        kaka.setPhoneNumber("0987654321");

        kaka.setName("kaka");
// Adding Contact To Db
        db.addContact(kaka);

        kaka.setId(22);
        kaka.setName("New Kaka");
        kaka.setPhoneNumber("0987890789");
        int affectRows = db.updateContact(kaka);




        Log.d("helloG", "no. of affected rows are " + affectRows);

//get all contacts
        db.deleteContactById(1);
        db.deleteContactById(2);
        db.deleteContactById(8);

 */

        ArrayList<String> contacts = new ArrayList<>();
       // listView =findViewById(R.id.listView);



        List<Coontact> allCoontacs = db.getAllCoontacts();
        for(Coontact contact: allCoontacs)
        {
            Log.d("piece","id" + contact.getId() + "\n" +
                    " Phone Number: " + contact.getPhoneNumber() + "\n" +
                    "Name: " + contact.getName() + "\n");


            contacts.add(contact.getName() + " (" + contact.getPhoneNumber()+ ")");

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(arrayAdapter);

      //  Log.d("hello", "Bro u have " + db.getCount() + "Contacts in your database");


    }
}
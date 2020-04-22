package com.example.gmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;


public class MainActivity extends AppCompatActivity {

    List<EmailItemModel> items;
    List<EmailItemModel> favoriteItems;
    List<EmailItemModel> searchIteams;
    List<String> name;
    List<String> content;
    List<String> subject;
    Button favarite;
    EditText editSearch;
    boolean isChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favarite = (Button) findViewById(R.id.btn_favorite);
        editSearch = (EditText) findViewById(R.id.edit_search);
        items = new ArrayList<>();

        Faker faker = new Faker();

        for (int i = 0; i < 15; i++)
            items.add(new EmailItemModel(faker.name.name(), faker.lorem.sentence(), faker.lorem.paragraph(), "12:00 PM"));
//        for (int i = 0; i < 15; i++) {
//            name.add(items.get(i).getName());
//            subject.add(items.get(i).getSubject());
//            content.add(items.get(i).getContent());
//        }

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final EmailItemAdapter adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);

        isChoose = false;

        favarite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChoose){
                    favoriteItems = new ArrayList<>();
                    for (int i = 0; i < items.size(); i++) {
                        if(items.get(i).isFavorite) {
                            favoriteItems.add(items.get(i));
                        }
                    }
                    EmailItemAdapter favoriteAdapter = new EmailItemAdapter(favoriteItems);
                    recyclerView.setAdapter(favoriteAdapter);
                }
                else {
                    recyclerView.setAdapter(adapter);
                }
                isChoose = !isChoose;
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String charString = s.toString();
                if (count > 1) {
                    searchIteams = new ArrayList<>();
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getName().toLowerCase().indexOf(charString) != -1 ||  items.get(i).getSubject().toLowerCase().indexOf(charString) != -1 || items.get(i).getContent().toLowerCase().indexOf(charString) != -1)
                            searchIteams.add(items.get(i));
                    }
                    EmailItemAdapter searchAdapter = new EmailItemAdapter(searchIteams);
                    recyclerView.setAdapter(searchAdapter);
                }
                else {
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}

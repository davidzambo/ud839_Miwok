package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, R.layout.list_item, words);
        this.mColorResourceId = colorResourceId;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.content_image);

        if (currentWord.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getImageResourceId());
        }

        if (!currentWord.hasImage()) {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.content_text_container);
        int color = ContextCompat.getColor(getContext(), this.mColorResourceId);
        textContainer.setBackgroundColor(color);

        TextView contentMiwok = (TextView) listItemView.findViewById(R.id.content_miwok);
        contentMiwok.setText(currentWord.getMiwokTranslation());

        TextView contentEnglish = (TextView) listItemView.findViewById(R.id.content_english);
        contentEnglish.setText(currentWord.getDefaultTranslation());

        return listItemView;
    }
}

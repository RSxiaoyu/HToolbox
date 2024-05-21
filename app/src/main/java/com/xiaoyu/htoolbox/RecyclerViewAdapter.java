package com.xiaoyu.htoolbox;// src/com/example/myapp/MyRecyclerViewAdapter.java


import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Item> items;
    private final LayoutInflater inflater;
    private final SharedPreferences sharedPreferences;

    public RecyclerViewAdapter(Context context, List<Item> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.sharedPreferences = context.getSharedPreferences((String) context.getText(R.string.dataset), Context.MODE_PRIVATE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        EditText editText;
        TextWatcher textWatcher;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            editText = itemView.findViewById(R.id.editText);
        }

        void bind(Item item) {
            textView.setText(item.getLabel());

            // Remove previous TextWatcher if any
            if (textWatcher != null) {
                editText.removeTextChangedListener(textWatcher);
            }

            // Set the current value to EditText
            editText.setText(String.valueOf(item.getValue()));

            // Create a new TextWatcher
            textWatcher = new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int data = 60 * 1000;
                    if (!s.toString().isEmpty()) {
                        data = Integer.parseInt(s.toString());
                    }
                    item.setValue(data);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(item.getKey(), data);
                    editor.apply();

                }

                public void afterTextChanged(Editable editable) {

                }

            };

            // Add the new TextWatcher
            editText.addTextChangedListener(textWatcher);
        }
    }
}

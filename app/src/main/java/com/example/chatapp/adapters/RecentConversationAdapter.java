package com.example.chatapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.databinding.ItemContainerRecentBinding;
import com.example.chatapp.listeners.ConversionListeners;
import com.example.chatapp.models.ChatMessage;
import com.example.chatapp.models.User;

import java.util.List;

public class RecentConversationAdapter extends RecyclerView.Adapter<RecentConversationAdapter.ConversionViewHolder> {

    private final List<ChatMessage> chatMessages;
    private final ConversionListeners conversionListeners;

    public RecentConversationAdapter(List<ChatMessage> chatMessages, ConversionListeners conversionListeners) {
        this.chatMessages = chatMessages;
        this.conversionListeners = conversionListeners;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(ItemContainerRecentBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,false
        )

        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
          holder.setData(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    class ConversionViewHolder extends RecyclerView.ViewHolder{

     ItemContainerRecentBinding binding;

     ConversionViewHolder(ItemContainerRecentBinding itemContainerRecentBinding){

         super(itemContainerRecentBinding.getRoot());
         binding = itemContainerRecentBinding;
     }
     void setData(ChatMessage chatMessage){
         binding.imageprofile.setImageBitmap(getConversionImage(chatMessage.conversionImage));
         binding.textName.setText(chatMessage.conversionName);
         binding.textRecentMessage.setText(chatMessage.message);
         binding.getRoot().setOnClickListener(v ->{
             User user = new User();
             user.id = chatMessage.conversionId;
             user.name = chatMessage.conversionName;
             user.image = chatMessage.conversionImage;
             conversionListeners.onConversionClicker(user);
         });


     }




    }
    private Bitmap getConversionImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage,Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}










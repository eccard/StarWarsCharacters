package com.eccard.starwarscharacters.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Charactter
import com.eccard.starwarscharacters.databinding.CharacterItemBinding
import com.eccard.starwarscharacters.ui.common.DataBoundListAdapter

class CharacterAdapter (appExecutors: AppExecutors,
                        private val characterClickCallback: ((Charactter) -> Unit)?) :
    DataBoundListAdapter<Charactter,CharacterItemBinding> (appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Charactter>(){
        override fun areItemsTheSame(oldItem: Charactter, newItem: Charactter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Charactter, newItem: Charactter): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.isMain == newItem.isMain &&
                    oldItem.id == newItem.id
        }
    }
    ){

    override fun createBinding(parent: ViewGroup): CharacterItemBinding {
        val binding = DataBindingUtil.inflate<CharacterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.character_item,
            parent,
            false
        )
        binding.root.setOnClickListener{
            binding.character?.let {
                characterClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: CharacterItemBinding, item: Charactter) {
        binding.character = item
    }
}
package com.eccard.starwarscharacters.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.CharacterAdapterPojo
import com.eccard.starwarscharacters.databinding.CharacterItemBinding
import com.eccard.starwarscharacters.ui.common.DataBoundListAdapter

class CharacterAdapter (appExecutors: AppExecutors,
                        private val characterClickCallback: ((CharacterAdapterPojo) -> Unit)?) :
    DataBoundListAdapter<CharacterAdapterPojo,CharacterItemBinding> (appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<CharacterAdapterPojo>(){
        override fun areItemsTheSame(oldItem: CharacterAdapterPojo, newItem: CharacterAdapterPojo): Boolean {
            return oldItem.charactter.id == newItem.charactter.id
        }

        private fun hasSameFoundContent(
            oldItem: CharacterAdapterPojo,
            newItem: CharacterAdapterPojo
        ): Boolean {
            var isSame = false
            if (oldItem.foundFilm == null && newItem.foundFilm == null) {
                isSame = true
            } else if (oldItem.foundFilm != null && newItem.foundFilm != null) {
                isSame = oldItem.foundFilm == newItem.foundFilm
            }
            return isSame
        }

        override fun areContentsTheSame(oldItem: CharacterAdapterPojo, newItem: CharacterAdapterPojo): Boolean {
            return oldItem.charactter.name == newItem.charactter.name &&
                    oldItem.charactter.isMain == newItem.charactter.isMain &&
                    oldItem.charactter.id == newItem.charactter.id && hasSameFoundContent(oldItem, newItem)
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
            binding.charPojo?.let {
                characterClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: CharacterItemBinding, item: CharacterAdapterPojo) {
        binding.charPojo = item
    }
}
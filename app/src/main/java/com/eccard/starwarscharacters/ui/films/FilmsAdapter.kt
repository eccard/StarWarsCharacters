package com.eccard.starwarscharacters.ui.films

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import coil.Coil
import coil.api.load
import coil.size.Scale
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.databinding.FilmItemBinding
import com.eccard.starwarscharacters.util.common.DataBoundListAdapter


class FilmsAdapter (appExecutors: AppExecutors,
                    private val characterClickCallback: ((Film) -> Unit)?) :
    DataBoundListAdapter<Film, FilmItemBinding>(appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Film>(){
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.completeName == newItem.completeName &&
                        oldItem.launchDate == newItem.launchDate &&
                        oldItem.charactersIds == newItem.charactersIds &&
                        oldItem.synopsis == newItem.synopsis &&
                        oldItem.director == newItem.director &&
                        oldItem.coverAlbum == newItem.coverAlbum
            }
        }
    ){

    override fun createBinding(parent: ViewGroup): FilmItemBinding {
        val binding = DataBindingUtil.inflate<FilmItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.film_item,
            parent,
            false
        )
        binding.root.setOnClickListener{
            binding.film?.let {
                characterClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: FilmItemBinding, item: Film) {
        binding.film = item
        setupImg(item,binding)
    }

    private fun setupImg(item: Film, binding: FilmItemBinding) {

        Coil.load(binding.imgFilmItem.context,item.coverAlbum){
            allowHardware(false)
            scale(Scale.FIT)
            target { drawable ->
                binding.imgFilmItem.background = drawable
                Palette.from(drawable.toBitmap()).generate{pallete ->
                    setUpInfoBackgroundColor(binding,pallete!!)
            }
        }

//        binding.imgFilmItem.load(item.coverAlbum){
//            scale(Scale.FIT)
//            allowHardware(false)
//            target(onSuccess = { result: Drawable ->
//                if (result is BitmapDrawable) {
//                    result.bitmap
//                    Palette.from(result.bitmap).generate{pallete ->
//                        setUpInfoBackgroundColor(binding,pallete!!)
//                    }
//                }
//            })
//            target { drawable ->
//                Palette.from(drawable.toBitmap()).generate{
//                    pallete ->
//                    setUpInfoBackgroundColor(binding,pallete!!)
//                }
//
//            }
        }

    }

    private fun setUpInfoBackgroundColor(binding: FilmItemBinding, palette: Palette) {

        var swatch = palette.vibrantSwatch
        //If there is no vibrant swatch, try getting dominant swatch
        if(swatch == null) {
            swatch = palette.dominantSwatch
        }

        // If there is no dominant swatch try getting muted swatch
        if(swatch == null) {
            swatch = palette.mutedSwatch
        }
        // If there is no dominant swatch try getting muted swatch
        swatch?.let {
            binding.imageLegend.setBackgroundColor(it.rgb)
            binding.tvFilmTitle.setTextColor(it.bodyTextColor)
            binding.tvFilmLauchDate.setTextColor(it.titleTextColor)
        }

    }
}
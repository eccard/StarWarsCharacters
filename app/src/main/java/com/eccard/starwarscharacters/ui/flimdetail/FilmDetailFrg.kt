package com.eccard.starwarscharacters.ui.flimdetail

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.databinding.FilmDetailFrgBinding
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.film_detail_frg.*


class FilmDetailFrg: Fragment() {
    companion object {
        private const val EXTRA_PLAYER_TIME_POSITION = "player-position-time"
        const val FILM_KEY = "FILME_KEY"
        fun newInstance ( film : Film) : FilmDetailFrg {
            val frg = FilmDetailFrg()
            val args = Bundle()
            args.putParcelable(FILM_KEY,film)
            frg.arguments = args
            return frg
        }
    }

    private var defautBgColor: Int = 0
    var fullscreen = false
    private var exoPlayer : SimpleExoPlayer? = null
    lateinit var binding :FilmDetailFrgBinding
    private var playerTimePosition: Long = 0
    private var film : Film? = null
    private var fullscreenButton : ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.film_detail_frg,container,false)
        binding.playerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
//        binding.playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val typedValue =  TypedValue()
        val theme = activity!!.theme;
        theme.resolveAttribute(R.attr.background, typedValue, true);
        defautBgColor = typedValue.data;

        savedInstanceState?.let {
            playerTimePosition = savedInstanceState.getLong(
                EXTRA_PLAYER_TIME_POSITION,
                0L
            )
        }



        arguments?.let {
            film = it.getParcelable<Film>(FILM_KEY)
            binding.film = film
        }
    }

    override fun onStart() {
        super.onStart()
        film?.let {
            if (Util.SDK_INT > 23) {
                setUpPlayer(it.trailer)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        film?.let {
            if ((Util.SDK_INT <= 23 || exoPlayer == null)) {
                setUpPlayer(it.trailer)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun setUpPlayer(stepUrlPath: String?) {

        if (!stepUrlPath.isNullOrBlank()){
            object : YouTubeExtractor(activity!!){
                override fun onExtractionComplete(
                    ytFiles: SparseArray<YtFile>?,
                    videoMeta: VideoMeta?
                ) {
                    ytFiles?.let {
                        val itag = 18
                        val downloadUrl = ytFiles[itag].url
                        exoPlayer?.prepare(buildMediaSource(Uri.parse(downloadUrl)), false, true)
                    }
                    var abc= 1
                    abc ++
                }

            }.extract(stepUrlPath,true,false)

        }

        if (stepUrlPath != null && stepUrlPath.isNotEmpty()) {
            val trackSelector: TrackSelector = DefaultTrackSelector()
            exoPlayer = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)
            exoPlayer?.prepare(buildMediaSource(Uri.parse(stepUrlPath)), false, true)
            exoPlayer?.seekTo(playerTimePosition)
            exoPlayer?.playWhenReady = true
            binding.playerView.player = exoPlayer
            R.style.AppTheme
            fullscreenButton = binding.playerView.findViewById(R.id.exo_fullscreen_icon)
            fullscreenButton?.setOnClickListener { setOnFulScreenClickListener() }

        } else {
            binding.playerView.visibility = View.GONE
        }
    }

    fun setOnFulScreenClickListener(){
        if (fullscreen) {
            fullscreenButton?.setImageDrawable(
                ContextCompat.getDrawable(
                    activity!!,
                    R.drawable.ic_fullscreen_open
                )
            )
            activity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            if (activity!!.actionBar != null) {
                activity!!.actionBar!!.show()
            }
//            activity!!.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            val params =
                playerView.layoutParams as LinearLayout.LayoutParams
//            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.width = (300 * activity!!.resources.displayMetrics.density).toInt()
            params.height =
                (200 * activity!!.resources.displayMetrics.density).toInt()
            playerView.layoutParams = params
            playerView.setBackgroundColor(defautBgColor)
            binding.root.setBackgroundColor(defautBgColor)
            fullscreen = false
        } else {
            fullscreenButton!!.setImageDrawable(
                ContextCompat.getDrawable(
                    activity!!,
                    R.drawable.ic_fullscreen_close
                )
            )
            activity!!.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            if (activity!!.actionBar != null) {
                activity!!.actionBar!!.hide()
            }
//            activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            val params =
                playerView.layoutParams as LinearLayout.LayoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.MATCH_PARENT
            playerView.layoutParams = params
            playerView.setBackgroundColor(Color.BLACK)
            binding.root.setBackgroundColor(Color.BLACK)
            fullscreen = true
        }
    }

    private fun buildMediaSource(uri: Uri): ExtractorMediaSource? {

        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("StarWarsApp")
//            DefaultDataSourceFactory(activity, "StarWarsApp")
        )//.setExtractorsFactory()
            .createMediaSource(uri)
    }

    private fun releasePlayer() {
        exoPlayer?.let {
            playerTimePosition = it.currentPosition
            it.stop()
            it.release()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        exoPlayer?.let {
            outState.putLong(
                EXTRA_PLAYER_TIME_POSITION,
                it.currentPosition
            )
        }
    }


}
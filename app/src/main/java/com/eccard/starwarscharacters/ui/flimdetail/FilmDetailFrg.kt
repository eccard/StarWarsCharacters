package com.eccard.starwarscharacters.ui.flimdetail

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eccard.starwarscharacters.AppExecutors
import com.eccard.starwarscharacters.R
import com.eccard.starwarscharacters.data.model.Film
import com.eccard.starwarscharacters.databinding.FilmDetailFrgBinding
import com.eccard.starwarscharacters.di.Injectable
import com.eccard.starwarscharacters.ui.MainActivity
import com.eccard.starwarscharacters.ui.common.SimpleDividerItemDecoration
import com.eccard.starwarscharacters.ui.home.CharacterAdapter
import com.eccard.starwarscharacters.util.autoCleared
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import timber.log.Timber
import javax.inject.Inject


class FilmDetailFrg: Fragment(), Injectable {
    companion object {
        private const val EXTRA_PLAYER_TIME_POSITION = "player-position-time"
        private const val EXTRA_PLAYER_FULL_SCREEN = "player-full-screen"
        const val FILM_KEY = "FILME_KEY"
        fun newInstance ( film : Film) : FilmDetailFrg {
            val frg = FilmDetailFrg()
            val args = Bundle()
            args.putParcelable(FILM_KEY,film)
            frg.arguments = args
            return frg
        }
    }

    @Inject
    lateinit var viewModelFactory : ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private var adapter by autoCleared<CharacterAdapter>()

    private val viewModel: FilmDetailViewModel by viewModels {
        viewModelFactory
    }

    var fullscreen = false
    private var exoPlayer : SimpleExoPlayer? = null
    var binding by autoCleared<FilmDetailFrgBinding>()
    private var playerTimePosition: Long = 0
    private var film : Film? = null
    private var fullscreenButton : ImageView? = null
    private var mFullScreenDialog : Dialog? = null
    private var playerView : PlayerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.film_detail_frg,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        val typedValue =  TypedValue()

        savedInstanceState?.let {
            playerTimePosition = savedInstanceState.getLong(
                EXTRA_PLAYER_TIME_POSITION,
                0L
            )
            fullscreen = savedInstanceState.getBoolean(EXTRA_PLAYER_FULL_SCREEN)
        }



        arguments?.let { args ->
            film = args.getParcelable<Film>(FILM_KEY)

            film?.let {
                viewModel.setCharactersIds(it.charactersIds?.map { character -> character._value })
                viewModel.setYouTubeUrl(it.trailer)
                binding.film = film
            }


        }

        viewModel.videoUrl.observe(viewLifecycleOwner, Observer {videoLink ->
            Timber.d("event url=$videoLink")
            exoPlayer?.prepare(
                buildMediaSource(Uri.parse(videoLink)),
                false,
                true
            )
        })

        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            setUpPlayer()
        }

    }

    private fun initRecyclerView() {

        val rvAdapter = CharacterAdapter(appExecutors = appExecutors){
                character -> (activity as MainActivity).navigateToDetailsFrg(character.charactter)
        }

        binding.filmDetails.rvCharactersInFilm
        binding.filmDetails.rvCharactersInFilm.adapter = rvAdapter
        adapter = rvAdapter

        binding.filmDetails.rvCharactersInFilm.addItemDecoration(SimpleDividerItemDecoration(binding.filmDetails.rvCharactersInFilm.context))
        viewModel.characterInFilm.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })
    }
    private fun initFullscreenDialog() {
        mFullScreenDialog =
            object : Dialog(activity!!, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
                override fun onBackPressed() {
                    if (fullscreen) closeFullscreenDialog()
                    super.onBackPressed()
                }
            }
    }
    override fun onResume() {
        super.onResume()

        if ((Util.SDK_INT <= 23)) {
            setUpPlayer()
        }

        if ( playerView == null ){
            playerView = binding.playerView
        }

        if (fullscreen) {
            playerView?.let {

                (it.parent as ViewGroup).removeView(it)
                mFullScreenDialog!!.addContentView(
                    it,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
                mFullScreenDialog!!.show()
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

    private fun openFullscreenDialog() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        playerView?.let{
            (it.parent as ViewGroup).removeView(it)
            mFullScreenDialog!!.addContentView(
                it,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        }

        fullscreenButton?.setImageDrawable(
            ContextCompat.getDrawable(
                activity!!,
                R.drawable.ic_fullscreen_close
            )
        )
        fullscreen = true
        mFullScreenDialog?.show()
    }

    private fun closeFullscreenDialog() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        playerView?.let{
            (it.parent as ViewGroup).removeView(it)

            val params =
                it.layoutParams as FrameLayout.LayoutParams
            params.width = (300 * activity!!.resources.displayMetrics.density).toInt()
            params.height = (200 * activity!!.resources.displayMetrics.density).toInt()
            it.layoutParams = params

            binding.mainMediaFrame.addView(it)
            fullscreen = false
            mFullScreenDialog!!.dismiss()
        }

        fullscreenButton?.setImageDrawable(
            ContextCompat.getDrawable(
                activity!!,
                R.drawable.ic_fullscreen_open
            )
        )
    }

    private fun setUpPlayer() {
        if (exoPlayer == null) {
            val trackSelector: TrackSelector = DefaultTrackSelector()
            exoPlayer = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)
            exoPlayer?.seekTo(playerTimePosition)
            exoPlayer?.playWhenReady = true
            binding.playerView.player = exoPlayer
            binding.playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            binding.playerView.setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS)
            fullscreenButton = binding.playerView.findViewById(R.id.exo_fullscreen_icon)
            fullscreenButton?.setOnClickListener { setOnFulScreenClickListener() }
            initFullscreenDialog()
        }
    }

    fun setOnFulScreenClickListener(){
        if (fullscreen) {
            closeFullscreenDialog()
        } else {
            openFullscreenDialog()
        }
    }

    private fun buildMediaSource(uri: Uri): ExtractorMediaSource? {

        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("StarWarsApp")
        ).createMediaSource(uri)
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
            outState.putBoolean(EXTRA_PLAYER_FULL_SCREEN,fullscreen)
        }
        super.onSaveInstanceState(outState)
    }


}
package com.eccard.starwarscharacters.ui.flimdetail

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.film_detail_frg.*
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

    private var fullscreen = false
    private var initializedPlayer = false
    private var binding by autoCleared<FilmDetailFrgBinding>()
    private var playerTimePosition: Float = 0F
    private var film : Film? = null
    private var fullscreenButton : ImageView? = null
    private var mFullScreenDialog : Dialog? = null

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

        savedInstanceState?.let {
            playerTimePosition = savedInstanceState.getFloat(EXTRA_PLAYER_TIME_POSITION)
            fullscreen = savedInstanceState.getBoolean(EXTRA_PLAYER_FULL_SCREEN)
        }



        arguments?.let { args ->
            film = args.getParcelable<Film>(FILM_KEY)

            film?.let {
                viewModel.setCharactersIds(it.charactersIds?.map { character -> character._value })
                binding.film = film
            }
        }

        initRecyclerView()
        setUpPlayer()
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
                fullscreenButton?.setImageResource(R.drawable.ayp_ic_fullscreen_exit_24dp)
            }
        }

    }


    private fun openFullscreenDialog() {


        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
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
            mFullScreenDialog?.show()
            fullscreenButton?.setImageResource(R.drawable.ayp_ic_fullscreen_exit_24dp)
        } else {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        fullscreen = true
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

        fullscreenButton?.setImageResource(R.drawable.ayp_ic_fullscreen_24dp)
    }

    private fun setUpPlayer() {
        if (!initializedPlayer) {

            val view  = binding.playerView as YouTubePlayerView
            viewLifecycleOwner.lifecycle.addObserver(view)

            film?.trailer?.let {
                val youTubeId = it.substring(it.indexOf("=") +1)
                Timber.d("youTubeId youTubePlayer=$youTubeId")

                view.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {
                        Timber.d("onApiChange youTubePlayer=$youTubePlayer")
                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
//                        Timber.d("onCurrentSecond second=$second")
                        playerTimePosition = second
                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {
                        Timber.d("onError error=$error")
                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {
                        Timber.d("onPlaybackQualityChange playbackQuality=$playbackQuality")
                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        Timber.d("onPlaybackQualityChange onReady playerTimePosition=$playerTimePosition")
                        youTubePlayer.loadVideo(youTubeId,playerTimePosition)
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                        Timber.d("onStateChange state=$state")
                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {
                    }

                    override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {
                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {
                    }
                })
            }

            binding.playerView.getPlayerUiController().setFullScreenButtonClickListener ( View.OnClickListener {
                setOnFulScreenClickListener()
            })

            fullscreenButton = binding.playerView.findViewById(R.id.fullscreen_button)
            initFullscreenDialog()
            initializedPlayer = true
        }
    }

    fun setOnFulScreenClickListener(){
        if (fullscreen) {
            closeFullscreenDialog()
        } else {
            openFullscreenDialog()
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        playerView?.let {
            outState.putFloat(
                EXTRA_PLAYER_TIME_POSITION,
                playerTimePosition
            )
            outState.putBoolean(EXTRA_PLAYER_FULL_SCREEN,fullscreen)
        }
        super.onSaveInstanceState(outState)
    }
}
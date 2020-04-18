package com.eccard.starwarscharacters.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton
import com.eccard.starwarscharacters.BuildConfig
import com.eccard.starwarscharacters.data.api.MockInterceptor
import com.eccard.starwarscharacters.data.api.StarWarsApi
import com.eccard.starwarscharacters.data.db.CharactterDao
import com.eccard.starwarscharacters.data.db.FilmDao
import com.eccard.starwarscharacters.data.db.StarWarsDb
import dagger.Module
import okhttp3.Interceptor
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    @Named("MockInterceptor")
    fun provideMockInterceptor(): Interceptor = MockInterceptor()


    @Provides
    @Singleton
    @Named("OkHttpClient")
    fun provideOkHttpClient(
        @Named("MockInterceptor") mockInterceptor: Interceptor): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(mockInterceptor)
        .build()

    @Provides
    @Singleton
    @Named("Retrofit")
    fun provideRetrofit(@Named("OkHttpClient") client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideStarWarsApi(@Named("Retrofit") retrofit: Retrofit): StarWarsApi {
        return retrofit.create(StarWarsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): StarWarsDb {
        return Room
            .databaseBuilder(app, StarWarsDb::class.java, "starWars.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: StarWarsDb): CharactterDao {
        return db.characterDao()
    }

    @Singleton
    @Provides
    fun provideFilmDao(db: StarWarsDb): FilmDao {
        return db.filmDao()
    }

//    @Provides
//    @Singleton
//    fun provideRepository(starWarsApi: StarWarsApi): Repository {
//        return Repository(AppExecutors(),starWarsApi)
//    }

}
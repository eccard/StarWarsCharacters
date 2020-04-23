package com.eccard.starwarscharacters.di

import android.app.Application
import android.content.Context
import com.eccard.starwarscharacters.BuildConfig
import com.eccard.starwarscharacters.data.api.MockInterceptor
import com.eccard.starwarscharacters.data.api.StarWarsApi
import com.eccard.starwarscharacters.data.db.CharactterDao
import com.eccard.starwarscharacters.data.db.FilmDao
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

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

//    @Singleton
//    @Provides
//    fun provideDb(app: Application): StarWarsDb {
//        return Room
//            .databaseBuilder(app, StarWarsDb::class.java, "starWars.db")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
    @Singleton
    @Provides
//    fun provideUserDao(realm : Realm): CharactterDao {
    fun provideUserDao(): CharactterDao {
//        return CharactterDao(realm)
        return CharactterDao()
    }

    @Singleton
    @Provides
//    fun provideFilmDao(realm : Realm): FilmDao {
    fun provideFilmDao(): FilmDao {
//        return FilmDao(realm)
        return FilmDao()
    }

//    @Provides
//    fun providesRealm(appExecutors: AppExecutors, context: Context) : Realm {
//        Realm.init(context)
//        return Realm.getDefaultInstance()
//    }

//    @Provides
//    @Singleton
//    fun provideRepository(starWarsApi: StarWarsApi): Repository {
//        return Repository(AppExecutors(),starWarsApi)
//    }

}
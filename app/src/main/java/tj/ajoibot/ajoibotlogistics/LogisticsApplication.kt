package tj.ajoibot.ajoibotlogistics

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import tj.ajoibot.ajoibotlogistics.data.network.RemoteDataSource
import tj.ajoibot.ajoibotlogistics.data.network.interceptors.RequestTokenInterceptor
import tj.ajoibot.ajoibotlogistics.data.repositories.AuthRepository
import tj.ajoibot.ajoibotlogistics.data.repositories.TripsRepository
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRequestTokenInterceptor
import tj.ajoibot.ajoibotlogistics.internal.interfaces.ITripsRepository
import tj.ajoibot.ajoibotlogistics.internal.utils.SharedSettings
import tj.ajoibot.ajoibotlogistics.services.LogisticsService
import tj.ajoibot.ajoibotlogistics.ui.login.LoginViewModelFactory
import tj.ajoibot.ajoibotlogistics.ui.main.MainViewModelFactory

class LogisticsApplication : Application(), KodeinAware, ViewModelStoreOwner {

    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@LogisticsApplication))

        bind() from singleton { SharedSettings(instance()) }

        bind<IRequestTokenInterceptor>() with singleton { RequestTokenInterceptor(instance()) }

        bind() from singleton { LogisticsService(instance()) }

        //Data Sources
        bind<IRemoteDataSource>() with singleton { RemoteDataSource(instance()) }

        //Repositories
        bind() from singleton { AuthRepository(instance()) }
        bind<ITripsRepository>() with singleton { TripsRepository(instance()) }

        bind() from provider { LoginViewModelFactory(instance()) }

        bind() from provider { MainViewModelFactory(instance(), instance()) }


    }
}
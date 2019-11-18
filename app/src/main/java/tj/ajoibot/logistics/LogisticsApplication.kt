package tj.ajoibot.logistics

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
import tj.ajoibot.logistics.data.network.RemoteDataSource
import tj.ajoibot.logistics.data.network.interceptors.RequestTokenInterceptor
import tj.ajoibot.logistics.data.repositories.AuthRepository
import tj.ajoibot.logistics.data.repositories.StoredItemsRepository
import tj.ajoibot.logistics.data.repositories.TripsRepository
import tj.ajoibot.logistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.logistics.internal.interfaces.IRequestTokenInterceptor
import tj.ajoibot.logistics.internal.interfaces.IStoredItemsRepository
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository
import tj.ajoibot.logistics.internal.utils.SharedSettings
import tj.ajoibot.logistics.services.LogisticsService
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModelFactory
import tj.ajoibot.logistics.ui.item.StoredItemViewModelFactory
import tj.ajoibot.logistics.ui.login.LoginViewModelFactory
import tj.ajoibot.logistics.ui.main.MainViewModelFactory
import tj.ajoibot.logistics.ui.trips.TripsViewModelFactory

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
        bind<IStoredItemsRepository>() with singleton { StoredItemsRepository(instance()) }

        //View models factories
        bind() from provider { LoginViewModelFactory(instance()) }
        bind() from provider { MainViewModelFactory(instance(), instance()) }
        bind() from provider { BarCodeViewModelFactory() }
        bind() from provider { StoredItemViewModelFactory(instance(), this@LogisticsApplication) }
        bind() from provider { TripsViewModelFactory(instance(), this@LogisticsApplication) }


    }
}
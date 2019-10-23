package tj.ajoibot.ajoibotlogistics

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import tj.ajoibot.ajoibotlogistics.data.network.RemoteDataSource
import tj.ajoibot.ajoibotlogistics.data.repositories.AuthRepository
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.ajoibotlogistics.services.LogisticsService
import tj.ajoibot.ajoibotlogistics.ui.login.LoginViewModelFactory

class LogisticsApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@LogisticsApplication))

        bind() from singleton { LogisticsService() }

        bind<IRemoteDataSource>() with singleton { RemoteDataSource(instance()) }

        bind() from singleton { AuthRepository(instance()) }

        bind() from provider { LoginViewModelFactory(instance()) }
    }
}
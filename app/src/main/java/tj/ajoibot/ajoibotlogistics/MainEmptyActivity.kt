package tj.ajoibot.ajoibotlogistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.ajoibotlogistics.internal.utils.SharedSettings

class MainEmptyActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val settings: SharedSettings by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_empty)

        val activityIntent: Intent

        if (settings.getToken().isNullOrBlank()) {
            activityIntent = Intent(this, LoginActivity::class.java)
        } else {
            activityIntent = Intent(this, MainActivity::class.java)
        }

        startActivity(activityIntent)
        finish()
    }
}

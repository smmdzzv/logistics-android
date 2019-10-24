package tj.ajoibot.ajoibotlogistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

class MainEmptyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_empty)

        val activityIntent: Intent

        if (true) {
            activityIntent = Intent(this, MainActivity::class.java)
        } else {
            activityIntent = Intent(this, LoginActivity::class.java)
        }

        startActivity(activityIntent)
        finish()
    }
}

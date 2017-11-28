package mobi.lucasborges.mrbeer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import mobi.lucasborges.mrbeer.features.onboarding.OnBoardingActivity
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(intentFor<OnBoardingActivity>().newTask().clearTask())
    }
}

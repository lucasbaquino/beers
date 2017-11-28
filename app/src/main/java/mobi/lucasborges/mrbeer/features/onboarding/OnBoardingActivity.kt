package mobi.lucasborges.mrbeer.features.onboarding

import android.graphics.Typeface
import android.os.Bundle
import com.codemybrainsout.onboarder.AhoyOnboarderActivity
import com.codemybrainsout.onboarder.AhoyOnboarderCard
import mobi.lucasborges.mrbeer.R
import mobi.lucasborges.mrbeer.features.beerstyles.BeerStylesActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask

/**
 * Created by Lucas Borges on 25/11/2017.
 */
class OnBoardingActivity : AhoyOnboarderActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val boardCollection = AhoyOnboarderCard(R.string.onboarding_collection, R.string.onboarding_collection_explanation, R.drawable.first_boarding_illustration)
        val boardDetail = AhoyOnboarderCard(R.string.onboarding_detail, R.string.onboarding_detail_exaplanation, R.drawable.second_boarding_illustration)
        val boardShare = AhoyOnboarderCard(R.string.onboarding_share, R.string.onboarding_detail_share, R.drawable.third_boarding_illustration)

        val pages = mutableListOf<AhoyOnboarderCard>(boardCollection, boardDetail, boardShare)

        pages.forEach { page ->
            page.backgroundColor = R.color.yellow_beer
            page.descriptionColor = R.color.grey_onboarding
            page.titleColor = R.color.grey_onboarding
            page.titleTextSize = dpToPixels(8, this)
            page.descriptionTextSize = dpToPixels(6, this)
            page.setIconLayoutParams(480, 720, -50, 0, 0, 50)
        }

        setInactiveIndicatorColor(android.R.color.darker_gray)
        setActiveIndicatorColor(R.color.white)

        setFinishButtonTitle(R.string.ok_lets_start)
        showNavigationControls(true)

        setColorBackground(mutableListOf(R.color.yellow_beer))

        val face = Typeface.createFromAsset(assets, "fonts/roboto_light.ttf")
        setFont(face)

        setOnboardPages(pages)
    }

    override fun onFinishButtonPressed() {
        startActivity(intentFor<BeerStylesActivity>().newTask().clearTask())
    }

}
package com.rickyslash.indohero

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.rickyslash.indohero.databinding.ActivityHeroDetailsBinding

class HeroDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroDetailsBinding

    companion object {
        const val EXTRA_HERO = "extra_hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = getHeroParcel(intent)

        if (hero != null) {
            binding.detailsName.text = hero.name
            binding.detailsDesc.text = hero.desc
            Glide.with(this)
                .load(hero.photo)
                .into(binding.detailsImg)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val hero = getHeroParcel(intent)

        when (item.itemId) {
            R.id.action_share -> {
                if (hero != null) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.setType("text/plain")

                    val descSend = "Article – ${hero.name}\n\n${hero.desc}"

                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, hero.name)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, descSend)
                    startActivity(Intent.createChooser(shareIntent, "Share the Hero"))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

private fun getHeroParcel(intent: Intent): Hero? {
    return if (Build.VERSION.SDK_INT >= 33) {
        intent.getParcelableExtra(HeroDetailsActivity.EXTRA_HERO, Hero::class.java)
    } else {
        @Suppress("DEPRECATION")
        intent.getParcelableExtra(HeroDetailsActivity.EXTRA_HERO)
    }
}
package com.rickyslash.indohero

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val hero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_HERO, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_HERO)
        }

        if (hero != null) {
            binding.detailsName.text = hero.name
            binding.detailsDesc.text = hero.desc
            Glide.with(this)
                .load(hero.photo)
                .into(binding.detailsImg)
        }

    }
}
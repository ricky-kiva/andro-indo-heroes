package com.rickyslash.indohero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyslash.indohero.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val heroList = ArrayList<Hero>()

    private fun showSelectedHero(hero: Hero) {
        val moveHeroDetailsIntent = Intent(this@MainActivity, HeroDetailsActivity::class.java)

        moveHeroDetailsIntent.putExtra(HeroDetailsActivity.EXTRA_HERO, Hero(hero.name, hero.desc, hero.photo, hero.birthdate, hero.birthplace))
        startActivity(moveHeroDetailsIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvHeroes.setHasFixedSize(true)
        heroList.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val moveAboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveAboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataBirthdate = resources.getStringArray(R.array.data_birthdate)
        val dataBirthplace = resources.getStringArray(R.array.data_birthplace)
        val listHero = ArrayList<Hero>()

        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDesc[i], dataPhoto[i], dataBirthdate[i], dataBirthplace[i])
            listHero.add(hero)
        }

        return listHero
    }

    private fun showRecyclerList() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)

        val listHeroAdapter = ListHeroAdapter(heroList)
        binding.rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }
}
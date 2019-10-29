package com.inavi.maps.androiddemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.inavi.mapsdk.maps.OnMapReadyCallback

abstract class InvBaseActivity : AppCompatActivity(), OnMapReadyCallback {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    supportActionBar?.let {
      it.setDisplayHomeAsUpEnabled(true)
      it.setDisplayShowHomeEnabled(true)
    }
  }

  override fun onOptionsItemSelected(item: MenuItem) = when {
    item.itemId == android.R.id.home -> {
      finish()
      true
    }

    else -> super.onOptionsItemSelected(item)
  }
}
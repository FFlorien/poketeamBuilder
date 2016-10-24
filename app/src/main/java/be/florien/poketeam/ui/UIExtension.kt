package be.florien.poketeam.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by FlamentF on 24-10-16.
 */


fun  ViewGroup.inflateBinding(layoutRes : Int, attach : Boolean = false) : ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, attach)
}
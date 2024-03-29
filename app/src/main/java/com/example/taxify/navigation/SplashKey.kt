package com.example.taxify.navigation

import androidx.fragment.app.Fragment
import com.example.taxify.dashboards.driver.DriverDashboardFragment
import com.example.taxify.splashscreen.SplashFragment
import com.example.taxify.splashscreen.SplashViewModel
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.lookup
import kotlinx.parcelize.Parcelize


/**
 * https://github.com/Zhuinden/simple-stack
 */
@Parcelize
data class SplashKey(private val noArgsPlaceholder: String = "") : DefaultFragmentKey(),
    DefaultServiceProvider.HasServices {
    override fun instantiateFragment(): Fragment = SplashFragment()

    //should be unique
    override fun getScopeTag(): String = toString()

    //How to create a scoped service
    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            add(SplashViewModel(backstack, lookup()))
        }
    }
}
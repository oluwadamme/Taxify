package com.example.taxify.navigation

import androidx.fragment.app.Fragment
import com.example.taxify.dashboards.driver.DriverDashboardFragment
import com.example.taxify.dashboards.driver.DriverDashboardViewModel
import com.example.taxify.dashboards.passenger.PassengerDashboardFragment
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider
import com.zhuinden.simplestackextensions.servicesktx.add
import com.zhuinden.simplestackextensions.servicesktx.lookup
import kotlinx.parcelize.Parcelize

@Parcelize
data class DriverDashboardKey(private val noArgsPlaceholder: String = ""): DefaultFragmentKey(),
    DefaultServiceProvider.HasServices {
    override fun instantiateFragment(): Fragment = DriverDashboardFragment()

    override fun getScopeTag(): String = toString()

    //How to create a scoped service
    override fun bindServices(serviceBinder: ServiceBinder) {
        with(serviceBinder) {
            add(DriverDashboardViewModel(backstack, lookup(), lookup()))
        }
    }
}
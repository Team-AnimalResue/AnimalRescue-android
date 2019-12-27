package com.team.animalrescue.utils

class AndroidNavigator {

    enum class HomeScreenTabIndex constructor(private val tabIndex: Int) {
        HOME(0),
        ADOPTION(1),
        DONATION(2),
        MY_ACCOUNT(3);

        fun tabIndex(): Int {
            return tabIndex
        }
    }
}

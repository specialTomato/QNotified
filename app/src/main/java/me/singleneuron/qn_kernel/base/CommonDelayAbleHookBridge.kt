/*
 * QNotified - An Xposed module for QQ/TIM
 * Copyright (C) 2019-2021 dmca@ioctl.cc
 * https://github.com/ferredoxin/QNotified
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version and our eula as published
 * by ferredoxin.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * and eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 * <https://github.com/ferredoxin/QNotified/blob/master/LICENSE.md>.
 */

package me.singleneuron.qn_kernel.base

import me.singleneuron.qn_kernel.ui.base.UiItem
import me.singleneuron.qn_kernel.ui.base.UiSwitchPreference
import nil.nadph.qnotified.hook.CommonDelayableHook

abstract class CommonDelayAbleHookBridge(keyName: String): CommonDelayableHook(keyName), UiItem {

    fun uiSwitchPreference(init: UiSwitchPreferenceItemFactory.()->Unit): UiSwitchPreference {
        val uiSwitchPreferenceFactory = UiSwitchPreferenceItemFactory()
        uiSwitchPreferenceFactory.init()
        return uiSwitchPreferenceFactory
    }

    open inner class UiSwitchPreferenceItemFactory: UiSwitchPreference {
        override lateinit var title: String
        override var summary: String? = null
        override var getValue: () -> Boolean? = { isEnabled }
        override var onPreferenceChangeListener: (Boolean) -> Boolean = {
            isEnabled = it
            true
        }
        override var onClickListener: () -> Boolean = {true}
        override var valid: Boolean = isValid
    }

}

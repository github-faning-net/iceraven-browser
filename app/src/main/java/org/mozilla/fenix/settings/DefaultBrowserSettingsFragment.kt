/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.settings

import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.provider.Settings.ACTION_MANAGE_DEFAULT_APPS_SETTINGS
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import org.mozilla.fenix.BrowserDirection
import org.mozilla.fenix.HomeActivity
import org.mozilla.fenix.R
import org.mozilla.fenix.ext.getPreferenceKey
import org.mozilla.fenix.ext.showToolbar

/**
 * Lets the user control their default browser preferences
 */
class DefaultBrowserSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val makeDefaultBrowserKey = getPreferenceKey(R.string.pref_key_make_default_browser)
        val preferenceMakeDefaultBrowser = findPreference<Preference>(makeDefaultBrowserKey)

        preferenceMakeDefaultBrowser?.onPreferenceClickListener =
            getClickListenerForMakeDefaultBrowser()
    }

    override fun onResume() {
        super.onResume()
        showToolbar(getString(R.string.preferences_set_as_default_browser))

        updatePreferences()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.default_browser_preferences, rootKey)
        updatePreferences()
    }

    private fun updatePreferences() {
        findPreference<DefaultBrowserPreference>(getPreferenceKey(R.string.pref_key_make_default_browser))
            ?.updateSwitch()
    }

    private fun getClickListenerForMakeDefaultBrowser(): Preference.OnPreferenceClickListener {
        return if (SDK_INT >= Build.VERSION_CODES.N) {
            Preference.OnPreferenceClickListener {
                val intent = Intent(ACTION_MANAGE_DEFAULT_APPS_SETTINGS)
                startActivity(intent)
                true
            }
        } else {
            Preference.OnPreferenceClickListener {
                (activity as HomeActivity).openToBrowserAndLoad(
                    searchTermOrURL = SupportUtils.getSumoURLForTopic(
                        requireContext(),
                        SupportUtils.SumoTopic.SET_AS_DEFAULT_BROWSER
                    ),
                    newTab = true,
                    from = BrowserDirection.FromDefaultBrowserSettingsFragment
                )
                true
            }
        }
    }
}

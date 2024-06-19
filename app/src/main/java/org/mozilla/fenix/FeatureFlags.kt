/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix

import android.content.Context
import mozilla.components.support.locale.LocaleManager
import mozilla.components.support.locale.LocaleManager.getSystemDefault

/**
 * A single source for setting feature flags that are mostly based on build type.
 */
object FeatureFlags {

    /**
     * Enables custom extension collection feature,
     * This feature does not only depend on this flag. It requires the AMO collection override to
     * be enabled which is behind the Secret Settings.
     * */
    val customExtensionCollectionFeature = Config.channel.isNightlyOrDebug || Config.channel.isBeta

    /**
     * Pull-to-refresh allows you to pull the web content down far enough to have the page to
     * reload.
     */
    const val pullToRefreshEnabled = true

    /**
     * Enables the Sync Addresses feature.
     */
    const val syncAddressesFeature = false

    /**
     * Show Pocket recommended stories on home.
     */
    fun isPocketRecommendationsFeatureEnabled(context: Context): Boolean {
        val langTag = LocaleManager.getCurrentLocale(context)
            ?.toLanguageTag() ?: getSystemDefault().toLanguageTag()
        return listOf("en-US", "en-CA").contains(langTag)
    }

    /**
     * Show Pocket sponsored stories in between Pocket recommended stories on home.
     */
    fun isPocketSponsoredStoriesFeatureEnabled(context: Context): Boolean {
        return isPocketRecommendationsFeatureEnabled(context)
    }

    /**
     * Enables compose on the tabs tray items.
     */
    const val composeTabsTray = true

    /**
     * Enables compose on the top sites.
     */
    const val composeTopSites = false

    /**
     * Enables new search settings UI with two extra fragments, for managing the default engine
     * and managing search shortcuts in the quick search menu.
     */
    const val unifiedSearchSettings = true

    /**
     * Allows users to enable Firefox Suggest.
     */
    const val fxSuggest = true

    /**
     * Allows users to enable SuggestStrongPassword feature.
     */
    const val suggestStrongPassword = true

    /**
     * Enable Meta attribution.
     */
    const val metaAttributionEnabled = true

    /**
     * Enables Navigation Toolbar.
     */
    val navigationToolbarEnabled = Config.channel.isNightlyOrDebug

    /**
     * Enables the menu redesign.
     */
    const val menuRedesignEnabled = false

    /**
     * Enables microsurveys.
     */
    val microsurveysEnabled = Config.channel.isDebug

    /**
     * Enables the Compose Homepage.
     */
    const val composeHomepage = false

    /**
     * Enables Homepage as a New Tab.
     */
    const val homepageAsNewTab = false
}

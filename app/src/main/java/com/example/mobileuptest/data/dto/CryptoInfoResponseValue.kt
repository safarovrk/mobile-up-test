package com.example.mobileuptest.data.dto

import com.google.gson.annotations.SerializedName

data class CryptoInfoResponseValue(
    val additional_notices: List<Any>,
    val asset_platform_id: Any,
    val block_time_in_minutes: Int,
    val categories: List<String>,
    val coingecko_rank: Int,
    val coingecko_score: Double,
    val community_score: Double,
    val country_origin: String,
    val description: Description,
    val detail_platforms: DetailPlatforms,
    val developer_score: Double,
    val genesis_date: String,
    val hashing_algorithm: String,
    val id: String,
    val image: Image,
    val last_updated: String,
    val links: Links,
    val liquidity_score: Double,
    val market_cap_rank: Int,
    val name: String,
    val platforms: Platforms,
    val public_interest_score: Double,
    val public_interest_stats: PublicInterestStats,
    val public_notice: Any,
    val sentiment_votes_down_percentage: Double,
    val sentiment_votes_up_percentage: Double,
    val status_updates: List<Any>,
    val symbol: String
) {
    data class Description(
        val en: String
    )

    data class DetailPlatforms(
        @SerializedName("")
        val x: X
    ) {
        data class X(
            val contract_address: String,
            val decimal_place: Any
        )
    }

    data class Image(
        val large: String,
        val small: String,
        val thumb: String
    )

    data class Links(
        val announcement_url: List<String>,
        val bitcointalk_thread_identifier: Any,
        val blockchain_site: List<String>,
        val chat_url: List<String>,
        val facebook_username: String,
        val homepage: List<String>,
        val official_forum_url: List<String>,
        val repos_url: ReposUrl,
        val subreddit_url: String,
        val telegram_channel_identifier: String,
        val twitter_screen_name: String
    ) {
        data class ReposUrl(
            val bitbucket: List<Any>,
            val github: List<String>
        )
    }

    data class Platforms(
        @SerializedName("")
        val string: String
    )

    data class PublicInterestStats(
        val alexa_rank: Int,
        val bing_matches: Any
    )
}
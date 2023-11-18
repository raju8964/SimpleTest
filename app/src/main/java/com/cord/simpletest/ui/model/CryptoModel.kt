package com.cord.simpletest.ui.model


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val usd: USD?
)


data class Status(
    @SerializedName("error_message")
    val errorMessage: String? = null,
    @SerializedName("elapsed")
    val elapsed: Int = 0,
    @SerializedName("total_count")
    val totalCount: Int = 0,
    @SerializedName("credit_count")
    val creditCount: Int = 0,
    @SerializedName("error_code")
    val errorCode: Int = 0,
    @SerializedName("timestamp")
    val timestamp: String = "",
    @SerializedName("notice")
    val notice: String? = null
)


data class DataItem(
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("circulating_supply")
    val circulatingSupply: Double = 0.0,
    @SerializedName("last_updated")
    val lastUpdated: String = "",
    @SerializedName("total_supply")
    val totalSupply: Double = 0.0,
    @SerializedName("tvl_ratio")
    val tvlRatio: String? = null,
    @SerializedName("cmc_rank")
    val cmcRank: Long = 0,
    @SerializedName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: String? = null,
    @SerializedName("platform")
    val platform: Any? = null,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("date_added")
    val dateAdded: String = "",
    @SerializedName("quote")
    val quote: Quote,
    @SerializedName("num_market_pairs")
    val numMarketPairs: Long = 0,
    @SerializedName("infinite_supply")
    val infiniteSupply: Boolean = false,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("max_supply")
    val maxSupply: Long = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("self_reported_market_cap")
    val selfReportedMarketCap: String? = null,
    @SerializedName("slug")
    val slug: String = ""
)


data class USD(
    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double = 0.0,
    @SerializedName("last_updated")
    val lastUpdated: String = "",
    @SerializedName("market_cap_dominance")
    val marketCapDominance: Double = 0.0,
    @SerializedName("tvl")
    val tvl: String? = null,
    @SerializedName("percent_change_30d")
    val percentChangeD: Double = 0.0,
    @SerializedName("market_cap")
    val marketCap: Double = 0.0,
    @SerializedName("volume_change_24h")
    val volumeChangeH: Double = 0.0,
    @SerializedName("price")
    val price: Double = 0.0,
    @SerializedName("volume_24h")
    val volumeH: Double = 0.0
)


data class CryptoResponse(
    @SerializedName("data")
    val data: List<DataItem>?,
    @SerializedName("status")
    val status: Status
)



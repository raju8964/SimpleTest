package com.cord.simpletest.ui.model

import com.google.gson.annotations.SerializedName

//data class Status(
//    @SerializedName("timestamp") val timestamp: String,
//    @SerializedName("error_code") val errorCode: Int,
//    @SerializedName("error_message") val errorMessage: String?,
//    @SerializedName("elapsed") val elapsed: Int,
//    @SerializedName("credit_count") val creditCount: Int,
//    @SerializedName("notice") val notice: String?
//)

data class Platform(
    @SerializedName("name") val name: String,
    @SerializedName("coin") val coin: Any
)

data class ContractAddress(
    @SerializedName("contract_address") val contractAddress: String,
    @SerializedName("platform") val platform: Platform
)

data class Data(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("subreddit") val subreddit: String,
    @SerializedName("notice") val notice: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("tag-names") val tagNames: List<String>,
    @SerializedName("tag-groups") val tagGroups: List<String>,
    @SerializedName("urls") val urls: Map<String, List<String>>,
    @SerializedName("platform") val platform: Any?,
    @SerializedName("date_added") val dateAdded: String,
    @SerializedName("twitter_username") val twitterUsername: String,
    @SerializedName("is_hidden") val isHidden: Int,
    @SerializedName("date_launched") val dateLaunched: Any?,
    @SerializedName("contract_address") val contractAddresses: List<ContractAddress>,
    @SerializedName("self_reported_circulating_supply") val selfReportedCirculatingSupply: Any?,
    @SerializedName("self_reported_tags") val selfReportedTags: Any?,
    @SerializedName("self_reported_market_cap") val selfReportedMarketCap: Any?,
    @SerializedName("infinite_supply") val infiniteSupply: Boolean
)

data class CryptoImageResponse(
    @SerializedName("status") val status: Status,
    @SerializedName("data") val data: Map<String, Data>
)
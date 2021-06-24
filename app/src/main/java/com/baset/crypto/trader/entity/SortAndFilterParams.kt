package com.baset.crypto.trader.entity

import android.os.Parcelable
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SortAndFilterParams(
    val sortType: CryptocurrencySortType,
    val sortDirection: SortDirection,
    val cryptoType: CryptocurrencyFilterType,
    val tagType: TagFilterType
) : Parcelable

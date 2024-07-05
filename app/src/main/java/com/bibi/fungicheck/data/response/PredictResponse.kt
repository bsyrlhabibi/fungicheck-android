package com.bibi.fungicheck.data.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("message") val message: String,
    @SerializedName("nutrisi") val nutrisi: NutritionData,
    @SerializedName("prediksi") val prediksi: Int,
    @SerializedName("tingkat_kepercayaan") val tingkatKepercayaan: Double,
    @SerializedName("class_names") val classNames: List<String>
)


data class NutritionData(
    val Kalori: Int?,
    val Karbohidrat: String?,
    val Lemak: String?,
    val Protein: String?,
    val Tautan: String?
)
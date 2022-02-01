package com.vs.notino.models

data class Attributes(
    val Action: Boolean?,
    val AirTransportDisallowed: Boolean?,
    val DifferentPackaging: Boolean?,
    val FreeDelivery: Boolean?,
    val Master: Boolean?,
    val New: Boolean?,
    val PackageSize: PackageSize?
)
package main

// Class 1
data class SportPants(val store: String, val material: String, val coldResistant: Boolean)

// Class 2
data class FancyTrousers(val store: String, val brand: String, val color: String)

// Composite class
data class Pants(val store: String, val material: String,
                 val coldResistant: Boolean,
                 val brand: String, val color: String)


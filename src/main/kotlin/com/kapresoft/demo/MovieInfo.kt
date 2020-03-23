package com.kapresoft.demo

class MovieInfo {

    var characterName: String? = null
    var movieName: String? = null
    var year: String? = null
    var url: String? = null

    override fun toString(): String {
        val sb = StringBuilder("MovieInfo{")
        sb.append("characterName='").append(characterName).append('\'')
        sb.append(", movieName='").append(movieName).append('\'')
        sb.append(", year='").append(year).append('\'')
        sb.append(", url='").append(url).append('\'')
        sb.append('}')
        return sb.toString()
    }

}
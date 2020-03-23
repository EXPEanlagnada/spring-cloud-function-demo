package com.kapresoft.demo

class FindMovieRequest {

    var characterName: String? = null

    override fun toString(): String {
        val sb = StringBuilder("FindMovieRequest{")
        sb.append("characterName='").append(characterName).append('\'')
        sb.append('}')
        return sb.toString()
    }

}
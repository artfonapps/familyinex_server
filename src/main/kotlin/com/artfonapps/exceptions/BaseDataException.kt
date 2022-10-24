package com.artfonapps.exceptions

sealed class BaseDataException(override val message: String) : Exception(message) {
    object NoSessionIdException: BaseDataException("Session id does not exist")
    object NoUserException: BaseDataException("Wrong user or password")
    object WrongPasswordException: BaseDataException("Wrong user or password")
    object NoTokenException: BaseDataException("Token does not exist")
}
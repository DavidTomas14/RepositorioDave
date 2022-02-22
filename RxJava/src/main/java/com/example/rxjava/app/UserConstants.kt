package com.example.rxjava.app

sealed class UserCommand{
    object ErrorDeleteUserCommand: UserCommand()
    object CompletedDeleteUserCommand: UserCommand()
    object ErrorCreadoUserCommand: UserCommand()
    object CompletedCreadoUserCommand: UserCommand()
}
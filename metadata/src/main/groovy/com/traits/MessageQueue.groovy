package com.traits

trait MessageQueue {

    abstract def bootstrap()
    abstract def postMessage(String message)
    abstract void regReceiveHandler()
}
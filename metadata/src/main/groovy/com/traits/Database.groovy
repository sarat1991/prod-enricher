package com.traits

trait Database {

    abstract void add(Map map)
    abstract def get(String productUri)
    abstract void update(def identifier, List metadata)

}
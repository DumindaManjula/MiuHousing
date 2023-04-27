package com.miu.housing.data

import java.io.Serializable

data class BuildingDetail(val id: Int, val buildingName:String, val numberOfRooms: Int, val image: Int) :
    Serializable

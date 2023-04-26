package com.miu.housing

import java.io.Serializable

data class BuildingDetail(val id: Int, val buildingName:String, val numberOfRooms: Int, val image: Int) :
    Serializable

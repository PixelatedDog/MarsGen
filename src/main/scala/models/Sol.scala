package uk.co.tomsturgeon.marsgen
package models

case class Camera(
    id: Int,
    name: String,
    rover_id: Int,
    full_name: String
)

case class Photos(
    id: Int,
    sol: Int,
    camera: Camera,
    img_src: String,
    earth_date: String
)

case class SolResponse (
    photos: Seq[Photos]
)

package authentikat.json

import java.util.{ TimeZone, Date }
import java.text.SimpleDateFormat

object SimpleJsonSerializer {

  /**
   * Simple flat json StringBuilder based serializer. Can be used w/o Json4s.
   * @param fields key value pairs
   * @return json string
   */
  def apply(fields: Seq[(String, Any)]) = {
    "{" +
      fields.map {
        case (key: String, value: Number) =>
          "\"" + key + "\":" + value
        case (key: String, value: Date) =>
          "\"" + key + "\":" + "\"" + formatDateIso8601(value) + "\""
        case (key: String, value: String) =>
          "\"" + key + "\":" + "\"" + value + "\""
        case (key: String, value: Any) =>
          "\"" + key + "\":" + "\"" + value.toString + "\""
      }.mkString(",") +
      "}"
  }

  private def formatDateIso8601(date: Date): String = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
    dateFormat.format(date)
  }

}

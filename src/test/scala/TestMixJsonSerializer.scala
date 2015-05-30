
import eu.inn.binders.dynamic._
import org.scalatest.{FlatSpec, Matchers}

case class Mixed(a: Int, b: String, extra: Value)

class TestMixJsonSerializer extends FlatSpec with Matchers {

  import eu.inn.binders.json._

  "Json " should " serialize Mixed" in {

    val t = Mixed(1, "ha", Obj(Map( // todo: find nicer way to construct
      "f" -> Number(555)
    )))
    val str = t.toJson
    assert (str === """{"a":1,"b":"ha","extra":{"f":555}}""")
  }

  "Json " should " deserialize Mixed" in {
    val o = """{"a":1,"b":"ha","extra":{"f":555}}""".parseJson[Mixed]
    val t = Mixed(1, "ha", Obj(Map(
      "f" -> Number(555)
    )))
    assert (o === t)
  }

  "Json " should " serialize Mixed (Null)" in {

    val t = Mixed(1, "ha", Null)
    val str = t.toJson
    assert (str === """{"a":1,"b":"ha","extra":null}""")
  }

  "Json " should " deserialize Mixed (Null)" in {
    val o = """{"a":1,"b":"ha"}""".parseJson[Mixed]
    val t = Mixed(1, "ha", Null)
    assert (o === t)
  }
}
#!/usr/bin/env amm

import ammonite.ops._

@main def main(s: String, i: Int): Unit = {
  (1 to i).foreach(_ => println(s))
}

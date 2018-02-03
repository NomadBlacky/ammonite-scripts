import ammonite.ops._

def rec(depth: Int, path: Path): Seq[Path] = {
  val children = ls! path
  if (depth <= 0)
    children
  else
    children ++ (children |? (_.isDir) |> (_.flatMap(dir => rec(depth - 1, dir))))
}

@main
def main(depth: Int, path: Path = pwd, file: Boolean = false, dir: Boolean = false) {
  val enableFile = (file ^ dir) && file || !(file ^ dir)
  val enableDir  = (file ^ dir) && dir  || !(file ^ dir)
  val result = for {
    p <- rec(depth, path)
    if p.isDir  || enableFile // Filter files
    if p.isFile || enableDir  // Filter directories
  } yield p
  println(result.mkString("\n"))
}

package foo.bar.inputtestdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
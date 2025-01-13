package rohegde.ktm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
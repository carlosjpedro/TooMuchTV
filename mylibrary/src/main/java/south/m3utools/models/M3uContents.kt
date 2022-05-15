package south.m3utools.models

import java.net.URL

class M3uCollection(val tvChannels: List<TvChannel>) {
    val channelCount: Int
        get() = this.tvChannels.size
}

class TvChannel(val name: String, val streamUrl: URL, val logoUrl: String = "") {
}
package south.m3utools

import net.bjoernpetersen.m3u.M3uParser
import net.bjoernpetersen.m3u.model.M3uEntry
import south.m3utools.models.*

import java.io.InputStream
import java.lang.Exception


interface IM3uCollectionParser {
    fun parse(contents: String): M3uCollection;
    fun parse(stream: InputStream): M3uCollection;
}

class M3uCollectionParser : IM3uCollectionParser {
    override fun parse(contents: String): M3uCollection {
        return parse(contents.toInputStream())
    }

    override fun parse(stream: InputStream): M3uCollection {
        return M3uParser.parse(stream.reader())
            .toM3uCollection()
    }

}

fun List<M3uEntry>.toM3uCollection(): M3uCollection {
    var channels = this.filter { it.isValid() }.map { it.toTvChannel() }
    return M3uCollection(channels)
}

fun M3uEntry.toTvChannel(): TvChannel {
    return TvChannel(this.title!!, this.location.url!!, this.metadata["tvg-logo"]!!);
}


fun M3uEntry.isValid(): Boolean {
    return this.title != null &&
            this.location != null
}

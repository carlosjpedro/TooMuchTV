package south.m3utools

import net.bjoernpetersen.m3u.M3uParser

import org.junit.Assert
import org.junit.Test
import java.net.URL

class M3uEntryToChannelSpecs {
    private val rawData = "#EXTM3U\n" +
            "#EXTINF:-1 tvg-id=\"TF1.fr\" tvg-name=\"FRN| TF1 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/tf1hd.png\" group-title=\"FRANCE HD\",FRN| TF1 HD\n" +
            "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508244"

    private val sampleEntry = M3uParser.parse(rawData).first()
    private val tvChannel = sampleEntry.toTvChannel()

    @Test
    fun tvChannel_has_expected_title() {
        Assert.assertEquals(tvChannel.name, "FRN| TF1 HD")
    }

    @Test
    fun tvChannel_has_expected_url() {
        Assert.assertEquals(
            tvChannel.streamUrl,
            URL("http://line.ult-ott.com:80/da9cf4b407/iptv1234/508244")
        )
    }

    @Test
    fun tvChannel_has_expected_logo() {
        Assert.assertEquals(
            tvChannel.logoUrl,
            "http://logo.protv.cc/picons/logos/tf1hd.png"
        )
    }
}
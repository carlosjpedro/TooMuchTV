package south.m3utools

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class M3uParseParsingSpecs() {
    private val m3uParser = M3uCollectionParser();


    @Test
    fun can_parse_single_channel_stream_file() {
        val rawData = "#EXTM3U\n" +
                "#EXTINF:-1 tvg-id=\"TF1.fr\" tvg-name=\"FRN| TF1 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/tf1hd.png\" group-title=\"FRANCE HD\",FRN| TF1 HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508244";

        val result = m3uParser.parse(rawData);
        assertEquals(result.channelCount, 1)

        val channel = result.tvChannels[0]
        assertEquals(channel.name, "FRN| TF1 HD");

    }

    @Test
    fun can_parse_multiple_channels() {
        val rawData = "#EXTM3U\n" +
                "#EXTINF:-1 tvg-id=\"TF1.fr\" tvg-name=\"FRN| TF1 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/tf1hd.png\" group-title=\"FRANCE HD\",FRN| TF1 HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508244\n" +
                "#EXTINF:-1 tvg-id=\"France2.fr\" tvg-name=\"FRN| FRANCE 2 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/france2hd.png\" group-title=\"FRANCE HD\",FRN| FRANCE 2 HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508243\n" +
                "#EXTINF:-1 tvg-id=\"France3.fr\" tvg-name=\"FRN| FRANCE3 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/france3hd.png\" group-title=\"FRANCE HD\",FRN| FRANCE3 HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508242\n" +
                "#EXTINF:-1 tvg-id=\"CanalPlus.fr\" tvg-name=\"FRN| CANAL+ HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/canalplushd.png\" group-title=\"FRANCE HD\",FRN| CANAL+ HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508241"

        val result = m3uParser.parse(rawData);
        assertEquals(result.channelCount, 4)
    }

    @Test
    fun invalid_stream_will_return_empty_collection() {
        val rawData = "invalid stream"

        val result = m3uParser.parse(rawData);
        assertEquals(result.channelCount, 0)
    }

    @Test
    fun invalid_channels_are_skiped() {
        val rawData = "#EXTM3U\n" +
                "#EXTINXXX:-1 tvg-id=\"TF1.fr\" tvg-name=\"FRN| TF1 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/tf1hd.png\" group-title=\"FRANCE HD\",FRN| TF1 HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508244\n" +
                "#EXTINF:-1 tvg-id=\"France2.fr\" tvg-name=\"FRN| FRANCE 2 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/france2hd.png\" group-title=\"FRANCE HD\",FRN| FRANCE 2 HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508243\n" +
                "#-1 tvg-id=\"France3.fr\" tvg-name=\"FRN| FRANCE3 HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/france3hd.png\" group-title=\"FRANCE HD\",FRN| FRANCE3 HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508242\n" +
                "#AAA:-1 tvg-id=\"CanalPlus.fr\" tvg-name=\"FRN| CANAL+ HD\" tvg-logo=\"http://logo.protv.cc/picons/logos/canalplushd.png\" group-title=\"FRANCE HD\",FRN| CANAL+ HD\n" +
                "http://line.ult-ott.com:80/da9cf4b407/iptv1234/508241"

        val result = m3uParser.parse(rawData);
        assertEquals(result.channelCount, 1)
    }
}

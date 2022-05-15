package south.m3utools

import java.io.ByteArrayInputStream
import java.io.InputStream


fun String.toInputStream(): InputStream {
    return ByteArrayInputStream(this.toByteArray())
}

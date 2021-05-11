package hochenchong.litespring.core.io;

import hochenchong.litespring.util.Assert;
import hochenchong.litespring.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource {
    private final File file;

    private final String path;

    public FileSystemResource(File file) {
        Assert.notNull(file, "File must not be null");
        this.file = file;
        this.path = StringUtils.cleanPath(file.getPath());
    }

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.file = new File(path);
        this.path = StringUtils.cleanPath(path);
    }

    /**
     * This implementation opens a FileInputStream for the underlying file.
     * @see java.io.FileInputStream
     */
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }
}

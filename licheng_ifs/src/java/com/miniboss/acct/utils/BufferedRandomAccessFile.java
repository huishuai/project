package com.miniboss.acct.utils;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * <p>
 * Title: BufferedRandomAccessFile
 * </p>
 * <p>
 * Description: 用于大文件的读写，可实现断点读写
 * </p>
 * @author 杨军海
 */
public class BufferedRandomAccessFile extends RandomAccessFile implements Serializable {

    private static final long serialVersionUID = 1065350572729805963L;

    private static final int DEFAULT_BUFFER_BIT_LEN = 100;
    protected byte buf[];
    protected int bufbitlen;
    protected int bufsize;
    protected long bufmask;
    protected boolean bufdirty;
    protected int bufusedsize;

    private long curpos;
    private long bufStartPos;
    private long bufEndPos;
    private long fileEndPos;
    private boolean append;
    private String fileName;
    private long length;

    public BufferedRandomAccessFile(String name) throws IOException {
        this(name, StringUtil.R, DEFAULT_BUFFER_BIT_LEN);
    }

    public BufferedRandomAccessFile(File file) throws IOException {
        this(file.getAbsolutePath(), StringUtil.R, DEFAULT_BUFFER_BIT_LEN);
    }

    public BufferedRandomAccessFile(String name, int bufbitlen)
            throws IOException {
        this(name, StringUtil.R, bufbitlen);
    }

    public BufferedRandomAccessFile(File file, int bufbitlen)
            throws IOException {
        this(file.getAbsolutePath(), StringUtil.R, bufbitlen);
    }

    public BufferedRandomAccessFile(String name, String mode)
            throws IOException {
        this(name, mode, DEFAULT_BUFFER_BIT_LEN);
    }

    public BufferedRandomAccessFile(File file, String mode) throws IOException {
        this(file.getAbsolutePath(), mode, DEFAULT_BUFFER_BIT_LEN);
    }

    public BufferedRandomAccessFile(String name, String mode, int bufbitlen)
            throws IOException {
        super(name, mode);
        this.init(name, mode, bufbitlen);
    }

    public BufferedRandomAccessFile(File file, String mode, int bufbitlen)
            throws IOException {
        this(file.getAbsolutePath(), mode, bufbitlen);
    }

    private void init(String name, String mode, int bufbitlen)
            throws IOException {
        if (mode.equals(StringUtil.R) == true) {
            this.append = false;
        } else {
            this.append = true;
        }
        this.fileName = name;
        this.length = super.length();
        this.fileEndPos = this.length - 1;
        this.curpos = super.getFilePointer();

        if (bufbitlen < 0) {
            throw new IllegalArgumentException(StringUtil.BUFBITLEN_SIZE_MUST_0);
        }

        this.bufbitlen = bufbitlen;
        this.bufsize = 1 << bufbitlen;
        this.buf = new byte[this.bufsize];
        this.bufmask = ~((long) this.bufsize - 1L);
        this.bufdirty = false;
        this.bufusedsize = 0;
        this.bufStartPos = -1;
        this.bufEndPos = -1;
    }

    private void flushbuf() throws IOException {
        if (this.bufdirty == true) {
            if (super.getFilePointer() != this.bufStartPos) {
                super.seek(this.bufStartPos);
            }
            super.write(this.buf, 0, this.bufusedsize);
            this.bufdirty = false;
        }
    }

    private int fillbuf() throws IOException {
        super.seek(this.bufStartPos);
        this.bufdirty = false;
        return super.read(this.buf);
    }

    public byte read(long pos) throws IOException {
        if (pos < this.bufStartPos || pos > this.bufEndPos) {
            this.flushbuf();
            this.seek(pos);

            if ((pos < this.bufStartPos) || (pos > this.bufEndPos)) {
                throw new IOException();
            }
        }
        this.curpos = pos;
        return this.buf[(int) (pos - this.bufStartPos)];
    }

    public boolean write(byte bw) throws IOException {
        return this.write(bw, this.curpos);
    }

    public boolean append(byte bw) throws IOException {
        return this.write(bw, this.fileEndPos + 1);
    }

    public boolean write(byte bw, long pos) throws IOException {

        if ((pos >= this.bufStartPos) && (pos <= this.bufEndPos)) {
            this.buf[(int) (pos - this.bufStartPos)] = bw;
            this.bufdirty = true;

            if (pos == this.fileEndPos + 1) {
                this.fileEndPos++;
                this.bufusedsize++;
            }
        } else {
            this.seek(pos);

            if ((pos >= 0) && (pos <= this.fileEndPos)
                    && (this.fileEndPos != 0)) {
                this.buf[(int) (pos - this.bufStartPos)] = bw;

            } else if (((pos == 0) && (this.fileEndPos == 0))
                    || (pos == this.fileEndPos + 1)) {
                this.buf[0] = bw;
                this.fileEndPos++;
                this.bufusedsize = 1;
            } else {
                throw new IndexOutOfBoundsException();
            }
            this.bufdirty = true;
        }
        this.curpos = pos;
        return true;
    }

    public void write(byte b[], int off, int len) throws IOException {
        long writeendpos = this.curpos + len - 1;
        super.seek(this.curpos);
        super.write(b, off, len);
        if (writeendpos > this.fileEndPos)
            this.fileEndPos = writeendpos;
        this.seek(writeendpos + 1);
    }

    public int read(byte b[], int off, int len) throws IOException {
        long readendpos = this.curpos + len - 1;
        if (readendpos > this.fileEndPos) {
            len = (int) (this.length() - this.curpos + 1);
        }
        super.seek(this.curpos);
        len = super.read(b, off, len);
        readendpos = this.curpos + len - 1;
        this.seek(readendpos + 1);
        return len;
    }

    public void write(byte b[]) throws IOException {
        this.write(b, 0, b.length);
    }

    public int read(byte b[]) throws IOException {
        return this.read(b, 0, b.length);
    }

    public void seek(long pos) throws IOException {

        if ((pos < this.bufStartPos) || (pos > this.bufEndPos)) {
            this.flushbuf();
            if ((pos >= 0) && (pos <= this.fileEndPos)
                    && (this.fileEndPos != 0)) {
                this.bufStartPos = pos & this.bufmask;
                this.bufusedsize = this.fillbuf();

            } else if (((pos == 0) && (this.fileEndPos == 0))
                    || (pos == this.fileEndPos + 1)) {

                this.bufStartPos = pos;
                this.bufusedsize = 0;
            }
            this.bufEndPos = this.bufStartPos + this.bufsize - 1;
        }
        this.curpos = pos;
    }

    public long length() throws IOException {
        return this.max(this.fileEndPos + 1, this.length);
    }

    public void setLength(long newLength) throws IOException {
        if (newLength > 0) {
            this.fileEndPos = newLength - 1;
        } else {
            this.fileEndPos = 0;
        }
        super.setLength(newLength);
    }

    public long getFilePointer() throws IOException {
        return this.curpos;
    }

    private long max(long a, long b) {
        if (a > b)
            return a;
        return b;
    }

    public void close() throws IOException {
        this.flushbuf();
        super.close();
    }

    public long getCurpos() {
        return curpos;
    }

    public void setCurpos(long curpos) {
        this.curpos = curpos;
    }

    public long getBufStartPos() {
        return bufStartPos;
    }

    public void setBufStartPos(long bufStartPos) {
        this.bufStartPos = bufStartPos;
    }

    public long getFileEndPos() {
        return fileEndPos;
    }

    public void setFileEndPos(long fileEndPos) {
        this.fileEndPos = fileEndPos;
    }

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getLength() {
        return length;
    }

    public long getBufEndPos() {
        return bufEndPos;
    }

    public void setBufEndPos(long bufEndPos) {
        this.bufEndPos = bufEndPos;
    }
}

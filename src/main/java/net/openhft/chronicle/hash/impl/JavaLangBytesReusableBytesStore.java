package net.openhft.chronicle.hash.impl;

import net.openhft.chronicle.bytes.BytesStore;
import net.openhft.chronicle.bytes.RandomDataInput;
import net.openhft.lang.io.Bytes;

import java.nio.ByteBuffer;

public class JavaLangBytesReusableBytesStore
        implements BytesStore<JavaLangBytesReusableBytesStore, Void> {

    private Bytes bytes;

    public void setBytes(Bytes bytes) {
        this.bytes = bytes;
    }

    @Override
    public BytesStore<JavaLangBytesReusableBytesStore, Void> copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long capacity() {
        return bytes.capacity();
    }

    @Override
    public long address() throws UnsupportedOperationException {
        return bytes.address();
    }

    @Override
    public Void underlyingObject() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSwapInt(long offset, int expected, int value) {
        return bytes.compareAndSwapInt(offset, expected, value);
    }

    @Override
    public boolean compareAndSwapLong(long offset, long expected, long value) {
        return bytes.compareAndSwapLong(offset, expected, value);
    }

    @Override
    public byte readByte(long offset) {
        return bytes.readByte(offset);
    }

    @Override
    public short readShort(long offset) {
        return bytes.readShort(offset);
    }

    @Override
    public int readInt(long offset) {
        return bytes.readInt(offset);
    }

    @Override
    public long readLong(long offset) {
        return bytes.readLong(offset);
    }

    @Override
    public float readFloat(long offset) {
        return bytes.readFloat(offset);
    }

    @Override
    public double readDouble(long offset) {
        return bytes.readDouble(offset);
    }

    @Override
    public void nativeRead(long position, long address, long size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JavaLangBytesReusableBytesStore writeByte(long offset, byte i8) {
        bytes.writeByte(offset, i8);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore writeShort(long offset, short i) {
        bytes.writeShort(offset, i);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore writeInt(long offset, int i) {
        bytes.writeInt(offset, i);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore writeOrderedInt(long offset, int i) {
        bytes.writeOrderedInt(offset, i);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore writeLong(long offset, long i) {
        bytes.writeLong(offset, i);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore writeOrderedLong(long offset, long i) {
        bytes.writeOrderedLong(offset, i);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore writeFloat(long offset, float d) {
        bytes.writeFloat(offset, d);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore writeDouble(long offset, double d) {
        bytes.writeDouble(offset, d);
        return this;
    }

    @Override
    public JavaLangBytesReusableBytesStore write(long offsetInRDO,
                                                 byte[] bs, int offset, int length) {
        bytes.write(offsetInRDO, bs, offset, length);
        return this;
    }

    @Override
    public void write(long offsetInRDO, ByteBuffer bb, int offset, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JavaLangBytesReusableBytesStore write(long offsetInRDO,
                                                 RandomDataInput input, long offset, long length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nativeWrite(long address, long position, long size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reserve() throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void release() throws IllegalStateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public long refCount() {
        throw new UnsupportedOperationException();
    }
}

package io.github.linktosriram.s3lite.core.mapper;

import io.github.linktosriram.s3lite.api.response.CommonPrefix;
import io.github.linktosriram.s3lite.api.response.ListObjectsV2Response;
import io.github.linktosriram.s3lite.api.response.Owner;
import io.github.linktosriram.s3lite.api.response.S3Object;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ListObjectsV2ResponseMapper implements ResponseMapper<ListObjectsV2Response> {

    @Override
    public ListObjectsV2Response apply(final InputStream is) {
        final XMLInputFactory factory = ResponseMapper.newFactory().get();

        ListObjectsV2Response.Builder response = null;
        final List<CommonPrefix> commonPrefixes = new ArrayList<>();
        final List<S3Object> contents = new ArrayList<>();

        try {
            CommonPrefix.Builder commonPrefix = null;
            S3Object.Builder s3Object = null;
            Owner.Builder owner = null;

            boolean bBucketName, bCommonPrefixes, bContents, bContinuationToken, bDelimiter, bEncodingType,
                bIsTruncated, bKeyCount, bMaxKeys, bNextContinuationToken, bPrefix, bStartAfter;
            boolean bETag, bKey, bLastModified, bOwner, bSize, bStorageClass;
            boolean bDisplayName, bID;

            bBucketName = bCommonPrefixes = bContents = bContinuationToken = bDelimiter = bEncodingType =
                bIsTruncated = bKeyCount = bMaxKeys = bNextContinuationToken = bPrefix = bStartAfter = false;
            bKey = bLastModified = bETag = bOwner = bSize = bStorageClass = false;
            bDisplayName = bID = false;

            final XMLStreamReader reader = factory.createXMLStreamReader(is);
            while (reader.hasNext()) {
                switch (reader.next()) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "ListBucketResult":
                                response = ListObjectsV2Response.builder();
                                break;

                            case "Name":
                                bBucketName = true;
                                break;

                            case "CommonPrefix":
                                bCommonPrefixes = true;
                                commonPrefix = CommonPrefix.builder();
                                break;

                            case "Contents":
                                bContents = true;
                                s3Object = S3Object.builder();
                                break;

                            case "ContinuationToken":
                                bContinuationToken = true;
                                break;

                            case "Delimiter":
                                bDelimiter = true;
                                break;

                            case "EncodingType":
                                bEncodingType = true;
                                break;

                            case "IsTruncated":
                                bIsTruncated = true;
                                break;

                            case "KeyCount":
                                bKeyCount = true;
                                break;

                            case "MaxKeys":
                                bMaxKeys = true;
                                break;

                            case "NextContinuationToken":
                                bNextContinuationToken = true;
                                break;

                            case "Prefix":
                                bPrefix = true;
                                break;

                            case "StartAfter":
                                bStartAfter = true;
                                break;

                            case "ETag":
                                bETag = true;
                                break;

                            case "Owner":
                                bOwner = true;
                                owner = Owner.builder();
                                break;

                            case "Key":
                                bKey = true;
                                break;

                            case "LastModified":
                                bLastModified = true;
                                break;

                            case "Size":
                                bSize = true;
                                break;

                            case "StorageClass":
                                bStorageClass = true;
                                break;

                            case "DisplayName":
                                bDisplayName = true;
                                break;

                            case "ID":
                                bID = true;
                                break;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        if (bBucketName) {
                            response = response.bucketName(reader.getText());
                        } else if (bContinuationToken) {
                            response = response.continuationToken(reader.getText());
                        } else if (bDelimiter) {
                            response = response.delimiter(reader.getText());
                        } else if (bEncodingType) {
                            response = response.encodingType(reader.getText());
                        } else if (bIsTruncated) {
                            response = response.isTruncated(Boolean.valueOf(reader.getText()));
                        } else if (bKeyCount) {
                            response = response.keyCount(Integer.valueOf(reader.getText()));
                        } else if (bMaxKeys) {
                            response = response.maxKeys(Integer.valueOf(reader.getText()));
                        } else if (bNextContinuationToken) {
                            response = response.nextContinuationToken(reader.getText());
                        } else if (bPrefix) {
                            final String text = reader.getText();
                            if (bCommonPrefixes) {
                                commonPrefix = commonPrefix.prefix(text);
                            } else {
                                response = response.prefix(text);
                            }
                        } else if (bStartAfter) {
                            response = response.startAfter(reader.getText());
                        } else if (bETag) {
                            s3Object = s3Object.eTag(reader.getText());
                        } else if (bOwner) {
                            if (bDisplayName) {
                                owner = owner.displayName(reader.getText());
                            } else if (bID) {
                                owner = owner.id(reader.getText());
                            }
                        } else if (bKey) {
                            s3Object = s3Object.key(reader.getText());
                        } else if (bLastModified) {
                            s3Object = s3Object.lastModified(Instant.parse(reader.getText()));
                        } else if (bSize) {
                            s3Object = s3Object.size(Long.valueOf(reader.getText()));
                        } else if (bStorageClass) {
                            s3Object = s3Object.storageClass(reader.getText());
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "Name":
                                bBucketName = false;
                                break;

                            case "CommonPrefix":
                                bCommonPrefixes = false;
                                commonPrefixes.add(commonPrefix.build());
                                break;

                            case "Contents":
                                bContents = false;
                                contents.add(s3Object.build());
                                break;

                            case "ContinuationToken":
                                bContinuationToken = false;
                                break;

                            case "Delimiter":
                                bDelimiter = false;
                                break;

                            case "EncodingType":
                                bEncodingType = false;
                                break;

                            case "IsTruncated":
                                bIsTruncated = false;
                                break;

                            case "KeyCount":
                                bKeyCount = false;
                                break;

                            case "MaxKeys":
                                bMaxKeys = false;
                                break;

                            case "NextContinuationToken":
                                bNextContinuationToken = false;
                                break;

                            case "Prefix":
                                bPrefix = false;
                                break;

                            case "StartAfter":
                                bStartAfter = false;
                                break;

                            case "ETag":
                                bETag = false;
                                break;

                            case "Owner":
                                bOwner = false;
                                if (bContents) {
                                    s3Object = s3Object.owner(owner.build());
                                } else if (bCommonPrefixes) {
                                    commonPrefix = commonPrefix.owner(owner.build());
                                }
                                break;

                            case "Key":
                                bKey = false;
                                break;

                            case "LastModified":
                                bLastModified = false;
                                break;

                            case "Size":
                                bSize = false;
                                break;

                            case "StorageClass":
                                bStorageClass = false;
                                break;

                            case "DisplayName":
                                bDisplayName = false;
                                break;

                            case "ID":
                                bID = false;
                                break;
                        }
                        break;

                    case XMLStreamConstants.END_DOCUMENT:
                        reader.close();
                        break;
                }
            }

            return response
                .commonPrefixes(commonPrefixes)
                .contents(contents)
                .build();
        } catch (final XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.nebula.glutils.net.upload;


import com.nebula.glutils.net.upload.parser.BaseResponseParser;
import com.nebula.glutils.net.upload.preprocessor.BasePreProcessor;

/**
 * Created by hjy on 7/19/15.<br>
 */
public class UploadOptions {

    private BasePreProcessor mPreProcessor;
    private BaseResponseParser mResponseParser;

    public UploadOptions(Builder builder) {
        mPreProcessor = builder.preProcessor;
        mResponseParser = builder.responseParser;
    }

    public BasePreProcessor getPreProcessor() {
        return mPreProcessor;
    }

    public BaseResponseParser getResponseParser() {
        return mResponseParser;
    }

    public static class Builder {

        private BasePreProcessor preProcessor;
        private BaseResponseParser responseParser;

        public Builder() {

        }

        public Builder setPreProcessor(BasePreProcessor preProcessor) {
            this.preProcessor = preProcessor;
            return this;
        }

        public Builder setResponseParser(BaseResponseParser parser) {
            this.responseParser = parser;
            return this;
        }

        public UploadOptions build() {
            UploadOptions options = new UploadOptions(this);
            return options;
        }

    }

}
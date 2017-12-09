/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This class implements the Context interface exposed to the user.
 */
package org.apache.pulsar.functions.instance;

import org.apache.pulsar.functions.api.Context;
import org.slf4j.Logger;

class ContextImpl implements Context {
    private JavaInstanceConfig config;
    private Logger logger;

    // Per Message related
    private String messageId;
    private String topicName;
    private long startTime;

    public ContextImpl(JavaInstanceConfig config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    public void setCurrentMessageContext(String messageId, String topicName) {
        this.messageId = messageId;
        this.topicName = topicName;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public String getMessageId() {
        return messageId;
    }

    @Override
    public String getTopicName() {
        return topicName;
    }

    @Override
    public String getFunctionName() {
        return config.getFunctionName();
    }

    @Override
    public String getFunctionId() {
        return config.getFunctionId();
    }

    @Override
    public String getFunctionVersion() {
        return config.getFunctionVersion();
    }

    @Override
    public long getMemoryLimit() {
        return config.getMaxMemory();
    }

    @Override
    public long getTimeBudgetInMs() {
        return config.getTimeBudgetInMs();
    }

    @Override
    public long getRemainingTimeInMs() {
        return getTimeBudgetInMs() - (System.currentTimeMillis() - startTime);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}
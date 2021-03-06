/*
 * Copyright 2015 Higher Frequency Trading
 *
 *  http://www.higherfrequencytrading.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.openhft.chronicle.hash.replication;

import net.openhft.chronicle.hash.ChronicleHash;
import net.openhft.chronicle.hash.replication.DefaultEventualConsistencyStrategy;
import net.openhft.chronicle.map.replication.MapRemoteOperations;
import net.openhft.chronicle.set.replication.SetRemoteOperations;

/**
 * Decision, if {@link MapRemoteOperations remote modification operation} should be accepted
 * or discarded. Used in {@link DefaultEventualConsistencyStrategy}.
 */
public enum AcceptanceDecision {
    /**
     * Acceptance decision -- the remote modification operation is applied to the local
     * {@link ChronicleHash} state.
     */
    ACCEPT,

    /**
     * Discard decision -- the remote modification operation is rejected.
     */
    DISCARD
}

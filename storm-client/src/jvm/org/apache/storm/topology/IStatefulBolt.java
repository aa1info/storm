/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.  The ASF licenses this file to you under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package org.apache.storm.topology;

import java.util.Map;
import org.apache.storm.state.State;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.tuple.Tuple;

/**
 * A bolt abstraction for supporting stateful computation. The state of the bolt is periodically checkpointed.
 *
 * <p>The framework provides at-least once guarantee for the
 * state updates. The stateful bolts are expected to anchor the tuples while emitting and ack the input tuples once its processed.</p>
 */
public interface IStatefulBolt<T extends State> extends IStatefulComponent<T> {
    /**
     * @see org.apache.storm.task.IBolt#prepare(Map, TopologyContext, OutputCollector)
     */
    void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector);

    /**
     * @see org.apache.storm.task.IBolt#execute(Tuple)
     */
    void execute(Tuple input);

    /**
     * @see org.apache.storm.task.IBolt#cleanup()
     */
    void cleanup();
}

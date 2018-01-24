/*
 * Copyright (c) 2018, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.svm.core.graal.nodes;

import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.nodeinfo.NodeCycles;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodeinfo.NodeSize;
import org.graalvm.compiler.nodes.calc.FloatingNode;
import org.graalvm.compiler.nodes.spi.LIRLowerable;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;

import com.oracle.svm.core.amd64.FrameAccess;
import com.oracle.svm.core.c.CGlobalData;
import com.oracle.svm.core.c.CGlobalDataImpl;
import com.oracle.svm.core.graal.code.SubstrateNodeLIRBuilder;

@NodeInfo(cycles = NodeCycles.CYCLES_1, size = NodeSize.SIZE_1, sizeRationale = "same as LoadAddressNode")
public final class CGlobalDataLoadAddressNode extends FloatingNode implements LIRLowerable {
    public static final NodeClass<CGlobalDataLoadAddressNode> TYPE = NodeClass.create(CGlobalDataLoadAddressNode.class);

    private final CGlobalDataImpl<?> data;

    public CGlobalDataLoadAddressNode(CGlobalData<?> data) {
        super(TYPE, FrameAccess.getWordStamp());
        this.data = (CGlobalDataImpl<?>) data;
    }

    public CGlobalDataImpl<?> getData() {
        return data;
    }

    @Override
    public void generate(NodeLIRBuilderTool gen) {
        ((SubstrateNodeLIRBuilder) gen).emitCGlobalDataLoadAddress(this);
    }
}

/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.load.java.descriptors

import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor
import org.jetbrains.kotlin.descriptors.impl.SimpleFunctionDescriptorImpl

interface SamTypeAliasConstructorDescriptor : SamConstructorDescriptor {
    val typeAliasDescriptor: TypeAliasDescriptor
}

class SamTypeAliasConstructorDescriptorImpl(
        override val typeAliasDescriptor: TypeAliasDescriptor,
        private val samInterfaceConstructorDescriptor: SamConstructorDescriptor
) : SimpleFunctionDescriptorImpl(
        typeAliasDescriptor.containingDeclaration,
        null,
        samInterfaceConstructorDescriptor.baseDescriptorForSynthetic.annotations,
        samInterfaceConstructorDescriptor.baseDescriptorForSynthetic.name,
        CallableMemberDescriptor.Kind.SYNTHESIZED,
        typeAliasDescriptor.source
), SamTypeAliasConstructorDescriptor {
    override val baseDescriptorForSynthetic: JavaClassDescriptor
        get() = samInterfaceConstructorDescriptor.baseDescriptorForSynthetic
}
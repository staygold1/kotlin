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

package kotlin.reflect

/**
 * An object of this type may be used to control the actual value of a `lateinit` property.
 * See the [Kotlin language documentation](http://kotlinlang.org/docs/reference/properties.html#late-initialized-properties)
 * for more information.
 */
public interface LateInit {
    /**
     * Returns `true` if the associated property has been initialized and it's safe to get its value.
     */
    public val isSet: Boolean

    /**
     * Resets the value of the associated property, whether it's already initialized with some value or not.
     * After this method is called, the property is not initialized anymore.
     */
    public fun reset()
}

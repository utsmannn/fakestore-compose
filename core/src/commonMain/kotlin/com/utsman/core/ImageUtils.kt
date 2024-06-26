package com.utsman.core

import androidx.compose.runtime.Composable
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.ComponentRegistryBuilder
import okio.Path
import okio.Path.Companion.toPath

internal expect fun ComponentRegistryBuilder.setupComponents(androidContext: Any? = null)
internal expect fun getImageCacheDirectoryPath(androidContext: Any? = null): Path

@Composable
expect fun AppImageLoader(): ImageLoader
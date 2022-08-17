package com.tubetoast.giffy.data.saved

import android.net.Uri
import com.tubetoast.giffy.data.CachedDataSource

interface SavedGifsCache : CachedDataSource<Uri, Uri>
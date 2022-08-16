package com.tubetoast.giffy.data.saved

import android.net.Uri
import com.tubetoast.giffy.data.CachedDataSource

interface SavedGifsRepository : CachedDataSource<Uri, Uri>
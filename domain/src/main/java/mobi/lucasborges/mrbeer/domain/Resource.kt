package mobi.lucasborges.mrbeer.domain

import android.support.annotation.NonNull
import android.support.annotation.Nullable

/**
 * Created by Lucsa Borges on 26/11/2017.
 */
class Resource<T> constructor(val status: Status, val data: T? = null, val message: String? = null) {

    companion object {
        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, @Nullable data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(@Nullable data: T): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
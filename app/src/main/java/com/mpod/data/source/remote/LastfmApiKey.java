package com.mpod.data.source.remote;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by xnorcode on 13/07/2018.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LastfmApiKey {
}

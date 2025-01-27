/*
 * Copyright (c) 2004-2022, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.dataexchange.client;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.web.util.UriComponentsBuilder;

/**
 * DHIS 2 instance configuration.
 *
 * @author Lars Helge Overland
 */
@Getter
@RequiredArgsConstructor
public class Dhis2Config
{
    /**
     * Base URL for the DHIS 2 instance, excluding the <code>/api</code> path.
     */
    @NonNull
    private final String url;

    /**
     * Username for the DHIS 2 instance.
     */
    @NonNull
    private final String username;

    /**
     * Password for the DHIS 2 instance.
     */
    @NonNull
    private final String password;

    /**
     * Returns a {@link UriComponentsBuilder} which is resolved to the base API
     * URL of the DHIS 2 instance.
     *
     * @return a resolved {@link UriComponentsBuilder}.
     */
    public UriComponentsBuilder getResolvedUriBuilder( String path )
    {
        return UriComponentsBuilder.fromHttpUrl( url )
            .pathSegment( "api" )
            .path( path );
    }
}

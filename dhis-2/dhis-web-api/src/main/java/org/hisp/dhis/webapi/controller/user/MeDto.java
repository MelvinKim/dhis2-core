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
package org.hisp.dhis.webapi.controller.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.hisp.dhis.organisationunit.OrganisationUnit;
import org.hisp.dhis.security.acl.Access;
import org.hisp.dhis.translation.Translation;
import org.hisp.dhis.user.User;
import org.hisp.dhis.user.UserAuthorityGroup;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.sharing.Sharing;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@JsonInclude( JsonInclude.Include.ALWAYS )
public class MeDto
{
    public MeDto( User user, Map<String, Serializable> settings, List<String> programs, List<String> authorities,
        List<String> dataSets )
    {
        this.id = user.getUid();
        this.username = user.getUsername();
        this.surname = user.getSurname();
        this.firstName = user.getFirstName();
        this.employer = user.getEmployer();
        this.languages = user.getLanguages();
        this.gender = user.getGender();
        this.jobTitle = user.getJobTitle();
        this.created = user.getCreated().toString();
        this.lastUpdated = user.getLastUpdated().toString();
        this.dataViewOrganisationUnits = user.getDataViewOrganisationUnits();
        this.favorites = user.getFavorites();
        this.sharing = user.getSharing();
        this.userGroupAccesses = user.getUserGroupAccesses();
        this.userAccesses = user.getUserAccesses();
        this.userGroups = user.getGroups();
        this.translations = user.getTranslations();
        this.teiSearchOrganisationUnits = user.getTeiSearchOrganisationUnits();
        this.organisationUnits = user.getOrganisationUnits();
        this.externalAccess = user.getExternalAccess();
        this.displayName = user.getDisplayName();
        this.access = user.getAccess();
        this.name = user.getName();
        this.email = user.getEmail();
        this.userRoles = user.getUserAuthorityGroups();
        this.userCredentials = null;

        this.settings = settings;
        this.programs = programs;
        this.authorities = authorities;
        this.dataSets = dataSets;
    }

    @JsonProperty( )
    private String id;

    @JsonProperty( )
    private String username;

    @JsonProperty( )
    private String surname;

    @JsonProperty( )
    private String firstName;

    @JsonProperty( )
    private String employer;

    @JsonProperty( )
    private String languages;

    @JsonProperty( )
    private String gender;

    @JsonProperty( )
    private String jobTitle;

    @JsonProperty( )
    private String created;

    @JsonProperty( )
    private String lastUpdated;

    @JsonProperty( )
    private Set<OrganisationUnit> dataViewOrganisationUnits;

    @JsonProperty( )
    protected Set<String> favorites;

    @JsonProperty( )
    protected Sharing sharing;

    @JsonProperty( )
    private Set<org.hisp.dhis.user.UserGroupAccess> userGroupAccesses;

    @JsonProperty( )
    private Set<org.hisp.dhis.user.UserAccess> userAccesses;

    @JsonProperty( )
    private Set<UserGroup> userGroups;

    @JsonProperty( )
    private Set<Translation> translations;

    @JsonProperty( )
    private Set<OrganisationUnit> teiSearchOrganisationUnits;

    @JsonProperty( )
    private Set<OrganisationUnit> organisationUnits;

    @JsonProperty( )
    private Boolean externalAccess;

    @JsonProperty( )
    private String displayName;

    @JsonProperty( )
    private Access access;

    @JsonProperty( )
    private String name;

    @JsonProperty( )
    private String email;

    @JsonProperty
    private Set<UserAuthorityGroup> userRoles;

    @JsonProperty( )
    private UserCredWrapperDto userCredentials;

    @JsonProperty( )
    private Map<String, Serializable> settings;

    @JsonProperty( )
    private List<String> programs;

    @JsonProperty( )
    private List<String> authorities;

    @JsonProperty( )
    private List<String> dataSets;

    protected void setUserCredentials( UserCredWrapperDto userCredWrapperDto )
    {
        this.userCredentials = userCredWrapperDto;
    }
}
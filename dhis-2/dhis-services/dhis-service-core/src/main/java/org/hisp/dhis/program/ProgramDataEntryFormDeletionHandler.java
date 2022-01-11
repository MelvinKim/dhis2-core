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
package org.hisp.dhis.program;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import org.hisp.dhis.dataentryform.DataEntryForm;
import org.hisp.dhis.dataentryform.DataEntryFormService;
import org.hisp.dhis.system.deletion.DeletionHandler;
import org.springframework.stereotype.Component;

/**
 * @author Chau Thu Tran
 */
@Component( "org.hisp.dhis.program.ProgramDataEntryFormDeletionHandler" )
public class ProgramDataEntryFormDeletionHandler
    extends DeletionHandler
{
    private final DataEntryFormService dataEntryFormService;

    private final ProgramStageService programStageService;

    public ProgramDataEntryFormDeletionHandler( DataEntryFormService dataEntryFormService,
        ProgramStageService programStageService )
    {
        checkNotNull( dataEntryFormService );
        checkNotNull( programStageService );
        this.dataEntryFormService = dataEntryFormService;
        this.programStageService = programStageService;
    }

    @Override
    protected void register()
    {
        whenDeleting( ProgramStage.class, this::deleteProgramStage );
    }

    public void deleteProgramStage( ProgramStage programStage )
    {
        DataEntryForm dataEntryForm = programStage.getDataEntryForm();

        if ( dataEntryForm != null )
        {
            boolean flag = false;

            Set<ProgramStage> programStages = programStage.getProgram().getProgramStages();

            programStages.remove( programStage );

            for ( ProgramStage stage : programStages )
            {
                if ( stage.getDataEntryForm() != null )
                {
                    programStage.setDataEntryForm( null );
                    programStageService.updateProgramStage( programStage );
                    flag = true;
                    break;
                }
            }

            if ( !flag )
            {
                dataEntryFormService.deleteDataEntryForm( dataEntryForm );
            }
        }
    }
}

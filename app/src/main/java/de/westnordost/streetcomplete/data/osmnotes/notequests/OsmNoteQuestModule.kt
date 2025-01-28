package de.westnordost.streetcomplete.data.osmnotes.notequests

import org.koin.dsl.module

val osmNoteQuestModule = module {
    factory { NoteQuestsHiddenDao(get()) }

    single<OsmNoteQuestSource> { get<OsmNoteQuestController>() }
    single<OsmNoteQuestsHiddenSource> { get<OsmNoteQuestsHiddenController>() }

    single { OsmNoteQuestController(get(), get(), get(), get()) }
}

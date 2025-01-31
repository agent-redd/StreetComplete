package de.westnordost.streetcomplete.quests.baby_changing_table

import de.westnordost.streetcomplete.R
import de.westnordost.streetcomplete.data.osm.geometry.ElementGeometry
import de.westnordost.streetcomplete.data.osm.osmquests.OsmFilterQuestType
import de.westnordost.streetcomplete.data.user.achievements.EditTypeAchievement.CITIZEN
import de.westnordost.streetcomplete.osm.Tags
import de.westnordost.streetcomplete.quests.YesNoQuestForm
import de.westnordost.streetcomplete.util.ktx.toYesNo

class AddBabyChangingTable : OsmFilterQuestType<Boolean>() {

    override val elementFilter = """
        nodes, ways with
        (
          // ask for all restaurants and toilets
          amenity ~ restaurant|toilets
          or
          // ask for fuel stations or shops which have toilets
          ((amenity ~ fuel or (shop and shop !~ no|vacant)) and toilets=yes)
          or
          //ask for cafés and fast_food which have toilets or seating
          (amenity ~ cafe|fast_food and (toilets=yes or indoor_seating=yes or outdoor_seating=yes))
        )
        and !diaper and !changing_table
    """
    override val changesetComment = "Survey availability of baby changing tables"
    override val wikiLink = "Key:changing_table"
    override val icon = R.drawable.ic_quest_baby
    override val isReplacePlaceEnabled = true
    override val achievements = listOf(CITIZEN)
    override val defaultDisabledMessage = R.string.default_disabled_msg_go_inside

    override fun getTitle(tags: Map<String, String>) = R.string.quest_baby_changing_table_title2

    override fun createForm() = YesNoQuestForm()

    override fun applyAnswerTo(answer: Boolean, tags: Tags, geometry: ElementGeometry, timestampEdited: Long) {
        tags["changing_table"] = answer.toYesNo()
    }
}

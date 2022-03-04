package de.westnordost.streetcomplete.quests.tactile_paving

import de.westnordost.streetcomplete.R
import de.westnordost.streetcomplete.data.osm.mapdata.Node
import de.westnordost.streetcomplete.quests.AbstractQuestAnswerFragment
import de.westnordost.streetcomplete.quests.AnswerItem
import de.westnordost.streetcomplete.quests.tactile_paving.TactilePavingAnswer.YES
import de.westnordost.streetcomplete.quests.tactile_paving.TactilePavingAnswer.NO
import de.westnordost.streetcomplete.quests.tactile_paving.TactilePavingAnswer.INCORRECT

class TactilePavingForm : AbstractQuestAnswerFragment<TactilePavingAnswer>() {

    override val contentLayoutResId = R.layout.quest_tactile_paving

    override val otherAnswers get() = listOfNotNull(
        createIncorrectAnswerItem()
    )

    private fun createIncorrectAnswerItem(): AnswerItem? {
        val node = osmElement as? Node ?: return null
        return if (node.tags["highway"] == "crossing" || node.tags["highway"] == "traffic_signals") {
            AnswerItem(R.string.quest_tactilePaving_incorrect) { applyAnswer(INCORRECT) }
        } else {
            null
        }
    }

    override val buttonPanelAnswers = listOf(
        AnswerItem(R.string.quest_generic_hasFeature_no) { applyAnswer(NO) },
        AnswerItem(R.string.quest_generic_hasFeature_yes) { applyAnswer(YES) }
    )
}

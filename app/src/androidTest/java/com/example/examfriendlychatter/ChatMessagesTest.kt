package com.example.examfriendlychatter

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.examfriendlychatter.data.Message
import com.example.examfriendlychatter.presentation.composables.ChatMessage
import com.example.examfriendlychatter.presentation.screens.ChatRoom
import org.junit.Rule
import org.junit.Test

class ChatMessagesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLeftDividerIsDisplayed() {
        composeTestRule.setContent {
            ChatMessage(message = Message("Test Left", "Me"), isLeft = true)
        }

        composeTestRule.onNodeWithContentDescription("Don'tWorryNoTestingHereLol").assertExists()
        composeTestRule.onNodeWithContentDescription("Don'tWorryNoTestingHereLol").assertIsDisplayed()
    }

    @Test
    fun testRightDividerIsDisplayed() {
        composeTestRule.setContent {
            ChatMessage(message = Message("Test Right", "Me2"), isLeft = false)
        }

        composeTestRule.onNodeWithContentDescription("VeryImportantDivider").assertExists()
        composeTestRule.onNodeWithContentDescription("VeryImportantDivider").assertIsDisplayed()
    }

    @Test
    fun testLeftSpacerIsDisplayed() {
        composeTestRule.setContent {
            ChatMessage(message = Message("Test Left Spacer", "Me"), isLeft = true)
        }

        composeTestRule.onNodeWithContentDescription("ImagineTestingASpacer").assertExists()
    }

    @Test
    fun testRightSpacerIsDisplayed() {
        composeTestRule.setContent {
            ChatMessage(message = Message("Test Right Spacer", "Me"), isLeft = false)
        }

        composeTestRule.onNodeWithContentDescription("VeryImportantSpacer").assertExists()
    }

    @Test
    fun messageIsSentAndDisplaysCorrectly() {
        composeTestRule.setContent {
            ChatRoom()
        }

        val initialSpacerCount = composeTestRule.onAllNodesWithContentDescription("ImagineTestingASpacer").fetchSemanticsNodes().size
        val initialDividerCount = composeTestRule.onAllNodesWithContentDescription("Don'tWorryNoTestingHereLol").fetchSemanticsNodes().size


        val message = "Test Send and Display"
        composeTestRule.onNodeWithContentDescription("MessageInput").performTextInput(message)
        composeTestRule.onNodeWithContentDescription("SendButton").performClick()


        composeTestRule.onNodeWithText(message).assertIsDisplayed()

        composeTestRule.onAllNodesWithContentDescription("ImagineTestingASpacer")
            .assertCountEquals(initialSpacerCount + 1)

        composeTestRule.onAllNodesWithContentDescription("Don'tWorryNoTestingHereLol")
            .assertCountEquals(initialDividerCount + 1)
    }
}
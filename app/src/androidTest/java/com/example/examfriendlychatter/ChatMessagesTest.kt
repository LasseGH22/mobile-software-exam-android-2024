package com.example.examfriendlychatter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.examfriendlychatter.data.Message
import com.example.examfriendlychatter.presentation.composables.ChatMessage
import org.junit.Rule
import org.junit.Test

class ChatMessagesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun chatMessageDisplaysCorrectly() {
        val message = Message(
            from = "Alice",
            message = "Hello, world!"
        )

        composeTestRule.setContent {
            ChatMessage(message = message, isLeft = true)
        }

        // Verify semantics for ChatMessage
        composeTestRule.onNodeWithContentDescription("ChatMessage").assertIsDisplayed()

        // Verify semantics for Profile Picture
        composeTestRule.onNodeWithContentDescription("ProfilePicture").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("ChatProfilePicture").assertIsDisplayed()

        // Verify text in the Message Field
        composeTestRule.onNodeWithContentDescription("ChatMessageText").assertIsDisplayed()
        composeTestRule.onNodeWithText(message.message).assertIsDisplayed()
    }

    @Test
    fun rightAlignedChatMessageDisplaysCorrectly() {
        val message = Message(
            from = "Bob",
            message = "Hi, Alice!"
        )

        composeTestRule.setContent {
            ChatMessage(message = message, isLeft = false)
        }

        // Verify right alignment
        composeTestRule.onNodeWithContentDescription("VeryImportantSpacer").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("VeryImportantDivider").assertIsDisplayed()
    }

    @Test
    fun spacerAndDividerNotTested() {
        val message = Message(
            from = "Charlie",
            message = "Spacer and divider should not break test!"
        )

        composeTestRule.setContent {
            ChatMessage(message = message, isLeft = true)
        }

        // Ensure irrelevant spacers are ignored
        composeTestRule.onNodeWithContentDescription("ImagineTestingASpacer").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Don'tWorryNoTestingHereLol").assertIsDisplayed()
    }

    @Test
    fun rightAlignedChatMessageExists() {
        val message = Message(
            from = "Bob",
            message = "Hi, Alice!"
        )

        composeTestRule.setContent {
            ChatMessage(message = message, isLeft = false)
        }

        // Assert that the spacer exists
        composeTestRule.onNodeWithContentDescription("VeryImportantSpacer", useUnmergedTree = true)
            .assertExists("VeryImportantSpacer does not exist in the UI hierarchy")

        // Assert that the divider exists
        composeTestRule.onNodeWithContentDescription("VeryImportantDivider", useUnmergedTree = true)
            .assertExists("VeryImportantDivider does not exist in the UI hierarchy")
    }

    @Test
    fun spacerAndDividerExist() {
        val message = Message(
            from = "Charlie",
            message = "Spacer and divider should not break test!"
        )

        composeTestRule.setContent {
            ChatMessage(message = message, isLeft = true)
        }

        // Assert that the irrelevant spacer exists
        composeTestRule.onNodeWithContentDescription("ImagineTestingASpacer", useUnmergedTree = true)
            .assertExists("ImagineTestingASpacer does not exist in the UI hierarchy")

        // Assert that the irrelevant divider exists
        composeTestRule.onNodeWithContentDescription("Don'tWorryNoTestingHereLol", useUnmergedTree = true)
            .assertExists("Don'tWorryNoTestingHereLol does not exist in the UI hierarchy")
    }
}
# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the **Goose Game Kata Android Version** - a workshop project focused on teaching Android testing practices. The app implements the classic Goose Game board game using Jetpack Compose UI. The architecture and UI are pre-built to allow workshop participants to focus solely on implementing comprehensive tests.

**Key Point:** This is a kata/workshop project where the primary goal is practicing Android testing techniques, not building production features. The code is intentionally left incomplete to be filled in through TDD practices.

## Architecture

The project follows a simple MVVM architecture with Jetpack Compose:

- **UI Layer (`view/`)**: Jetpack Compose screens (AddPlayerScreen, GameBoardScreen)
- **ViewModel Layer (`viewmodel/`)**: Single MainViewModel using Hilt for DI
- **Model Layer (`model/`)**: Data classes (Player, PlayerUI)
- **Navigation**: Type-safe navigation using kotlinx.serialization with routes (AddPlayerScreenRoute, GameBoardScreenRoute)
- **Dependency Injection**: Dagger Hilt (@HiltAndroidApp, @HiltViewModel, @AndroidEntryPoint)

The app has two main screens:
1. **AddPlayerScreen**: For adding players to the game
2. **GameBoardScreen**: The game board with a 10x7 grid (63 squares) showing player positions and dice rolls

## Testing Setup

The project uses a comprehensive testing stack:

- **Unit Tests**: JUnit 5 + Kotest assertions
- **Compose UI Tests**: Compose testing library with Robolectric for local tests
- **Mocking**: MockK
- **DI in Tests**: Hilt testing support with custom HiltTestRunner
- **Test Location**: All tests in `app/src/test/` (using Robolectric for Android component tests)

### Testing Patterns

**ViewModel Tests**:
```kotlin
class MainViewModelTest {
    private val viewModel = MainViewModel()

    @Test
    fun `SHOULD add player`() {
        viewModel.addPlayer("Pippo")
        viewModel.players.value shouldBe listOf(Player(0, "Pippo", 0, true))
    }
}
```

**Compose UI Tests** (with Robolectric):
```kotlin
@RunWith(AndroidJUnit4::class)
class AddPlayerScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `Add player is present`() {
        rule.setContent {
            AddPlayerScreen(...)
        }
        rule.onNodeWithText("Add Player").assertIsDisplayed()
    }
}
```
## Common Commands

### Building and Testing

```bash
# Run all tests (using JUnit 5)
./gradlew test

# Run tests for a specific class
./gradlew test --tests "com.filnik.goosegamekata.MainViewModelTest"

# Run a specific test method
./gradlew test --tests "com.filnik.goosegamekata.MainViewModelTest.SHOULD add player"

# Run tests with debug output
./gradlew test --info

# Lint the code
./gradlew ktlintCheck

# Auto-fix lint issues
./gradlew ktlintFormat

# Build the app
./gradlew assembleDebug

# Clean build
./gradlew clean
```

### Important Build Configuration Notes

- **JUnit 5**: Project uses JUnit 5 (not JUnit 4). The `useJUnitPlatform()` is configured in build.gradle.kts
- **Robolectric**: Enabled with `isIncludeAndroidResources = true` for Compose UI tests in unit test directory
- **targetSDK**: 36
- **minSDK**: 29

## Game Requirements

The kata follows these feature requirements (see README.md for full details):

1. **Add players** - Players can be added to the game, duplicates are rejected
2. **Dice rolling** - Two dice generate random numbers 1-6
3. **Player movement** - Players move around a 63-square board
4. **Win condition** - First to land exactly on square 63 wins (overshooting bounces back)
5. **Special squares** - "The Bridge" at square 6 jumps player to square 12

## Development Workflow

When implementing features:

1. Read the corresponding feature requirement in README.md
2. Write the test first (TDD approach)
3. Implement the minimum code to make the test pass
4. Run tests with `./gradlew test`
5. Ensure code formatting with `./gradlew ktlintCheck`

## Key Files

- `app/build.gradle.kts` - Build configuration with all testing dependencies
- `gradle/libs.versions.toml` - Version catalog for dependencies
- `MainActivity.kt` - Entry point with NavHost setup
- `viewmodel/MainViewModel.kt` - Main business logic (currently stub implementation)
- `app/src/test/java/com/filnik/goosegamekata/HiltTestRunner.kt` - Custom test runner for Hilt

## Important Notes

- Tests use **backtick naming convention**: `` `SHOULD add player` ``
- Use **Kotest matchers** (`shouldBe`) not JUnit assertions for cleaner syntax
- **StateFlow** is used for reactive state management in ViewModel
- Player positions are tracked in the `Player` data class `position` field
- The game board is rendered as a 10-column grid (GridCells.Fixed(10))

## Git Operations Policy

**Git operations are controlled** via `.claude/settings.json` configuration.

**Completely BLOCKED commands (deny):**
- ❌ `git add`, `git commit`, `git push`, `git pull`
- ❌ `git merge`, `git rebase`
- ❌ `git push --force`

**Commands that REQUIRE permission (ask before executing):**
- ⚠️ `git reset`, `git cherry-pick`, `git stash`

This is a workshop/kata project where manual git control is important. After making changes, Claude Code will inform you what files were modified so you can commit them manually.

**Configuration file**: See `.claude/settings.json` for the complete configuration.

# Personal Finance Companion Mobile App

A lightweight, intuitive Android application designed to help users understand and manage their daily money habits through transaction tracking, goal setting, and financial insights.

## Overview

The Personal Finance Companion is built with a focus on simplicity, usability, and thoughtful design. Rather than being a complex banking application, it serves as your daily companion for tracking finances, understanding spending patterns, and achieving savings goals.

### Key Features

- **Dashboard**: Visual overview of your financial activity with income, expenses, and balance at a glance
- **Transaction Management**: Add, edit, delete, and track all your income and expense transactions
- **Savings Goals**: Set and monitor multiple savings goals with progress tracking
- **Financial Insights**: Analyze spending patterns with detailed analytics and visual charts
- **Intuitive UI**: Clean, modern Material Design interface optimized for mobile use

## Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM with Repository Pattern
- **Database**: Android Room (SQLite)
- **State Management**: Android LiveData & ViewModel
- **UI**: Material Design 3, RecyclerView, CardView
- **Charts**: MPAndroidChart for data visualization
- **Build Tool**: Gradle with Kotlin DSL

## Project Structure

```
app/src/main/
├── java/com/androproj/expencetracker/
│   ├── data/
│   │   ├── local/
│   │   │   ├── AppDatabase.kt          # Room database setup
│   │   │   ├── TransactionDao.kt       # Transaction data access
│   │   │   └── GoalDao.kt              # Goal data access
│   │   └── model/
│   │       ├── Transaction.kt          # Transaction entity
│   │       └── Goal.kt                 # Goal entity
│   ├── repository/
│   │   └── FinanceRepository.kt        # Data layer abstraction
│   ├── ui/
│   │   ├── dashboard/
│   │   │   └── DashboardActivity.kt    # Main dashboard screen
│   │   ├── transactions/
│   │   │   ├── TransactionActivity.kt  # Transaction management
│   │   │   ├── TransactionViewModel.kt
│   │   │   └── TransactionAdapter.kt
│   │   ├── goals/
│   │   │   ├── GoalActivity.kt         # Goal tracking screen
│   │   │   └── GoalViewModel.kt
│   │   └── insights/
│   │       ├── InsightsActivity.kt     # Analytics screen
│   │       └── InsightsViewModel.kt
│   ├── utils/
│   │   ├── DateUtils.kt                # Date formatting utilities
│   │   └── DataInitializer.kt          # Sample data initialization
│   └── MainActivity.kt                  # App entry point
└── res/
    ├── layout/                         # UI layouts
    └── values/                         # Colors, strings, styles
```

## Core Requirements Implementation

### 1. Home Dashboard ✓
- **Summary Metrics**: Balance, Income, Expense, Savings status
- **Visual Chart**: Pie chart showing income vs expense breakdown
- **Card-based Layout**: Clean, organized presentation
- **Real-time Updates**: LiveData-driven updates from database

### 2. Transaction Tracking ✓
- **Full CRUD Operations**: Add, View, Edit, Delete transactions
- **Rich Data Fields**: Amount, Type (income/expense), Category, Date, Notes
- **Date Picker**: Material DatePicker for precise date selection
- **Type Selector**: Spinner to choose between income and expense
- **List View**: RecyclerView with formatted display
- **Edit/Delete Buttons**: Quick access on each transaction item
- **Confirmation Dialogs**: Safety checks before deletion

### 3. Goal Feature ✓
- **Multiple Goals**: Create and track multiple savings goals
- **Progress Visualization**: ProgressBar showing progress towards goal
- **Goal Details**: Title, target amount, category, deadline
- **Current Balance Tracking**: Automatically calculated from transactions
- **Card-based UI**: Organized, easy-to-scan interface

### 4. Insights Screen ✓
- **Top Spending Category**: Highlights highest spending category with amount
- **Total Expenses**: Aggregate spending analysis
- **Average Transaction**: Per-transaction average calculation
- **Weekly Trends**: Income vs expense comparison for current week
- **Bar Chart**: Visual representation of weekly income and expenses
- **Scrollable Layout**: Easy access to multiple metrics

### 5. Smooth Mobile UX ✓
- **Intuitive Navigation**: Clear main menu with descriptive buttons
- **Responsive Layouts**: ScrollView and weight-based layouts for flexibility
- **Error Handling**: Toast messages for validation and confirmation
- **Loading-friendly**: Database queries use coroutines for responsiveness
- **Empty States**: Graceful handling when no data exists
- **Visual Feedback**: Color-coded income (green) and expense (red) amounts

### 6. Local Data Handling ✓
- **Room Database**: SQLite-backed persistent storage
- **Dao Pattern**: Type-safe database access
- **LiveData Integration**: Reactive data binding
- **Query Methods**: Multiple filtering options (by type, category, date range)
- **Coroutine Support**: Async database operations

### 7. Code Structure ✓
- **MVVM Architecture**: Clear separation of concerns
- **Repository Pattern**: Abstracted data layer
- **ViewModel**: Lifecycle-aware state management
- **LiveData**: Reactive data streams
- **Adapter Pattern**: Reusable RecyclerView adapter
- **Utility Classes**: Organized helper functions

## Build and Setup

### Prerequisites
- Android Studio (latest version)
- Android SDK 24+ (minimum SDK version)
- Kotlin plugin

### Building the App

1. **Clone/Open Project**
   ```bash
   cd expencetracker
   ```

2. **Build the Project**
   ```bash
   ./gradlew build
   ```

3. **Install on Device/Emulator**
   ```bash
   ./gradlew installDebug
   ```

4. **Run Tests**
   ```bash
   ./gradlew test
   ```

### Running the App
- Launch from Android Studio Run menu
- Or use: `./gradlew runDebug`

## Dependencies

Key libraries used:

```kotlin
// AndroidX Core
androidx.core:core-ktx:1.12.0
androidx.appcompat:appcompat:1.6.1

// Room Database
androidx.room:room-runtime:2.6.1
androidx.room:room-ktx:2.6.1
androidx.room:room-compiler:2.6.1 (kapt)

// Lifecycle
androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0
androidx.lifecycle:lifecycle-livedata-ktx:2.7.0

// UI Components
com.google.android.material:material:1.11.0
androidx.constraintlayout:constraintlayout:2.1.4

// Charts
com.github.PhilJay:MPAndroidChart:v3.1.0
```

## Key Assumptions & Design Decisions

### 1. **Data Model**
- Transactions are immutable once created (edited = delete + create)
- Goals are independent of transactions (not transaction-based)
- All amounts stored as Double for precision
- Timestamps use system milliseconds for consistency

### 2. **Currency**
- App defaults to Indian Rupees (₹) as the currency symbol
- All formatting uses 2 decimal places
- This can be easily extended to support multi-currency

### 3. **Analytics Calculation**
- Weekly trends based on Calendar.MONDAY as week start
- "This Week" calculation uses current date as end point
- Averages exclude zero amounts
- Top category determined by total spending per category

### 4. **Database Strategy**
- Single database (finance_db) with two tables (transactions, goals)
- No complex relationships, normalized schema
- Sample data auto-initializes on first launch only
- Can handle thousands of transactions efficiently

### 5. **Navigation**
- Activity-based navigation for simplicity
- Intent-based communication between screens
- Back button supported natively
- No custom navigation framework (keeps it simple)

### 6. **UI/UX Philosophy**
- Material Design 3 principles
- Touch-friendly button sizes (minimum 48dp)
- Clear visual hierarchy with typography
- Color coding: Green for income, Red for expense
- Consistent spacing (16dp base rhythm)

## Features Walkthrough

### Adding a Transaction
1. Navigate to Transactions screen
2. Tap the "+" floating action button
3. Enter amount and select type (Income/Expense)
4. Choose or enter a category
5. Tap date selector to pick transaction date
6. Add optional notes
7. Tap Save

### Viewing Insights
1. Navigate to Insights screen
2. View top spending category and total expenses
3. Check average transaction size
4. Scroll to see weekly trend comparison
5. Bar chart visualizes income vs. expense

### Setting Goals
1. Navigate to Goals screen
2. Quick goal: Enter amount and tap "Set Goal"
3. Detailed goal: Tap "+" button
4. Enter goal title, amount, and category
5. Progress updates automatically as you save money

### Dashboard Overview
1. Top section shows Balance, Income, Expense, Savings
2. Pie chart displays income vs expense breakdown
3. Updates in real-time as you add transactions
4. Tap any screen to drill down into details

## Sample Data

The app includes pre-loaded sample data for demonstration:
- 8 diverse transactions across different categories
- Mix of income and expense entries
- Spanning the last week for trend visualization
- Categories: Salary, Food, Transport, Utilities, Entertainment, Freelance, Shopping

Data auto-initializes on first launch, skip on subsequent launches.

## Future Enhancements

Potential features for version 2.0:
- Dark mode support
- Transaction search and advanced filtering
- Budget limits with alerts
- Recurring transaction templates
- Data export (CSV/PDF)
- Cloud sync/backup
- Biometric authentication
- Multiple accounts/profiles
- Category customization
- Spending notifications

## Known Limitations

1. **No Offline Sync**: App works fully offline but doesn't sync with cloud
2. **Single User**: No multi-user support in current version
3. **No Notifications**: Reminder/alert features not yet implemented
4. **Fixed Currency**: Only Indian Rupees supported (easily extensible)
5. **No Image Attachments**: Receipts cannot be attached to transactions
6. **Basic Charts**: Limited to basic pie and bar charts (no complex trends)

## Troubleshooting

### App crashes on launch
- Clear app data: Settings > Apps > Personal Finance > Storage > Clear Data
- Reinstall the app
- Check logs in Android Studio

### Charts not displaying
- Ensure MPAndroidChart dependency is properly synced
- Run `./gradlew clean build`
- Invalidate caches in Android Studio

### Database errors
- Clear app cache and data
- Delete and reinstall app
- Check device has sufficient storage

## Performance Considerations

- **Database Queries**: Use LiveData for efficient observation
- **List Rendering**: RecyclerView with proper item sizing
- **Chart Rendering**: Animations limited to 1 second
- **Coroutines**: All DB operations on IO dispatcher
- **Memory**: Sample data loaded asynchronously

## Testing Notes

Manual testing checklist:
- [ ] Add transaction with all fields
- [ ] Edit existing transaction
- [ ] Delete transaction with confirmation
- [ ] View dashboard with multiple transactions
- [ ] Check pie chart updates correctly
- [ ] Create goal and verify progress
- [ ] View insights with proper calculations
- [ ] Test with empty database
- [ ] Test date picker functionality
- [ ] Verify color coding (income/expense)

## License

This project is created as an assignment for mobile development evaluation.

## Contact & Support

For questions or issues:
- Review the code structure in `app/src/main/java`
- Check database schema in `AppDatabase.kt`
- Refer to activity implementations for usage patterns

---

**Version**: 1.0  
**Last Updated**: April 2026  
**Target**: Android 24+ (API Level 24 - Android 7.0)

